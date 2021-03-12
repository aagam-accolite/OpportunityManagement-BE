package com.accolite.opportunitymanagement.controller;


import com.accolite.opportunitymanagement.model.Audit;
import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.Impl.AuditServiceImpl;
import com.accolite.opportunitymanagement.service.Impl.TrendsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuditController.class)
public class AuditControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public AuditServiceImpl auditServiceImpl;

    @Autowired
    public ObjectMapper objectMapper;

    @Before
    private Audit setAuditObj()
    {
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
    public void getAllControllerTest() throws Exception{
        ArrayList<Audit> auditArrayList = new ArrayList<>();
        Audit audit = setAuditObj();
        auditArrayList.add(audit);
        Mockito.when(auditServiceImpl.getAllAudit()).thenReturn(auditArrayList);
        mockMvc.perform(get("/audit/getAll")).andExpect(status().isOk());
    }

    @Test
    public void addAuditTest() throws Exception{
        ArrayList<Audit> auditArrayList = new ArrayList<>();
        Audit audit = new Audit("aagam","aagam.shah@accolitedigital.com","Add","Old Val","New Val");
        audit.setId(1);
        String jsonString = objectMapper.writeValueAsString(audit);
        Mockito.when(auditServiceImpl.insertAudit(audit)).thenReturn(1);
        mockMvc.perform(post("/audit/addAudit")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());
    }

}
