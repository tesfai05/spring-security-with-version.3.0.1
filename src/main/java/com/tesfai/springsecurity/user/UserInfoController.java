package com.tesfai.springsecurity.user;

import com.tesfai.springsecurity.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserInfoController {
    private UserInfoService service;
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
}
