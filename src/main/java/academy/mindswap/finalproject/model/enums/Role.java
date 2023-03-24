package academy.mindswap.finalproject.model.enums;

public enum Role {
    ADMIN("Admin"),
    PERSONAL_TRAINER("Personal Trainer"),
    GENERIC ("Generic"),
    CLIENT("Client");

    private final String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
