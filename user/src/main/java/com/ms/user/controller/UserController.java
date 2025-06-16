package com.ms.user.controller;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto UserRecordDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body();
    }

}
