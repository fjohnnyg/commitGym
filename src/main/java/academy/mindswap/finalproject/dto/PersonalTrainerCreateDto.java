package academy.mindswap.finalproject.dto;

import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.model.enums.Specializations;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonalTrainerCreateDto {

    @NotBlank(message = "Must have a first name")
    private String firstName;

    @NotBlank(message = "Must have a last name")
    private String lastName;

    @NotBlank(message = "Must have a birth date")
    private LocalDate birthDate;

    @Email(message = "Email must be valid")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
    private String email;

    @NotBlank(message = "Must have a username")
    private String username;


    @NotBlank(message = "Must have a role")
    private Role role;


    @Min(value = 8, message = "Password must be at least 8 characters long")
    @NotBlank (message = "Must have a password")
    private String password;


    @NotBlank (message = "Must have a specialization")
    private Specializations specializations;





}
