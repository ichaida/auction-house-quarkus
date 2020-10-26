package xyz.ichaida.services;

import lombok.extern.slf4j.Slf4j;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.entities.AuctionStatus;
import xyz.ichaida.entities.Bid;
import xyz.ichaida.entities.User;
import xyz.ichaida.exceptions.BidCreationException;
import xyz.ichaida.exceptions.UserNotFoundException;
import xyz.ichaida.repositories.AuctionRepository;
import xyz.ichaida.repositories.BidRepository;
import xyz.ichaida.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * This class implements all Bidding CRUD logic
 *
 * @author Ismail Chaida.
 */
@ApplicationScoped
@Slf4j
public class BidService {

    @Inject
    private BidRepository bidRepository;

    @Inject
    private AuctionRepository auctionRepository;

    @Inject
    private UserRepository userRepository;

    @Transactional
    public Bid placeBidOnAuction(Double bidAmount, Long userId, Long auctionId) throws Exception {
        // Retrieve User
        User user = getUser(userId);

        return auctionRepository
            .findByIdOptional(auctionId)
            .filter(existingAuction -> Objects.equals(existingAuction.id, auctionId))
            .filter(existingAuction -> AuctionStatus.RUNNING.equals(existingAuction.status))
            .filter(existingAuction -> bidAmount > existingAuction.startingPrice && bidAmount > existingAuction.currentPrice)
            .map(existingAuction -> {
                auctionRepository.updateAuctionCurrentPriceById(bidAmount, existingAuction.id);
                // Place Bid
                Bid bid = createBid(bidAmount, user, existingAuction);
                bidRepository.persistAndFlush(bid);
                return bid;
            })
            .orElseThrow(() -> new BidCreationException("Bid creation error"));
    }

    public List<Bid> getAllBiddingsByUser(Long userId) throws Exception {
        return bidRepository.findBiddingsByUser(getUser(userId));
    }

    private User getUser(Long userId) throws UserNotFoundException {
        // Retrieve User
        return userRepository
            .findByIdOptional(userId)
            .orElseThrow(() -> new UserNotFoundException("User wasn't found"));
    }

    private Bid createBid(Double bidAmount, User user, Auction auction) {
        Bid bid = new Bid();
        bid.auction = auction;
        bid.user = user;
        bid.bidAmount = bidAmount;

        return bid;
    }
}
