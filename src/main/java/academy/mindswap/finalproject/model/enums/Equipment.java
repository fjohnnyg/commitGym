package academy.mindswap.finalproject.model.enums;

public enum Equipment {

    TREADMILL ("Treadmill"),
    BICYCLE ("Bicycle"),
    ELLIPTICAL ("Elliptical"),
    MACHINE ("Machine"),
    BODY_WEIGHT ("Body Weight"),
    STEP ("Step"),
    DUMBBELLS ("Dumbbells");

    private final String name;

    private Equipment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
