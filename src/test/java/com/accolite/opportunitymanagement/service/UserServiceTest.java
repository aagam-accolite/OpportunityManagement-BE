package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.mapper.OpportunityMapper;
import com.accolite.opportunitymanagement.mapper.UserMapper;
import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.Impl.OpportunityServiceImpl;
import com.accolite.opportunitymanagement.service.Impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    public void getAllUserTest(){
        List<User> userList = new ArrayList<>();
        User user = setUserObj();
        userList.add(user);
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(UserMapper.class))).thenReturn(userList);
        List<User> res = userServiceImpl.getAllUsers();
        Assert.assertEquals(userList.size(),res.size());
    }

    @Test
    public void getUserByEmail(){
        List<User> userList = new ArrayList<>();
        User user = setUserObj();
        userList.add(user);
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(UserMapper.class))).thenReturn(userList);
        List<User> res = userServiceImpl.getUserByEmail("Aagam.shah@accolitedigital.com");
        Assert.assertEquals(userList.size(),res.size());
    }

    @Test
    public void addUserTest(){
        List<User> userList = new ArrayList<>();
        User user = setUserObj();
        userList.add(user);
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                Mockito.any(UserMapper.class))).thenReturn(1);
        Assert.assertEquals(0,userServiceImpl.addUser(user));

    }

    private User setUserObj(){
        User user = new User();
        user.setUserId(1);
        user.setEmail("aagam.shah@accolitedigital.com");
        user.setName("Aagam Shah");
        return user;
    }
}
