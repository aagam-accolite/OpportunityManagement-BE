package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.mapper.OpportunityMapper;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.service.Impl.OpportunityServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.util.ArrayList;

@SpringBootTest
public class OpportunityServiceTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    OpportunityServiceImpl opportunityServiceImpl;

    @Test
    public void getAllTest(){
        ArrayList<Opportunity> opportunityList = new ArrayList<>();
        Opportunity opportunity = setOpportunityObject();
        opportunityList.add(opportunity);
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(OpportunityMapper.class))).thenReturn(opportunityList);
        ArrayList<Opportunity> opportunityResList = (ArrayList<Opportunity>) opportunityServiceImpl.getAllOpportunity();
        Assert.assertEquals(opportunityList.size(),opportunityResList.size());
    }

    @Test
    public void insertTest(){
        int expectedVal = 1;
        Opportunity opportunity = setOpportunityObject();
        Mockito.when(namedParameterJdbcTemplate.update(
                Mockito.anyString(),Mockito.any(SqlParameterSource.class),Mockito.any(KeyHolder.class)
        )).thenReturn(expectedVal);
        Assert.assertEquals(expectedVal,1);
    }

    @Test
    public void updateTest()
    {
        int expectedVal = 1;
        int id = 1;
        Opportunity opportunity = setOpportunityObject();
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                (Object[])Mockito.any()
        )).thenReturn(expectedVal);
        int resultVal = opportunityServiceImpl.update(opportunity);
        Assert.assertEquals(expectedVal,resultVal);
    }

    @Test
    public void deleteTest()
    {
        int expectedVal = 1;
        int id = 1;
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                (Object[])Mockito.any()
        )).thenReturn(expectedVal);
        int resultVal = opportunityServiceImpl.delete(id);
        Assert.assertEquals(expectedVal,resultVal);
    }

    @Test
    public void searchTest()
    {
        ArrayList<Opportunity> opportunityArrayList = new ArrayList<>();
        Opportunity opportunity = setOpportunityObject();
        opportunityArrayList.add(opportunity);
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(OpportunityMapper.class))).thenReturn(opportunityArrayList);
        ArrayList<Opportunity> resultArrayList;
        resultArrayList = (ArrayList<Opportunity>) opportunityServiceImpl.searchOpportunity("description","SDE Intern");
        Assert.assertEquals(resultArrayList.size(),opportunityArrayList.size());
        resultArrayList = (ArrayList<Opportunity>) opportunityServiceImpl.searchOpportunity("skills","Java");
        Assert.assertEquals(resultArrayList.size(),opportunityArrayList.size());
        resultArrayList = (ArrayList<Opportunity>) opportunityServiceImpl.searchOpportunity("location","Mumbai");
        Assert.assertEquals(resultArrayList.size(),opportunityArrayList.size());
    }

    @Test
    public void getOppByIdTest(){
        Opportunity opportunity = setOpportunityObject();
        Mockito.when(jdbcTemplate.queryForObject(
                Mockito.anyString(),
                Mockito.any(OpportunityMapper.class))).thenReturn(opportunity);
        Opportunity resOpportunity = opportunityServiceImpl.getOpportunityById(1);
        Assert.assertEquals(opportunity,resOpportunity);
    }

    private Opportunity setOpportunityObject()
    {
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

}
