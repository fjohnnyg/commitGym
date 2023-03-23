package academy.mindswap.finalproject.dto;

import academy.mindswap.finalproject.model.Goal;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FitnessTestCreateDtoBySchedule {

    @NotBlank(message = "Must have a date")
    private LocalDate date;

    @NotBlank(message = "Must have a id")
    private Long id;





}
