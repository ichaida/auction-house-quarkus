package xyz.ichaida.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import xyz.ichaida.entities.AuctionHouse;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

/**
 * @author Ismail Chaida.
 */
@ApplicationScoped
public class AuctionHouseRepository implements PanacheRepository<AuctionHouse> {

    // Retrieves the AuctionHouse by name
    public Optional<AuctionHouse> findByName(String name)  {
        return find("name", name).firstResultOptional();
    }
}
