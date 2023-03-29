package academy.mindswap.finalproject.model.enums;

public enum Specializations {

    YOGA ("Yoga"),
    CARDIO ("Cardio"),
    GENERIC ("Generic");

    private final String name;

    private Specializations(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}



/*
  for (Specializations specializations : personalTrainerUpdateSpecializationDto.getSpecializations()) {
            String specialization = String.valueOf(specializations);
            if (!EnumUtils.isValidEnum(Specializations.class, specialization)){
                throw new SpecializationDoesNotExist();
            }
        }
 */