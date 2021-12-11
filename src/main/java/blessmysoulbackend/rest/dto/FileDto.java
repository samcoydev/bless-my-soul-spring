package blessmysoulbackend.rest.dto;

import lombok.Data;

@Data
public class FileDto {

    private long id;

    private String name;

    private byte[] data;

}
