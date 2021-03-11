package com.accolite.opportunitymanagement.service.Impl;

import com.accolite.opportunitymanagement.mapper.UserMapper;
import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public int addUser(User user) {
        int flag = 0;
        List<User> users = getAllUsers();
        for(int i = 0; i<users.size(); i++){
            if(user.getEmail().equals(users.get(i).getEmail())){
                flag = 1;
                break;
            }
        }
        if(flag == 0) {
            String SQL = "insert into Users(name,email) values (?,?)";
            return jdbcTemplate.update(SQL,new Object[]{
                    user.getName(),
                    user.getEmail()
            });
        }
        return 0;
    }

    @Override
    public List<User> getAllUsers() {
        String SQL = "Select * from Users";
        return jdbcTemplate.query(SQL,new UserMapper());
    }

    @Override
    public List<User> getUserByEmail(String email) {
        List<User> userList;
        String SQL = "Select * from Users where email LIKE '%"+email+"%';";
        userList = jdbcTemplate.query(SQL,new UserMapper());
        return userList;
    }
}
