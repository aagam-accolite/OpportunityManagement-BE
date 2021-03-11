package com.accolite.opportunitymanagement.service;

import java.util.Map;
import java.util.List;

public interface TrendsService {

    public List<Map<String, String>> getCountLoc();

    public List<Map<String, String>> getCountSkills();

    public List<Map<String,String>> getCountDemand();

    public List<Map<String,String>> getCountMinExp();

    public List<Map<String,String>> getCountDescription();
}
