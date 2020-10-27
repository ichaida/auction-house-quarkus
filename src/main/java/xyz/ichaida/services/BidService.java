package xyz.ichaida.services;

import lombok.extern.slf4j.Slf4j;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.entities.AuctionStatus;
import xyz.ichaida.entities.Bid;
import xyz.ichaida.entities.User;
import xyz.ichaida.exceptions.AuctionNotFoundException;
import xyz.ichaida.exceptions.BidCreationException;
import xyz.ichaida.exceptions.BidException;
import xyz.ichaida.exceptions.UserNotFoundException;
import xyz.ichaida.repositories.AuctionRepository;
import xyz.ichaida.repositories.BidRepository;
import xyz.ichaida.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class implements all Bidding CRUD logic
 *
 * @author Ismail Chaida.
 */
@ApplicationScoped
@Slf4j
public class BidService {

    @Inject
    BidRepository bidRepository;

    @Inject
    AuctionRepository auctionRepository;

    @Inject
    UserRepository userRepository;

    @Transactional
    public Response placeBidOnAuction(Double bidAmount, Long userId, Long auctionId) {
        try {
            // Retrieve User
            User user = getUser(userId);

            Bid bid = auctionRepository
                .findByIdOptional(auctionId)
                // Id null checks
                .filter(existingAuction -> Objects.equals(existingAuction.id, auctionId))
                .filter(existingAuction -> AuctionStatus.RUNNING.equals(existingAuction.status))
                .filter(existingAuction -> bidAmount > existingAuction.startingPrice && bidAmount > existingAuction.currentPrice)
                .map(existingAuction -> {
                    existingAuction.currentPrice = bidAmount;
                    auctionRepository.persistAndFlush(existingAuction);
                    // Place Bid
                    var bidPlaced = createBid(bidAmount, user, existingAuction);
                    user.bids.add(bidPlaced);
                    userRepository.persistAndFlush(user);


                    return bidPlaced;
                })
                .orElseThrow(() -> new BidCreationException("Bid creation error"));

            return Response.status(204).entity(bid).build();

        } catch (UserNotFoundException | BidCreationException ex) {
            log.error("Unable to place a bid, {}", ex.getMessage());
            return Response.serverError().build();
        }
    }

    // All the bidding by a given username that happen thus far
    public List<Bid> getAllBiddingsByUser(String userName) throws UserNotFoundException {
        List<Bid> bidList = userRepository.findUserByName(userName)
            .map(user -> bidRepository.findBiddingsByUser(user))
            .orElseThrow(() -> new UserNotFoundException("Username not found"));

        // Either test if the current time is less than the end time as an extra test
        // Or only use the Auction Status, checking if it is in a RUNNING state
        return bidList.stream()
            .filter(bid -> LocalDateTime.now().isBefore(bid.auction.endTime))
            .filter(bid -> AuctionStatus.RUNNING.equals(bid.auction.status))
            .collect(Collectors.toList());
    }

    public User getWinnerForTerminatedAuction(Long auctionId) {
        try {
            Auction auction = auctionRepository
                .findByIdOptional(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("Auction does not exists"));

            if(!AuctionStatus.TERMINATED.equals(auction.status)){
               throw new IllegalStateException("Auction Status Should be Terminated");
            }

            User user = this.bidRepository.findMaxBidAmount(auction)
                .stream()
                .max(Comparator.comparing(bid -> bid.bidAmount))
                .map(bid -> bid.user)
                .orElseThrow(() -> new BidException("Could not find the winning Bidder"));

            return user;

        } catch (Exception e) {
            log.error("Unable to find a winner, {}", e.getMessage());
            return null;
        }
    }


    private User getUser(Long userId) throws UserNotFoundException {
        // Retrieve User
        return userRepository
            .findByIdOptional(userId)
            .orElseThrow(() -> new UserNotFoundException("User wasn't found"));
    }

    private Bid createBid(Double bidAmount, User user, Auction auction) {
        var bid = new Bid();
        bid.auction = auction;
        bid.user = user;
        bid.bidAmount = bidAmount;

        return bid;
    }
}
