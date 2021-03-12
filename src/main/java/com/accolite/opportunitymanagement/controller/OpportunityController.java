package com.accolite.opportunitymanagement.controller;

import com.accolite.opportunitymanagement.model.Audit;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.model.User;
import com.accolite.opportunitymanagement.service.Impl.AuditServiceImpl;
import com.accolite.opportunitymanagement.service.Impl.OpportunityServiceImpl;

import com.accolite.opportunitymanagement.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opportunity")
public class OpportunityController {

    @Autowired
    OpportunityServiceImpl opportunityServiceImpl;

    @Autowired
    AuditServiceImpl auditServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping(value = "/getAll")
    public List<Opportunity> getAllOpportunity(@RequestHeader(value = "Email") String email){
        List<Opportunity> opportunityList;
        opportunityList = opportunityServiceImpl.getAllOpportunity();
        return opportunityList;
    }

    @GetMapping(value = "/search/{colName}/{val}")
    public List<Opportunity> searchOpportunity(@PathVariable("colName") String colName,@PathVariable("val")  String val,@RequestHeader(value = "Email") String email)
    {
        List<Opportunity> opportunityList;
        opportunityList = opportunityServiceImpl.searchOpportunity(colName,val);
        return opportunityList;
    }


    @PostMapping(value = "/add")
    public int addOpportunity(@RequestBody Opportunity opportunity,@RequestHeader(value = "Email") String email)
    {
        List<User> userList = userServiceImpl.getUserByEmail(email);
        int id = opportunityServiceImpl.insert(opportunity);
        opportunity.setId(id);
        auditServiceImpl.insertAudit(new Audit(userList.get(0).getName(),email,"Add","",opportunity.toString()));
        return 1;
    }

    @PostMapping(value = "/update")
    public int updateOpportunity(@RequestBody Opportunity opportunity,@RequestHeader(value = "Email") String email)
    {
        List<User> userList = userServiceImpl.getUserByEmail(email);
        Opportunity opportunity1 = opportunityServiceImpl.getOpportunityById(opportunity.getId());
        auditServiceImpl.insertAudit(new Audit(userList.get(0).getName(),email,"Update",opportunity1.toString(),opportunity.toString()));
        return opportunityServiceImpl.update(opportunity);
    }

    @PostMapping(value = "/delete/{id}")
    public int deleteOpportunity(@PathVariable("id") int id,@RequestHeader(value = "Email") String email)
    {
        List<User> userList = userServiceImpl.getUserByEmail(email);
        Opportunity opportunity = opportunityServiceImpl.getOpportunityById(id);
        auditServiceImpl.insertAudit(new Audit(userList.get(0).getName(),email,"Delete",opportunity.toString(),""));
        return opportunityServiceImpl.delete(id);
    }


}
