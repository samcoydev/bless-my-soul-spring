package blessmysoulbackend.rest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Item item;

    @ManyToOne
    private User user;

    @Column(name="qty")
    private float qty;

    private boolean attachedToOrder;

}
