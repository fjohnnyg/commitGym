package academy.mindswap.finalproject.controller;

import academy.mindswap.finalproject.auth.AuthenticationRequest;
import academy.mindswap.finalproject.auth.AuthenticationResponse;
import academy.mindswap.finalproject.auth.RegisterRequest;
import academy.mindswap.finalproject.dto.UserCreateDto;
import academy.mindswap.finalproject.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register-user")

    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserCreateDto request) {
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }
    @PostMapping("/adminRegister")
    public ResponseEntity<AuthenticationResponse> adminRegister(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.adminRegister(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
