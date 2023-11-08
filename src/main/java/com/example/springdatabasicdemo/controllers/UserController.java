package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable String id) {
        Optional<UserDto> userDto = userService.findUser(id);

        return userDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        if (!id.equals(userDto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        UserDto updatedUser = userService.update(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
