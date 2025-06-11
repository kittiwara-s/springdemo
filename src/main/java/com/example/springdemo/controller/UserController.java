package com.example.springdemo.controller;

import com.example.springdemo.model.UserModel;
import com.example.springdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        UserModel data = userService.createData(userModel);
        return ResponseEntity.status(200).body(data);
    }

    @GetMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody UserModel userModel)
            throws BadRequestException{
        UserModel data =  userService.getLogin(userModel);
        return ResponseEntity.status(200).body(data);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> updateUser(
            @PathVariable Long id ,
            @RequestBody UserModel userModel)
            throws BadRequestException {
        UserModel data = userService.updateUsername(id, userModel);
        return ResponseEntity.status(200).body(data);
    }

}
