package xyz.ichaida.resources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import xyz.ichaida.services.AuctionService;
import xyz.ichaida.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Ismail Chaida.
 */
@Path(UserResource.RESOURCE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Users Resource", description = "User Rest Endpoint API")
public class UserResource {
    public static final String RESOURCE_PATH = "/api/users";

    final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = auctionService;
    }
}
