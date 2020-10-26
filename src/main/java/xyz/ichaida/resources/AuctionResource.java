package xyz.ichaida.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.services.AuctionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    final AuctionService auctionService;

    @Inject
    public AuctionResource(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    /**
     * Lists all Auction.
     *
     * @return List of all Auctions.
     */
    @GET
    @Path("/all")
    public List<Auction> getAll() {
        return this.auctionService.getAll();
    }

    @GET
    @Path("/find/status/{auctionStatus}")
    public List<Auction> findByStatus(@PathParam("auctionStatus") String status) {
        return auctionService.findByStatus(status);
    }

    @GET
    @Path("/find/house/id/{auctionHouseId}")
    public List<Auction> getAllByAuctionHouseId(@PathParam("auctionHouseId") Long id) {
        return this.auctionService.getAllByHouseId(id);
    }

    @GET
    @Path("/find/house/name/{auctionHouseName}")
    public List<Auction> getAllByAuctionHouseName(@PathParam("auctionHouseName") String name) {
        return this.auctionService.getAllByHouseName(name);
    }

    /**
     * Deletes Auction by Id.
     *
     * @param id Auction Id.
     * @return Response
     */
    @DELETE
    @Path("/delete/id/{auctionId}")
    public Response deleteAuctionById(@PathParam("auctionId") Long id) {
        return this.auctionService.deleteById(id);
    }

    @DELETE
    @Path("/delete/name/{auctionName}")
    public Response deleteAuctionByName(@PathParam("auctionName") String name) {
        return this.auctionService.deleteByName(name);
    }

    /**
     * Adds an Auction to an existing Auction House.
     *
     * @param auction Auction
     * @return 200
     */
    @POST
    @Path("/add")
    public Response addAuction(Auction auction) {
        return this.auctionService.create(auction);
    }
}
