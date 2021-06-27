package com.nsss.conferencemanagementtoolbackend.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.nsss.conferencemanagementtoolbackend.jwt.JwtUtils;
import com.nsss.conferencemanagementtoolbackend.model.ERole;
import com.nsss.conferencemanagementtoolbackend.model.Role;
import com.nsss.conferencemanagementtoolbackend.model.User;
import com.nsss.conferencemanagementtoolbackend.reponse.JwtResponse;
import com.nsss.conferencemanagementtoolbackend.reponse.MessageResponse;
import com.nsss.conferencemanagementtoolbackend.repository.RoleRepository;
import com.nsss.conferencemanagementtoolbackend.repository.UserRepository;
import com.nsss.conferencemanagementtoolbackend.request.LoginRequest;
import com.nsss.conferencemanagementtoolbackend.request.SignupRequest;
import com.nsss.conferencemanagementtoolbackend.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

/*        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();*/
        String strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        if(strRoles.equals("rp")){
            Role rpRole = roleRepository.findByName(ERole.ROLE_RP)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(rpRole);
        } else if(strRoles.equals("wp")){
            Role wpRole = roleRepository.findByName(ERole.ROLE_WP)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(wpRole);
        } else if(strRoles.equals("attendee")){
            Role attendeeRole = roleRepository.findByName(ERole.ROLE_ATTENDEE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(attendeeRole);
        } else if(strRoles.equals("admin")){
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
        } else if(strRoles.equals("editor")){
            Role editorRole = roleRepository.findByName(ERole.ROLE_EDITOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(editorRole);
        } else if(strRoles.equals("reviewer")){
            Role reviewerRole = roleRepository.findByName(ERole.ROLE_REVIEWER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(reviewerRole);
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
