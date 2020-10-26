package xyz.ichaida.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

/**
 * @author Ismail Chaida.
 */
@Entity
@Table(name = "bids")
public class Bid extends PanacheEntity {
    @Column(name = "bid_amount", unique = true, nullable = false)
    public Double bidAmount;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "auction_id")
    public Auction auction;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bidder_id")
    public User user;
}
