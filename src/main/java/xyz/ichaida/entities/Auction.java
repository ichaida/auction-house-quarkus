package xyz.ichaida.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author Ismail Chaida.
 */
@Entity
public class Auction extends PanacheEntity {
    @NotNull
    @Size(min = 3, max = 50)
    public String name;

    @NotNull
    public LocalDateTime startTime;

    @NotNull
    public LocalDateTime endTime;

    @NotNull
    public Double startingPrice;

    @NotNull
    public Double currentPrice;

    @Enumerated(EnumType.STRING)
    public AuctionStatus status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonbTransient
    public AuctionHouse auctionHouse;
}
