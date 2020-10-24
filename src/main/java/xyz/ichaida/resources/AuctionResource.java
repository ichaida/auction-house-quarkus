package xyz.ichaida.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.repositories.AuctionRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

/**
 * The class represent the Auction Rest Endpoint
 *
 * @author Ismail Chaida.
 */
@Path(AuctionResource.RESOURCE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Auctions Resource", description = "Auction Rest Endpoint API")
public class AuctionResource {
    public static final String RESOURCE_PATH = "/api/auctions";

    @Inject
    AuctionRepository auctionRepository;

    /**
     * Lists all Auction.
     *
     * @return List of all Auctions.
     */
    @GET
    public List<Auction> getAll() {
        return auctionRepository.listAll();
    }


    @GET
    @Path("/find/status/{auctionStatus}")
    public List<Auction> findByStatus(@PathParam("auctionStatus") String status) {
        return auctionRepository.listAll();
    }

    @GET
    @Path("/find/house/{auctionHouseId}")
    public List<Auction> getAllByAuctionHouseId(@PathParam("auctionHouseId") Long id) {
        //List<Auction> auctionListByAuctionHouseId = auctionRepository.findByAuctionHouseId()
        return Collections.emptyList();
    }

    /**
     * Deletes Auction by Id.
     *
     * @param id Auction Id.
     * @return Response
     */
    @DELETE
    @Path("/delete/{auctionId}")
    public Response deleteAuctionById(@PathParam("auctionId") Long id) {
        return null;
    }

    /**
     * Adds an Auction to an existing Auction House.
     *
     * @param auction Auction
     * @return
     */
    @POST
    @Transactional
    public Response addAuction(Auction auction) {
        auctionRepository.persist(auction);
        return Response.ok().build();
    }
}
