package xyz.ichaida.resources;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import xyz.ichaida.entities.Bid;
import xyz.ichaida.entities.User;
import xyz.ichaida.exceptions.UserNotFoundException;
import xyz.ichaida.services.BidService;
import xyz.ichaida.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Ismail Chaida.
 */
@Path(UserResource.RESOURCE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Users/Bidders Resource", description = "User Rest Endpoint API")
public class UserResource {
    public static final String RESOURCE_PATH = "/api/users";

    @Inject
    BidService bidService;

    @Inject
    UserService userService;


    @GET
    @Path("/all")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GET
    @Path("/all/bids/{userName}")
    public List<Bid> getAllBidings(@Parameter(description = "The User Name", required = true) @PathParam("userName") String userName) throws UserNotFoundException {
        return this.bidService.getAllBiddingsByUser(userName);
    }

    @POST
    @Path("/bid/{bidAmount}/user/{userId}/auction/{auctionId}")
    public Response addBid(
        @Parameter(description = "The Bid amount", required = true) @PathParam("bidAmount") Double bidAmount,
        @Parameter(description = "The User id", required = true) @PathParam("userId") Long userId,
        @Parameter(description = "The Auction id", required = true) @PathParam("auctionId") Long auctionId) {
        return this.bidService.placeBidOnAuction(bidAmount, userId, auctionId);
    }

    @POST
    @Path("/add/user")
    public Response addUser(@Valid User user) {
        return this.userService.create(user);
    }

    @GET
    @Path("/winner/{auctionId}")
    public User getAllBidings(@Parameter(description = "The auction Id", required = true) @PathParam("auctionId") Long auctionId) {
        return this.bidService.getWinnerForTerminatedAuction(auctionId);
    }
}
