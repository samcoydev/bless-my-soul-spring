package blessmysoulbackend.rest.helpers;

public enum StateType {
    DRAFT ("Draft"),
    COMING_SOON ("Coming Soon"),
    POSTED ("Posted"),
    ARCHIVED ("Archived");

    private final String state;

    StateType(String state) {
        this.state = state;
    }

    private String getState() {
        return this.state;
    }
}
