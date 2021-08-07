package blessmysoulbackend.rest.model;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="item_id")
    private int itemID;

    @Column(name="user_id")
    private int userID;

    @Column(name="qty")
    private float qty;

    public CartItem() { }

    CartItem(String name, int itemID, int userID, float qty) {
        this.name = name;
        this.itemID = itemID;
        this.userID = userID;
        this.qty = qty;
    }

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
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemID=" + itemID +
                ", userID=" + userID +
                ", qty=" + qty +
                '}';
    }
}
