package academy.mindswap.finalproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FitnessTestPTRequest {
    @NotBlank(message = "Must have a client username")
    private String clientUsername;
    @NotBlank(message = "Must have a date")
    private LocalDate date;
}
