package blessmysoulbackend.rest.dto;

import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.model.Order;
import blessmysoulbackend.rest.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartItemDto {

    private long id;

    @NotNull(message="Item cannot be empty.")
    private Item item;

    @NotNull(message="User cannot be empty.")
    private User user;

    @NotNull(message="Quantity cannot be empty.")
    private float qty;

    @NotNull(message="Attached to order mush have a value.")
    private boolean attachedToOrder;

}
