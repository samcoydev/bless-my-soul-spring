package blessmysoulbackend.rest.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {

    private long id;

    @NotNull(message="Name cannot be empty.")
    private String name;

}
