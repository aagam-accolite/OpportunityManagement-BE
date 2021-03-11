package com.accolite.opportunitymanagement.controller;

import com.accolite.opportunitymanagement.service.Impl.TrendsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/trends")
public class TrendsController {

    @Autowired
    TrendsServiceImpl trendsServiceImpl;

    @GetMapping("/getLocation")
    public List<Map<String,String>> getLocation()
    {
        List<Map<String,String>> list = trendsServiceImpl.getCountLoc();
        return list;
    }
    @GetMapping("/getSkills")
    public List<Map<String,String>> getSkills()
    {
        List<Map<String,String>> list = trendsServiceImpl.getCountSkills();
        return list;
    }
    @GetMapping("/getDemand")
    public List<Map<String,String>> getDemand()
    {
        List<Map<String,String>> list = trendsServiceImpl.getCountDemand();
        return list;
    }
    @GetMapping("/getMinExp")
    public List<Map<String,String>> getMinExp()
    {
        List<Map<String,String>> list = trendsServiceImpl.getCountMinExp();
        return list;
    }
    @GetMapping("/getDescription")
    public List<Map<String,String>> getDescription()
    {
        List<Map<String,String>> list = trendsServiceImpl.getCountDescription();
        return list;
    }
}
