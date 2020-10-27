package xyz.ichaida.services;

import lombok.extern.slf4j.Slf4j;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.entities.AuctionHouse;
import xyz.ichaida.entities.AuctionStatus;
import xyz.ichaida.exceptions.AuctionCreationException;
import xyz.ichaida.exceptions.AuctionHouseNotFoundException;
import xyz.ichaida.exceptions.AuctionNotFoundException;
import xyz.ichaida.repositories.AuctionHouseRepository;
import xyz.ichaida.repositories.AuctionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author Ismail Chaida.
 */
@ApplicationScoped
@Slf4j
public class AuctionService {

    @Inject
    private AuctionRepository auctionRepository;

    @Inject
    private AuctionHouseRepository auctionHouseRepository;


    public List<Auction> getAll() {
        return auctionRepository.listAll();
    }

    public List<Auction> findByStatus(String status) {
        return auctionRepository.findByStatus(status);
    }

    public List<Auction> getAllByHouseName(String houseName) {
        return auctionHouseRepository.findByName(houseName)
            .map(getAuctionListFunction())
            .orElse(Collections.emptyList());
    }

    private Function<AuctionHouse, List<Auction>> getAuctionListFunction() {
        return auction -> auctionRepository.findByAuctionHouseId(auction.id);
    }

    public List<Auction> getAllByHouseId(Long id) {
        return auctionHouseRepository.findByIdOptional(id)
            .map(getAuctionListFunction())
            .orElse(Collections.emptyList());
    }

    @Transactional
    public Response create(Long auctionHouseId, @Valid Auction auction) {
        try {
            log.debug("Creating a new Auction with {}", auction.toString());
            var existingAuctionSameIdOpt = auctionRepository.findByIdOptional(auction.id);
            if (existingAuctionSameIdOpt.isPresent()) {
                throw new AuctionNotFoundException("Error creating an Auction, existing auction with the same Id");
            }
            // Retrieve the Auction House
            var auctionHouse = auctionHouseRepository.findByIdOptional(auctionHouseId)
                .orElseThrow(() -> new AuctionHouseNotFoundException("The referenced Auction House doesn't exists"));
            // Validate Auction creation
            auctionValidation(auction);
            // We pass null because the PanacheEntity base class is handling the creation of the Ids otherwise will have DetachEntity exception
            auction.id = null;
            auction.house = auctionHouse;
            auctionHouse.auctions.add(auction);
            // The persistAndFlush() shorthand method on a Panache repository to persist to database then flush the changes.
            if (auctionHouseRepository.isPersistent(auctionHouse)) {
                auctionHouseRepository.persistAndFlush(auctionHouse);
            }
            // No need to return the entity body, just the status code
            return Response.status(204).build();
        } catch (Exception ex) {
            log.error("Unable to create the Auction {}", ex.getMessage());
            return Response.serverError().build();
        }
    }


    @Transactional
    public Response deleteById(Long id) {
        return this.auctionRepository.findByIdOptional(id)
            .map(deleteAuctionResponseFunction())
            .orElse(Response.status(Response.Status.NOT_FOUND).entity("Auction not found").build());
    }

    @Transactional
    public Response deleteByName(String name) {
        return this.auctionRepository.findByName(name)
            .map(deleteAuctionResponseFunction())
            .orElse(Response.status(Response.Status.NOT_FOUND).entity("Auction not found").build());
    }

    private Function<Auction, Response> deleteAuctionResponseFunction() {
        return (Auction auction) -> {
            auctionRepository.delete(auction);
            return Response.status(204).build();
        };
    }

    private void auctionValidation(@Valid Auction auction) throws AuctionCreationException {
        if (auction.startTime.isEqual(auction.endTime)) {
            log.error("Couldn't create an Auction, bidding duration is wrong");
            throw new AuctionCreationException("The Auction Starting Time should be different from the Ending Time");
        }

        if (auction.startTime.isAfter(auction.endTime)) {
            log.error("Couldn't create an Auction, Starting time is before the ending time");
            throw new AuctionCreationException("The Auction Starting Time should before Ending Time");
        }

        if (auction.startingPrice < 0 || auction.currentPrice < 0) {
            log.error("Couldn't create an Auction, The price shouldn't be negative");
            throw new AuctionCreationException("The Auction price shouldn't be negative");
        }

        if (auction.startingPrice >= auction.currentPrice) {
            log.error("Couldn't create an Auction, The Starting price shouldn't be greater than the current bidding Price");
            throw new AuctionCreationException("The Auction Starting price shouldn't be greater than the current bidding Price");
        }

        if (AuctionStatus.DELETED.equals(auction.status) || AuctionStatus.TERMINATED.equals(auction.status)) {
            log.error("Couldn't create an Auction, It shouldn't have a Status of {}", auction.status.toString());
            throw new AuctionCreationException(String.format("The Auction shouldn't have a Status of %s", auction.status.toString()));
        }
    }

}
