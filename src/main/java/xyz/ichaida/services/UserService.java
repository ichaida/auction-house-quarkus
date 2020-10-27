package xyz.ichaida.services;

import lombok.extern.slf4j.Slf4j;
import xyz.ichaida.entities.User;
import xyz.ichaida.repositories.UserRepository;

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
public class UserService {
    @Inject
    UserRepository userRepository;


    public List<User> getAll() {
        return userRepository.listAll();
    }

    @Transactional
    public Response create(User user) {
        try {
            log.debug("Creating a new User with {}", user.toString());

            user.id = null;
            this.userRepository.persistAndFlush(user);

            // No need to return the entity body, just the status code
            return Response.status(204).build();

        } catch (Exception ex) {
            log.error("Unable to create a User {}", ex.getMessage());
            return Response.serverError().build();
        }
    }
}
