package xyz.ichaida.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import xyz.ichaida.entities.AuctionHouse;
import xyz.ichaida.services.AuctionHouseService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path(AuctionHouseResource.RESOURCE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Auction House Resource", description = "Auction House Rest Endpoint API")
public class AuctionHouseResource {
    public static final String RESOURCE_PATH = "/api/houses";

    private final AuctionHouseService auctionHouseService;

    @Inject
    public AuctionHouseResource(AuctionHouseService auctionHouseService) {
        this.auctionHouseService = auctionHouseService;
    }

    @GET
    @Operation(summary = "Gets auction houses", description = "Lists all available auction houses")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AuctionHouse.class))))
    @Path("/all")
    public List<AuctionHouse> getAll() {
        return this.auctionHouseService.getAll();
    }

    @POST
    @Path("/add/name/{auctionHouseName}")
    public Response create(@PathParam("auctionHouseName") String name) {
        return auctionHouseService.create(name);
    }

    @DELETE
    @Path("/delete/id/{auctionHouseId}")
    public Response deleteById(@PathParam("auctionHouseId") Long id) {
        return auctionHouseService.deleteById(id);
    }

    @DELETE
    @Path("/delete/name/{auctionHouseName}")
    public Response deleteByName(@PathParam("auctionHouseName") String name) {
        return auctionHouseService.deleteByName(name);
    }


}
