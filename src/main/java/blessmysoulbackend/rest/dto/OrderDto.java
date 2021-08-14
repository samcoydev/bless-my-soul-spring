package blessmysoulbackend.rest.dto;

import blessmysoulbackend.rest.helpers.OrderType;
import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDto {

    private long id;

    @NotNull(message="User cannot be empty.")
    private User user;

    @NotNull(message="CartItems cannot be empty.")
    private List<CartItem> cartItems;

    private String notes;

    @NotNull(message="State cannot be empty.")
    private OrderType state;

}
