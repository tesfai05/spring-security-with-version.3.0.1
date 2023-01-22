package com.tesfai.springsecurity.user;

import com.tesfai.springsecurity.auth.AuthRequest;
import com.tesfai.springsecurity.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserInfoController {
    private UserInfoService service;
    private AuthService authService;
    private AuthenticationManager authenticationManager;
    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        return service.getUser(userId);
    }


    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@RequestBody UserInfo userInfo){
        return service.saveUser(userInfo);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws UsernameNotFoundException{
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return authService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

