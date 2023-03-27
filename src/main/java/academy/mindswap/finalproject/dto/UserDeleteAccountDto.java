package academy.mindswap.finalproject.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDeleteAccountDto {

    @NotBlank(message = "Must have a username")
    private String profileToInactive;

}
