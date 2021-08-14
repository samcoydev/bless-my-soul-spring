package blessmysoulbackend.rest.dto;

import blessmysoulbackend.rest.helpers.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    private long id;

    @NotNull(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "First cannot be empty")
    private String firstname;

    @NotNull(message = "Last name cannot be empty")
    private String lastname;

    @NotNull(message = "Email cannot be empty")
    private String email;

    private RoleType role;

    private String token;

}