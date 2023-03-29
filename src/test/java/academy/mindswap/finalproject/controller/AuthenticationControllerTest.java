package academy.mindswap.finalproject.controller;

import academy.mindswap.finalproject.auth.AuthenticationRequest;
import academy.mindswap.finalproject.auth.AuthenticationResponse;
import academy.mindswap.finalproject.auth.RegisterRequest;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerClient_shouldReturnOkResponse() {
        // given
        UserCreateDto userCreateDto = new UserCreateDto();
        when(authenticationService.registerClient(userCreateDto)).thenReturn(new AuthenticationResponse("token"));

        // when
        ResponseEntity<AuthenticationResponse> response = authenticationController.registerClient(userCreateDto);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("token", response.getBody().getToken());
        verify(authenticationService, times(1)).registerClient(userCreateDto);
    }

    @Test
    void registerPersonalTrainer_shouldReturnOkResponse() {
        // given
        UserCreateDto userCreateDto = new UserCreateDto();
        when(authenticationService.registerPersonalTrainer(userCreateDto)).thenReturn(new AuthenticationResponse("token"));

        // when
        ResponseEntity<AuthenticationResponse> response = authenticationController.registerPersonalTrainer(userCreateDto);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("token", response.getBody().getToken());
        verify(authenticationService, times(1)).registerPersonalTrainer(userCreateDto);
    }

    @Test
    void adminRegister_shouldReturnOkResponse() {
        // given
        RegisterRequest registerRequest = new RegisterRequest();
        when(authenticationService.adminRegister(registerRequest)).thenReturn(new AuthenticationResponse("token"));

        // when
        ResponseEntity<AuthenticationResponse> response = authenticationController.adminRegister(registerRequest);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("token", response.getBody().getToken());
        verify(authenticationService, times(1)).adminRegister(registerRequest);
    }

    @Test
    void authenticate_shouldReturnOkResponse() {
        // given
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        when(authenticationService.authenticate(authenticationRequest)).thenReturn(new AuthenticationResponse("token"));

        // when
        ResponseEntity<AuthenticationResponse> response = authenticationController.authenticate(authenticationRequest);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("token", response.getBody().getToken());
        verify(authenticationService, times(1)).authenticate(authenticationRequest);
    }
}
