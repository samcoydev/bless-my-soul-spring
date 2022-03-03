package blessmysoulbackend.rest.model;

import blessmysoulbackend.rest.helpers.StateType;
import lombok.Data;
import org.hibernate.annotations.Where;

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

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Category category;

    @Column(name = "state")
    private StateType state;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_featured")
    private boolean isFeatured;

    @ManyToOne
    @Lob
    private Image image;

}
