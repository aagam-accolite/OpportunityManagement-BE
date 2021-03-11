package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.model.User;
import java.util.List;

public interface UserService {

    public int addUser(User user);

    List<User> getAllUsers();

    List<User> getUserByEmail(String email);
}
