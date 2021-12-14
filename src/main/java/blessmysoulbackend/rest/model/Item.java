package blessmysoulbackend.rest.model;

import blessmysoulbackend.rest.helpers.StateType;
import javassist.bytecode.ByteArray;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Category category;

    @Column(name = "state")
    private StateType state;

    @ManyToOne
    @Lob
    private Image image;

}
