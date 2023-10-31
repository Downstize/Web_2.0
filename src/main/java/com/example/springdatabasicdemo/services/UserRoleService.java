package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.UserRoleDto;
import com.example.springdatabasicdemo.models.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserRoleService {

    UserRoleDto register(UserRoleDto userRole);

    void delete(String id);

    Optional<UserRoleDto> findUser(String id);

    List<UserRoleDto> getAll();

    UserRoleDto update(UserRoleDto userRole);
}
