package blessmysoulbackend.rest.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @Lob
    private Image image;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int sequence;

    private boolean allProducts;

    private boolean isFeaturedCategory;

}
