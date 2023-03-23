package academy.mindswap.finalproject.dto;

import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.model.enums.Specializations;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Must have first name")
    private String firstName;

    @NotBlank(message = "Must have last name")
    private String lastName;

    /*
    @NotBlank(message = "Must have a birth date")
    private String birthDate;
     */

    @Email(message = "Email must be valid")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
    private String email;

    @NotBlank(message = "Must have a role")
    private Role role;


    /*
    @Min(value = 8, message = "Password must be at least 8 characters long")
    @NotBlank (message = "Must have password")
    private String password;

     */

    @NotBlank (message = "Must have a specialization")
    private Specializations specializations;


}
