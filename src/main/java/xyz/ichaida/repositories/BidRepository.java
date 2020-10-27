package xyz.ichaida.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.entities.Bid;
import xyz.ichaida.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

/**
 * @author Ismail Chaida.
 */
@ApplicationScoped
public class BidRepository implements PanacheRepository<Bid> {

    /**
     * List all bidding by User that is happening thus far
     */
    public List<Bid> findBiddingsByUser(User user){
        return find("user", user).list();
    }

    public List<Bid> findAllByAuction(Auction auction) {
        return find("auction", auction).list();
    }

    public List<Bid> findMaxBidAmount(Auction auction){
        return find("auction", auction).list();
    }
}
