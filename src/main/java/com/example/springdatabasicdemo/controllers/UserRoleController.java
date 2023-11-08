package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.UserRoleDto;
import com.example.springdatabasicdemo.services.UserRoleService;
import com.example.springdatabasicdemo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRoleController {

    private final UserRoleService userRoleService;
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


    @GetMapping("/userRole/getAll")
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        List<UserRoleDto> userRoles = userRoleService.getAll();
        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/userRole/{id}")
    public ResponseEntity<UserRoleDto> findUserRoleById(@PathVariable String id) {
        Optional<UserRoleDto> userRoleDto = userRoleService.findUser(id);

        return userRoleDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/userRole/create")
    public ResponseEntity<UserRoleDto> createUserRole(@RequestBody UserRoleDto userRoleDto) {
        UserRoleDto createdUserRole = userRoleService.register(userRoleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserRole);
    }

    @PutMapping("/userRole/update/{id}")
    public ResponseEntity<UserRoleDto> updateUserRole(@PathVariable String id, @RequestBody UserRoleDto userRoleDto) {
        if (!id.equals(userRoleDto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        UserRoleDto updatedUserRole = userRoleService.update(userRoleDto);
        return ResponseEntity.ok(updatedUserRole);
    }

    @DeleteMapping("/userRole/delete/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable String id) {
        userRoleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
