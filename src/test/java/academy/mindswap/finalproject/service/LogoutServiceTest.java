package academy.mindswap.finalproject.service;

import academy.mindswap.finalproject.model.entities.Token;
import academy.mindswap.finalproject.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class LogoutServiceTest {

    @Mock
    TokenRepository tokenRepository;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    Authentication authentication;

    @InjectMocks
    LogoutService logoutService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogout() throws IOException {
        String jwt = "testJwt";
        String authHeader = "Bearer " + jwt;
        Token storedToken = new Token();
        when(request.getHeader("Authorization")).thenReturn(authHeader);
        when(tokenRepository.findByToken(jwt)).thenReturn(Optional.of(storedToken));

        logoutService.logout(request, response, authentication);

        verify(tokenRepository, times(1)).findByToken(jwt);
        verify(tokenRepository, times(1)).save(storedToken);
        verify(request, times(1)).getHeader("Authorization");
        verify(response, never()).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    void testLogoutWithInvalidAuthHeader() throws IOException {
        String authHeader = "invalidHeader";
        when(request.getHeader("Authorization")).thenReturn(authHeader);

        logoutService.logout(request, response, authentication);

        verify(tokenRepository, never()).findByToken(anyString());
        verify(tokenRepository, never()).save(any(Token.class));
        verify(request, times(1)).getHeader("Authorization");
        verify(response, never()).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}