package academy.mindswap.finalproject.controller;

import academy.mindswap.finalproject.dto.FitnessTestCreateDtoBySchedule;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.dto.UserDto;
import academy.mindswap.finalproject.service.FitnessTestServiceImpl;
import academy.mindswap.finalproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private FitnessTestServiceImpl fitnessTestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService, fitnessTestService);
    }

    @Test
    public void testGetById() {
        Long userId = 1L;
        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .build();
        when(userService.getUserById(userId)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getById(userId);

        assertEquals(userDto, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).getUserById(userId);
    }

    @Test
    public void testCreateUser() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .build();
        UserDto savedUserDto = UserDto.builder()
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .email(userCreateDto.getEmail())
                .build();
        when(userService.createUser(userCreateDto)).thenReturn(savedUserDto);

        ResponseEntity<UserDto> response = userController.createUser(userCreateDto);

        assertEquals(savedUserDto, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userService).createUser(userCreateDto);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        UserDto userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .build();
        UserDto updatedUserDto = UserDto.builder()
                .firstName("Jane")
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
        when(userService.updateUser(userId, updatedUserDto)).thenReturn(updatedUserDto);

        ResponseEntity<UserDto> response = userController.updateUser(userId, updatedUserDto);

        assertEquals(updatedUserDto, response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(userService).updateUser(userId, updatedUserDto);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(userService).deleteUser(userId);
    }

}
