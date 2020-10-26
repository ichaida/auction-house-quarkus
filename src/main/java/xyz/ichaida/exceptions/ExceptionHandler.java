package xyz.ichaida.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception Handler (ExceptionMapper)
 *
 * @author Ismail Chaida.
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof UserNotFoundException) {
            return notFoundResponse(exception.getMessage());
        }

        if (exception instanceof AuctionHouseNotFoundException) {
            return notFoundResponse(exception.getMessage());
        }

        if (exception instanceof AuctionNotFoundException) {
            return notFoundResponse(exception.getMessage());
        }

        if (exception instanceof BidCreationException) {
            return notFoundResponse(exception.getMessage());
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(new ErrorResponseBody("Something unexpected happened. Try again"))
            .build();
    }

    /**
     * Not Found error Response
     *
     * @param message Error Message
     * @return Response
     */
    private Response notFoundResponse(String message) {
        return Response.status(Response.Status.NOT_FOUND)
            .entity(new ErrorResponseBody(message))
            .build();
    }

    @Getter
    @AllArgsConstructor
    public static final class ErrorResponseBody {
        private final String message;
    }
}
