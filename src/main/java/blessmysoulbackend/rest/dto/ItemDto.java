package blessmysoulbackend.rest.dto;

import blessmysoulbackend.rest.helpers.StateType;
import blessmysoulbackend.rest.model.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemDto {

    private long id;

    @NotNull(message="Name cannot be empty.")
    private String name;

    private float price;

    private String description;

    private Category category;

    private StateType state;

}
