package com.accolite.opportunitymanagement.controller;

import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.Impl.TrendsServiceImpl;
import com.accolite.opportunitymanagement.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrendsController.class)
public class TrendsControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public TrendsServiceImpl trendsServiceImpl;

    private List<Map<String,String>> setTrendsObj(){
        List<Map<String,String>> list = new ArrayList<>();
        return list;
    }

    @Test
    public void getLocation() throws Exception{
        List<Map<String,String>> list = new ArrayList<>();
        list = setTrendsObj();
        Mockito.when(trendsServiceImpl.getCountLoc()).thenReturn(list);
        mockMvc.perform(get("/trends/getLocation")).andExpect(status().isOk());
    }

    @Test
    public void getMinExp() throws Exception{
        List<Map<String,String>> list = new ArrayList<>();
        list = setTrendsObj();
        Mockito.when(trendsServiceImpl.getCountMinExp()).thenReturn(list);
        mockMvc.perform(get("/trends/getMinExp")).andExpect(status().isOk());
    }

    @Test
    public void getDemand() throws Exception{
        List<Map<String,String>> list = new ArrayList<>();
        list = setTrendsObj();
        Mockito.when(trendsServiceImpl.getCountDemand()).thenReturn(list);
        mockMvc.perform(get("/trends/getDemand")).andExpect(status().isOk());
    }

    @Test
    public void getSkills() throws Exception{
        List<Map<String,String>> list = new ArrayList<>();
        list = setTrendsObj();
        Mockito.when(trendsServiceImpl.getCountSkills()).thenReturn(list);
        mockMvc.perform(get("/trends/getSkills")).andExpect(status().isOk());
    }

    @Test
    public void getDescription() throws Exception{
        List<Map<String,String>> list = new ArrayList<>();
        list = setTrendsObj();
        Mockito.when(trendsServiceImpl.getCountDescription()).thenReturn(list);
        mockMvc.perform(get("/trends/getDescription")).andExpect(status().isOk());
    }
}
