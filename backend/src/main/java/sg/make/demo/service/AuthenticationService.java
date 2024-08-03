package sg.make.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sg.make.demo.dtos.LoginUserDto;
import sg.make.demo.dtos.RegisterUserDto;
import sg.make.demo.model.User;
import sg.make.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }

//    public User signup(RegisterUserDto input) {
//        var user = new User();
//        user.setUsername(input.getUsername());
//        user.setPassword(passwordEncoder.encode(input.getPassword()));
//        user.setRole("ROLE_VIEW");
//        return userRepository.save(user);
//    }
}