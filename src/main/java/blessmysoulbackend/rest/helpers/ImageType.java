package blessmysoulbackend.rest.helpers;

public enum ImageType {
    CATALOG ("Catalog"),
    THUMBNAIL ("Thumbnail");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    private String getType() {
        return this.type;
    }
}
