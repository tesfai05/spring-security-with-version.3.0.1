package com.tesfai.springsecurity.user;

import com.tesfai.springsecurity.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserInfoService {
    private UserinfoRepository repository;
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<?> getUser(Long userId) {
        return ResponseEntity.ok(repository.findById(userId));
    }

    public ResponseEntity<?> saveUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        UserInfo savedUserInfo = repository.save(userInfo);
        return new ResponseEntity<>("User created Successfully with ID : "+savedUserInfo.getUserInfoId(), HttpStatus.CREATED);
    }
}
