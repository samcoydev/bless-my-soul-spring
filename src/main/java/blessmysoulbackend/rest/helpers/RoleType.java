package blessmysoulbackend.rest.helpers;

public enum RoleType {
    USER ("User"),
    ADMIN ("Admin");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }

    private String getRole() {
        return this.role;
    }
}
