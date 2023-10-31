package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {

    UserDto register(UserDto user);

    void delete(String id);

    Optional<UserDto> findUser(String id);

    List<UserDto> getAll();

    UserDto update(UserDto user);
}
