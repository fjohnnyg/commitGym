package academy.mindswap.finalproject.model.enums;

public enum Role {
    ADMIN("Admin"),
    PERSONAL_TRAINER("Personal Trainer"),
    GENERIC ("Generic"),
    INACTIVE("Inactive"),
    CLIENT("Client"),
    CLIENT_PERSONAL_TRAINER ("Client and Personal trainer");

    private final String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
