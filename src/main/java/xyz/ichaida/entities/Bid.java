package xyz.ichaida.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Ismail Chaida.
 */
@Entity
@Table(name = "bids")
public class Bid extends PanacheEntity {
    @NotNull
    @Column(name = "bid_amount", unique = true, nullable = false)
    public Double bidAmount;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "auction_id")
    public Auction auction;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonbTransient
    public User user;
}
