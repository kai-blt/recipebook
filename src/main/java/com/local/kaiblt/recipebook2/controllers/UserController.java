package com.local.kaiblt.recipebook2.controllers;

import com.local.kaiblt.recipebook2.models.User;
import com.local.kaiblt.recipebook2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userService.findAll();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/getuserinfo", produces = "application/json")
    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
