package xyz.ichaida.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import xyz.ichaida.entities.User;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Ismail Chaida.
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
