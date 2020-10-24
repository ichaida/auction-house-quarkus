package xyz.ichaida.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Ismail Chaida.
 */
@Entity
public class AuctionHouse extends PanacheEntity {
    @NotNull
    @Size(min = 3, max = 50)
    public String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "auctionHouse")
    @JsonbTransient
    public List<Auction> auctions;
}
