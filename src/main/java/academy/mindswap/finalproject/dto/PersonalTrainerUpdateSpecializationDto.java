package academy.mindswap.finalproject.dto;

import academy.mindswap.finalproject.model.enums.Specializations;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonalTrainerUpdateSpecializationDto {

    @NotBlank(message = "Must have a username")
    private Set<Specializations> specializations;
}
