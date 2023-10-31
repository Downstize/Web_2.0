package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.UserRoleDto;
import com.example.springdatabasicdemo.models.UserRole;
import com.example.springdatabasicdemo.repositories.OfferRepository;
import com.example.springdatabasicdemo.repositories.UserRoleRepository;
import com.example.springdatabasicdemo.services.UserRoleService;
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
public class UserRoleServiceImpl implements UserRoleService {


    private ModelMapper modelMapper;

    private UserRoleRepository userRoleRepository;

    private final ValidationUtil validationUtil;

    @Autowired
    public UserRoleServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleDto register(UserRoleDto role) {

        if (!this.validationUtil.isValid(role)) {
            this.validationUtil
                    .violations(role)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try{
            UserRole b = modelMapper.map(role, UserRole.class);
            if (b.getId() == null ||  findUser(b.getId()).isEmpty()) {
                return modelMapper.map(userRoleRepository.save(b), UserRoleDto.class);
            }
            } catch (Exception e) {
            System.out.println("Some thing went wrong!");
        }
        }
        return role;

    }

    @Override
    public List<UserRoleDto> getAll() {
        return userRoleRepository.findAll().stream().map((s) -> modelMapper.map(s, UserRoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserRoleDto> findUser(String id) {
        return Optional.ofNullable(modelMapper.map(userRoleRepository.findById(id), UserRoleDto.class));
    }

    @Override
    public void delete(String id) {
        if (userRoleRepository.findById(id).isPresent()) {
            userRoleRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Exception of delete");
        }
    }

    @Override
    public UserRoleDto update(UserRoleDto role) {
        if (userRoleRepository.findById(role.getId()).isPresent()) {
            return modelMapper.map(userRoleRepository.save(modelMapper.map(role, UserRole.class)), UserRoleDto.class);
        } else {
            throw new DataIntegrityViolationException("Exception of update");
        }
    }
}