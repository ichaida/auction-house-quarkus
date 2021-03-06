package xyz.ichaida.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.entities.AuctionHouse;
import xyz.ichaida.entities.AuctionStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
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
        return Arrays.stream(AuctionStatus.values())
            .filter(auctionStatus -> status.equalsIgnoreCase(auctionStatus.name()))
            .findFirst()
            .map(auctionStatus -> find("status", auctionStatus).list())
            .orElse(Collections.emptyList());
    }

    /**
     * Finds an Auction by House Id
     *
     * @param id AuctionHouse Id.
     * @return Optional<Auction>
     */
    public List<Auction> findByAuctionHouseId(Long id) {
        return find("house_id", id).list();
    }

    public int updateAuctionCurrentPriceById(Double bidAmount, Long auctionId) {
        return update("currentPrice = ?1 where id = ?2", bidAmount, auctionId);
    }
}
