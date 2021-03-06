package xyz.ichaida.services;

import lombok.extern.slf4j.Slf4j;
import xyz.ichaida.entities.AuctionHouse;
import xyz.ichaida.repositories.AuctionHouseRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Ismail Chaida.
 */
@ApplicationScoped
@Slf4j
public class AuctionHouseService {
    @Inject
    private AuctionHouseRepository auctionHouseRepository;

    public List<AuctionHouse> getAll() {
        return auctionHouseRepository.listAll();
    }

    @Transactional
    public Response create(String name) {
        log.debug("Creating an Auction House with the name {}", name);
        // Avoid Throwing exceptions, returning meaningful messages to the rest client
        return auctionHouseRepository.findByName(name)
            .map(
                existingAuctionHouse ->
                    Response.status(Response.Status.CONFLICT)
                        .entity("Auction House exists already in database.").build())
            .orElseGet(() -> {
                AuctionHouse auctionHouse = new AuctionHouse();
                auctionHouse.name = name;
                auctionHouseRepository.persist(auctionHouse);
                // No need to return the entity body (AuctionHouse), just the status code
                return Response.status(204).build();
            });
    }

    @Transactional
    public Response deleteById(Long id) {
        return this.auctionHouseRepository.findByIdOptional(id)
            .map(
                auctionHouse -> {
                    auctionHouseRepository.delete(auctionHouse);
                    return Response.status(204).build();
                })
            .orElse(Response.status(Response.Status.NOT_FOUND).entity("Auction House not found").build());
    }

    @Transactional
    public Response deleteByName(String name) {
        return this.auctionHouseRepository.findByName(name)
            .map(
                auctionHouse -> {
                    auctionHouseRepository.delete(auctionHouse);
                    return Response.status(204).build();
                })
            .orElse(Response.status(Response.Status.NOT_FOUND).entity("Auction House not found").build());
    }
}
