package blessmysoulbackend.rest.dto;

import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.model.User;

import javax.validation.constraints.NotNull;

public class CartDto {

    private long id;

    @NotNull(message="Item cannot be empty.")
    private Item item;

    @NotNull(message="User cannot be empty.")
    private User user;

    @NotNull(message="Quantity cannot be empty.")
    private float qty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", item=" + item +
                ", user=" + user +
                ", qty=" + qty +
                '}';
    }
}
