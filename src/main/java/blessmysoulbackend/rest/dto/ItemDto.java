package blessmysoulbackend.rest.dto;

import javax.validation.constraints.NotNull;

import blessmysoulbackend.rest.helpers.StateType;
import lombok.Data;

@Data
public class ItemDto {

    private long id;

    @NotNull(message="Name cannot be empty.")
    private String name;

    private float price;

    private String description;

    private StateType state;

}
