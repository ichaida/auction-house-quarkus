package xyz.ichaida.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ismail Chaida.
 */
@Entity
@Table(name = "users")
public class User extends PanacheEntity {
    @Column(name = "username", nullable = false)
    public String username;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(max = 256)
    public String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(max = 256)
    public String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    public List<Bid> bids = new ArrayList<>();


}
