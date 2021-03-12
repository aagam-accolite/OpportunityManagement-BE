package com.accolite.opportunitymanagement.controller;

import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.Impl.OpportunityServiceImpl;
import com.accolite.opportunitymanagement.service.Impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {


    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public UserServiceImpl userServiceImpl;

    @Autowired
    public ObjectMapper objectMapper;

    @Before
    private User setUserObj(){
        User user = new User();
        user.setUserId(1);
        user.setEmail("aagam.shah@accolite.com");
        user.setName("Aagam");
        return user;
    }

    @Test
    public void getAllControllerTest() throws Exception{
        ArrayList<User> userArrayList = new ArrayList<>();
        User user = setUserObj();
        userArrayList.add(user);
        Mockito.when(userServiceImpl.getAllUsers()).thenReturn(userArrayList);
        mockMvc.perform(get("/user/getAll")).andExpect(status().isOk());
    }

    @Test
    public void addUserTest() throws Exception{
        ArrayList<User> userArrayList = new ArrayList<>();
        User user = setUserObj();
        String jsonString = objectMapper.writeValueAsString(user);
        Mockito.when(userServiceImpl.addUser(user)).thenReturn(1);
        mockMvc.perform(post("/user/addUser")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void getUserByEmail() throws Exception{
        ArrayList<User> userArrayList = new ArrayList<>();
        User user = setUserObj();
        String email = "aagam.shah@accolitedigital.com";
        String jsonString = objectMapper.writeValueAsString(email);
        Mockito.when(userServiceImpl.addUser(user)).thenReturn(1);
        mockMvc.perform(get("/user/getUserByEmail/"+email)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }




}
