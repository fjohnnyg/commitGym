package academy.mindswap.finalproject.controller;

import academy.mindswap.finalproject.auth.AuthenticationRequest;
import academy.mindswap.finalproject.auth.AuthenticationResponse;
import academy.mindswap.finalproject.auth.RegisterRequest;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.exceptions.InactiveUser;
import academy.mindswap.finalproject.exceptions.UserNotFoundException;
import academy.mindswap.finalproject.model.entities.User;
import academy.mindswap.finalproject.model.enums.Role;
import academy.mindswap.finalproject.repository.UserRepository;
import academy.mindswap.finalproject.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationController = new AuthenticationController(authenticationService, userRepository);
    }

    @Test
    @DisplayName("Register client returns 200 OK status")
    void registerClient() {
        UserCreateDto userCreateDto = new UserCreateDto();
        when(authenticationService.registerClient(userCreateDto)).thenReturn(new AuthenticationResponse("token"));
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.registerClient(userCreateDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("token", responseEntity.getBody().getToken());
    }

    @Test
    @DisplayName("Register personal trainer returns 200 OK status")
    void registerPersonalTrainer() {
        UserCreateDto userCreateDto = new UserCreateDto();
        when(authenticationService.registerPersonalTrainer(userCreateDto)).thenReturn(new AuthenticationResponse("token"));
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.registerPersonalTrainer(userCreateDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("token", responseEntity.getBody().getToken());
    }

    @Test
    @DisplayName("Admin register returns 200 OK status")
    void adminRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        when(authenticationService.adminRegister(registerRequest)).thenReturn(new AuthenticationResponse("token"));
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.adminRegister(registerRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("token", responseEntity.getBody().getToken());
    }


    @Test
    @DisplayName("Authenticate throws UserNotFoundException when user not found")
    void authenticateThrowsUserNotFoundException() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> authenticationController.authenticate(authenticationRequest));
        verify(authenticationService, never()).authenticate(any());
    }
}