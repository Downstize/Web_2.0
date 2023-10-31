package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.UserRoleDto;
import com.example.springdatabasicdemo.models.UserRole;
import com.example.springdatabasicdemo.repositories.UserRoleRepository;
import com.example.springdatabasicdemo.services.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleDto register(UserRoleDto role) {
        UserRole b = modelMapper.map(role, UserRole.class);
        if (b.getId() == null ||  findUser(b.getId()).isEmpty()) {
            return modelMapper.map(userRoleRepository.save(b), UserRoleDto.class);
        } else {
            throw new DataIntegrityViolationException("A role with this id already exists");
        }
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