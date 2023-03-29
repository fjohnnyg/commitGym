package academy.mindswap.finalproject.model.enums;

public enum Goal {
    WEIGHT_LOSS("Weight loss"),
    MUSCLE_GAIN("Muscle Gain"),
    HYPERTROPHY("Hypertrophy"),
    MEDICAL_REASONS("Medical Reasons");

    private final String name;

    private Goal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
