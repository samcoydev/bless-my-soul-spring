package blessmysoulbackend.rest.model;

import blessmysoulbackend.rest.helpers.ImageType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private ImageType type;

    private String fileExtension;

    private String url;

}
