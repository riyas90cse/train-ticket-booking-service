package com.cloud.bees.challenge.service.impl;

import com.cloud.bees.challenge.entity.UserEntity;
import com.cloud.bees.challenge.mapper.TicketMapper;
import com.cloud.bees.challenge.mapper.UserMapper;
import com.cloud.bees.challenge.model.Ticket;
import com.cloud.bees.challenge.model.User;
import com.cloud.bees.challenge.repository.UserRepository;
import com.cloud.bees.challenge.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        UserEntity entity = userMapper.toEntity(user);
        UserEntity savedEntity = userRepository.saveAndFlush(entity);
        User savedUser = userMapper.toResource(savedEntity);
        log.info("Saved User {}", savedUser.toString());
        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        Optional<UserEntity> entity = userRepository.findById(user.getId());
        if (entity.isEmpty()) {
            throw new RuntimeException("User Entity Not Found" + HttpStatus.NOT_FOUND);
        }
        userMapper.copyToEntity(user, entity.get());
        UserEntity updatedEntity = userRepository.saveAndFlush(entity.get());
        User updatedUser = userMapper.toResource(updatedEntity);
        log.info("Updated User {}", updatedUser.toString());
        return updatedUser;
    }

    @Override
    public User getUser(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new RuntimeException("Entity Not Found");
        }
        User user = userMapper.toResource(entity.get());
        log.info("Existing User {}", user.toString());
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<UserEntity> entities = userRepository.findAll();
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<User> users = userMapper.toResources(entities);
        log.info("Existing Users {}", users.toString());
        return users;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new RuntimeException("Entity Not Found: " + " " + HttpStatus.NOT_FOUND);
        }
        log.info("Existing User to be deleted {}", entity.get());
        userRepository.delete(entity.get());
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> entity = userRepository.findUserEntityByEmail(email);
        if (entity.isEmpty()) {
            throw new RuntimeException("Entity Not Found: " + " " + HttpStatus.NOT_FOUND);
        }
        return userMapper.toResource(entity.get());
    }

}
