package xyz.ichaida.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import xyz.ichaida.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

/**
 * @author Ismail Chaida.
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Optional<User> findUserByName(String userName) {
        return find("lower(username)", userName.toLowerCase()).firstResultOptional();
    }
}
