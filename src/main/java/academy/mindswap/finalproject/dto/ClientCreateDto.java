package academy.mindswap.finalproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class ClientCreateDto {
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


    @Min(value = 8, message = "Password must be at least 8 characters long")
    @NotBlank (message = "Must have a password")
    private String password;
}
