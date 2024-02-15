package dev.esther.authentication.auth;

import dev.esther.authentication.config.JwtService;
import dev.esther.authentication.model.Role;
import dev.esther.authentication.model.User;
import dev.esther.authentication.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
//    public AuthenticationResponse register(RegisterRequest request) {
//        var user= User.builder();
//                .firstname=(request.getFirstname())
//                .lastname=(request.getLastname())
//                .email=(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
//
//                repository.save(user);
//
//                var jwtToken=jwtService.generateToken(user);
//
//
//
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=repository.findByEmail(request.getEmail())
                .orElseThrow();
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();


    }
}
