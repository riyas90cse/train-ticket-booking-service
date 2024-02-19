package com.cloud.bees.challenge.service;


import com.cloud.bees.challenge.model.User;

import java.util.List;

public interface IUserService {

    User saveUser(User user);
    User updateUser(User user);

    User getUser(Long id);

    List<User> getUsers();

    void deleteUser(Long id);

    User getUserByEmail(String email);

}
