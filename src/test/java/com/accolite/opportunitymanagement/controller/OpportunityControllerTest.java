package com.accolite.opportunitymanagement.controller;

import com.accolite.opportunitymanagement.model.Audit;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.Impl.AuditServiceImpl;
import com.accolite.opportunitymanagement.service.Impl.OpportunityServiceImpl;
import com.accolite.opportunitymanagement.service.Impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.*;


@WebMvcTest(controllers = OpportunityController.class)
public class OpportunityControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public OpportunityServiceImpl opportunityServiceImpl;

    @MockBean
    public AuditServiceImpl auditServiceImpl;

    @MockBean
    public UserServiceImpl userServiceImpl;

    @Autowired
    public ObjectMapper objectMapper;

    @Before
    private Opportunity setOpportunityObject() {
        Opportunity opportunity = new Opportunity();
        opportunity.setId(1);
        opportunity.setDescription("Software Developer");
        opportunity.setLocation("Mumbai");
        opportunity.setSkills("Spring Boot");
        opportunity.setUserEmail("aagamshah@accolitedigital.com");
        opportunity.setMinExperience(2);
        opportunity.setDemand(4);
        opportunity.setDate(new Date(System.currentTimeMillis()));
        return opportunity;
    }

    @Before
    private User setUserList() {
        User user = new User();
        user.setName("Aagam");
        user.setEmail("Aagam.shah@accolitedigital.com");
        user.setUserId(1);
        return user;
    }

    @Before
    private Audit setAuditObj() {
        Audit audit = new Audit();
        audit.setId(1);
        audit.setUserName("aagam");
        audit.setUserEmail("aagam.shah@accolitedigital.com");
        audit.setDate(new Date(System.currentTimeMillis()));
        audit.setOperation("Get");
        audit.setNewValues("New Val");
        audit.setOldValues("Old Val");
        return audit;
    }

    @Test
    public void getAllControllerTest() throws Exception {
        ArrayList<Opportunity> opportunityArrayList = new ArrayList<>();
        Opportunity opportunity = setOpportunityObject();
        opportunityArrayList.add(opportunity);
        Mockito.when(opportunityServiceImpl.getAllOpportunity()).thenReturn(opportunityArrayList);
        mockMvc.perform(get("/opportunity/getAll").header("Email", "aagam.shah@accolitedigital.com")).andExpect(status().isOk());
    }


    @Test
    public void addTest() throws Exception {
        Opportunity opportunity = setOpportunityObject();
        String jsonString = objectMapper.writeValueAsString(opportunity);
        Mockito.when(opportunityServiceImpl.insert(opportunity)).thenReturn(1);

        List<User> userList = new ArrayList<>();
        userList.add(setUserList());
        Mockito.when(userServiceImpl.getUserByEmail("aagam.shah@accolitedigital.com")).thenReturn(userList);

        Audit audit = setAuditObj();
        Mockito.when(auditServiceImpl.insertAudit(audit)).thenReturn(1);

        mockMvc.perform(post("/opportunity/add")
                .header("Email", "aagam.shah@accolitedigital.com")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        Opportunity opportunity = setOpportunityObject();
        String jsonString = objectMapper.writeValueAsString(opportunity);
        System.out.println(jsonString);
        Mockito.when(opportunityServiceImpl.update(opportunity)).thenReturn(1);

        List<User> userList = new ArrayList<>();
        userList.add(setUserList());
        Mockito.when(userServiceImpl.getUserByEmail("aagam.shah@accolitedigital.com")).thenReturn(userList);

        Mockito.when(opportunityServiceImpl.getOpportunityById(1)).thenReturn(opportunity);


        Mockito.when(opportunityServiceImpl.insert(opportunity)).thenReturn(1);

        mockMvc.perform(post("/opportunity/update")
                .header("Email", "aagam.shah@accolitedigital.com")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());

    }

    @Test
    public void deleteTest() throws Exception {

        List<User> userList = new ArrayList<>();
        userList.add(setUserList());
        Mockito.when(userServiceImpl.getUserByEmail("aagam.shah@accolitedigital.com")).thenReturn(userList);

        Opportunity opportunity = setOpportunityObject();
        Mockito.when(opportunityServiceImpl.getOpportunityById(1)).thenReturn(opportunity);

        Audit audit = setAuditObj();
        Mockito.when(auditServiceImpl.insertAudit(audit)).thenReturn(1);

        int id = 1;
        Mockito.when(opportunityServiceImpl.delete(id)).thenReturn(1);
        mockMvc.perform(post("/opportunity/delete/".concat(String.valueOf(id)))
                .header("Email", "aagam.shah@accolitedigital.com")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(String.valueOf(id))
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

    @Test
    public void searchTest() throws Exception {
        ArrayList<Opportunity> opportunityArrayList = new ArrayList<>();
        Opportunity opportunity = setOpportunityObject();
        opportunityArrayList.add(opportunity);

        Mockito.when(opportunityServiceImpl.searchOpportunity("description", "SDE Intern")).thenReturn(opportunityArrayList);
        mockMvc.perform(get("/opportunity/search/description/SDE Intern").header("Email", "aagam.shah@accolitedigital.com")).andExpect(status().isOk());

        Mockito.when(opportunityServiceImpl.searchOpportunity("skills", "Java")).thenReturn(opportunityArrayList);
        mockMvc.perform(get("/opportunity/search/skills/Java").header("Email", "aagam.shah@accolitedigital.com")).andExpect(status().isOk());

        Mockito.when(opportunityServiceImpl.searchOpportunity("location", "Mumbai")).thenReturn(opportunityArrayList);
        mockMvc.perform(get("/opportunity/search/location/Mumbai").header("Email", "aagam.shah@accolitedigital.com")).andExpect(status().isOk());
    }
}
