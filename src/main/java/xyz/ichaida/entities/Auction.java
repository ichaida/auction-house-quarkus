package xyz.ichaida.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author Ismail Chaida.
 */
@Entity
@Table(name = "auctions")
public class Auction extends PanacheEntity {
    @NotNull
    @Size(min = 3, max = 50)
    public String name;

    @NotNull
    @Column(name = "start_time", nullable = false)
    @JsonbDateFormat("dd/MM/yyyy HH:mm:ss")
    public LocalDateTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    @JsonbDateFormat("dd/MM/yyyy HH:mm:ss")
    public LocalDateTime endTime;

    @NotNull
    @Column(name = "starting_price", nullable = false)
    public Double startingPrice;

    @NotNull
    @Column(name = "current_price", nullable = false)
    public Double currentPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    public AuctionStatus status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonbTransient
    public AuctionHouse house;
}
