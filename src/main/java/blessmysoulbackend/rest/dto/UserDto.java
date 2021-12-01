package blessmysoulbackend.rest.dto;

import blessmysoulbackend.rest.helpers.RoleType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    private long id;

    @NotNull(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "First cannot be empty")
    private String firstname;

    @NotNull(message = "Last name cannot be empty")
    private String lastname;

    private RoleType role;

    private String token;

}