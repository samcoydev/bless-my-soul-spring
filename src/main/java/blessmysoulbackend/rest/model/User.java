package blessmysoulbackend.rest.model;

import blessmysoulbackend.rest.helpers.RoleType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="bms_users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "role_type")
    private RoleType role;

}
