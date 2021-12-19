package blessmysoulbackend.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import blessmysoulbackend.rest.helpers.OrderType;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<CartItem> cartItems = new ArrayList<>();

    private String notes;

    @Column(name="state")
    private OrderType state;

}
