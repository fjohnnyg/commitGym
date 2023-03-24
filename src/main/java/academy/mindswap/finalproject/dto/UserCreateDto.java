package academy.mindswap.finalproject.dto;

import academy.mindswap.finalproject.model.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

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
    private Set<Role> roles;

    @Min(value = 8, message = "Password must be at least 8 characters long")
    @NotBlank (message = "Must have a password")
    private String password;




}
