package xyz.ichaida.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import xyz.ichaida.entities.Auction;
import xyz.ichaida.entities.Bid;
import xyz.ichaida.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

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

    public List<Auction> findAllByUserId(Long userId) {
        return null;
    }
}
