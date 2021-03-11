package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.model.Opportunity;

import java.util.List;

public interface OpportunityService {

    public List<Opportunity> getAllOpportunity();

    public int insert(Opportunity opportunity);

    public int update(Opportunity opportunity);

    public int delete(int id);

    public List<Opportunity> searchOpportunity(String colName,String val);

    public Opportunity getOpportunityById(int id);

}
