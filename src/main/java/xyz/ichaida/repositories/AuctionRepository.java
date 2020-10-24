package xyz.ichaida.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.entities.AuctionHouse;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * @author Ismail Chaida.
 */
@ApplicationScoped
public class AuctionRepository implements PanacheRepository<Auction> {

    /**
     * Retrieves the Auction by name
     *
     * @param name Name
     * @return Optional<Auction>
     */
    public Optional<Auction> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    /**
     * Retrieves the Auction by status
     *
     * @param status Status
     * @return List<Auction>
     */
    public List<Auction> findByStatus(String status) {
        return find("status", status).list();
    }

    /**
     * Finds an Auction by House Id
     *
     * @param auctionHouse AuctionHouse.
     * @return Optional<Auction>
     */
    public Optional<Auction> findByAuctionHouseId(Long auctionHouse) {
        return find("auctionHouse", auctionHouse).firstResultOptional();
    }

}
