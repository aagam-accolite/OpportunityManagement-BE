package com.accolite.opportunitymanagement.controller;

import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping(value = "/getAll")
    public List<User> getAllUser(){
        List<User> userList;
        userList = userServiceImpl.getAllUsers();
        return userList;
    }

    @PostMapping(value = "/addUser")
    public int addUser(@RequestBody User user){
        return userServiceImpl.addUser(user);
    }

    @GetMapping(value = "/getUserByEmail/{email}")
    public List<User> getByEmail(@PathVariable("email") String email)
    {
        return userServiceImpl.getUserByEmail(email);
    }
}
