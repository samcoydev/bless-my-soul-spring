package blessmysoulbackend.rest.dto;

import javax.validation.constraints.NotNull;

public class CartDto {

    private long id;

    @NotNull(message="Name cannot be empty.")
    private String name;

    @NotNull(message="Item ID cannot be empty.")
    private int itemID;

    @NotNull(message="User ID cannot be empty.")
    private int userID;

    @NotNull(message="Quantity cannot be empty.")
    private float qty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
                ", name='" + name + '\'' +
                ", itemID=" + itemID +
                ", userID=" + userID +
                ", qty=" + qty +
                '}';
    }
}
