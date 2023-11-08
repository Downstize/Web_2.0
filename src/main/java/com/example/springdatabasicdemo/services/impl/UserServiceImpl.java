package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.UserRepository;
import com.example.springdatabasicdemo.services.UserService;
import com.example.springdatabasicdemo.utill.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private  ValidationUtil validationUtil;

    @Autowired
    public void setValidationUtil(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(UserDto users) {

        if (!this.validationUtil.isValid(users)) {
            this.validationUtil
                    .violations(users)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try{
                User b = modelMapper.map(users, User.class);
            if (b.getId() == null || findUser(b.getId()).isEmpty()) {
                return modelMapper.map(userRepository.save(b), UserDto.class);
            }
            } catch (Exception e) {
                System.out.println("Smth goes wrong!");
        }
        }
        return users;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map((s) -> modelMapper.map(s, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findUser(String id) {
        return Optional.ofNullable(modelMapper.map(userRepository.findById(id), UserDto.class));
    }

    @Override
    public void delete(String id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Exception of delete");
        }
    }

    @Override
    public UserDto update(UserDto users) {
        if (userRepository.findById(users.getId()).isPresent()) {
            return modelMapper.map(userRepository.save(modelMapper.map(users, User.class)), UserDto.class);
        } else {
            throw new DataIntegrityViolationException("Exception of update");
        }
    }
}