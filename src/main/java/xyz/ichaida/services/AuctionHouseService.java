package xyz.ichaida.services;

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
public class AuctionHouseService {
    @Inject
    AuctionHouseRepository auctionHouseRepository;

    public List<AuctionHouse> getAll() {
        return auctionHouseRepository.listAll();
    }

    @Transactional
    public Response create(String name) {
        return auctionHouseRepository.findByName(name)
            .map(
                existingAuctionHouse ->
                    Response.status(Response.Status.CONFLICT)
                        .entity("AuctionHouse exists already in db.").build())
            .orElseGet(() -> {
                AuctionHouse auctionHouse = new AuctionHouse();
                auctionHouse.name = name;
                auctionHouseRepository.persist(auctionHouse);
                return Response.status(204).build();
            });
    }

    public Response deleteById(Long id) {
        return auctionHouseRepository.findByIdOptional(id)
            .map(
                auctionHouse -> {
                    auctionHouseRepository.delete(auctionHouse);
                    return Response.status(204).build();
                })
            .orElse(Response.status(Response.Status.NOT_FOUND).entity("AuctionHouse not found").build());
    }

    public Response deleteByName(String name) {
        return auctionHouseRepository.findByName(name)
            .map(
                auctionHouse -> {
                    auctionHouseRepository.delete(auctionHouse);
                    return Response.status(204).build();
                })
            .orElse(Response.status(Response.Status.NOT_FOUND).entity("AuctionHouse not found").build());
    }
}
