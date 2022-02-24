package blessmysoulbackend.rest.dto;

import blessmysoulbackend.rest.model.Image;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {

    private long id;

    @NotNull(message="Name cannot be empty.")
    private String name;

    private Image image;

    private int sequence;

    private boolean allProducts;

    private boolean isFeaturedCategory;

    public boolean getAllProducts() {
        return allProducts;
    }

    public boolean getIsFeaturedCategory() {
        return isFeaturedCategory;
    }
}
