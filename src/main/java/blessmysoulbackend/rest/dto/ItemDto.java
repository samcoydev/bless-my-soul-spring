package blessmysoulbackend.rest.dto;

import blessmysoulbackend.rest.helpers.StateType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemDto {

    private long id;

    @NotNull(message="Name cannot be empty.")
    private String name;

    private float price;

    private String description;

    private StateType state;

}
