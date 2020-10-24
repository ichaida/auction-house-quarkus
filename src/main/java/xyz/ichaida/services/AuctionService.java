package xyz.ichaida.services;

import lombok.extern.slf4j.Slf4j;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.entities.AuctionHouse;
import xyz.ichaida.repositories.AuctionHouseRepository;
import xyz.ichaida.repositories.AuctionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
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
    public Response create(Auction auction) {
        try {
            // The persistAndFlush() shorthand method on a Panache repository to persist to database then flush the changes.
            auctionRepository.persistAndFlush(auction);
            // No need to return the entity body, just the status code
            return Response.status(204).build();
        } catch (PersistenceException pe) {
            log.error("Unable to create the parameter", pe);
            return Response.serverError().build();
        }
    }

    @Transactional
    public Response deleteById(Long id) {
        return this.auctionRepository.findByIdOptional(id)
            .map(deleteAuctionResponseFunction())
            .orElse(Response.status(Response.Status.NOT_FOUND).entity("Auction not found").build());
    }

    private Function<Auction, Response> deleteAuctionResponseFunction() {
        return (Auction auction) -> {
            auctionRepository.delete(auction);
            return Response.status(204).build();
        };
    }

    @Transactional
    public Response deleteByName(String name) {
        return this.auctionRepository.findByName(name)
            .map(deleteAuctionResponseFunction())
            .orElse(Response.status(Response.Status.NOT_FOUND).entity("Auction not found").build());
    }

}
