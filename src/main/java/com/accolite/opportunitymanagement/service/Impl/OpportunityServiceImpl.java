package com.accolite.opportunitymanagement.service.Impl;

import com.accolite.opportunitymanagement.mapper.OpportunityMapper;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("OpportunityService")
@Transactional
public class OpportunityServiceImpl implements OpportunityService  {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Opportunity> getAllOpportunity() {
        String SQL = "select * from Opportunity";
        List<Opportunity> opportunityList = jdbcTemplate.query(SQL,new OpportunityMapper());
        return opportunityList;
    }

    @Override
    public int insert(Opportunity opportunity) {
        KeyHolder holder = new GeneratedKeyHolder();
        String insertSQL = "insert into Opportunity (description,location,skills,userEmail,minExperience,demand,date) values (:description,:location,:skills,:userEmail,:minExperience,:demand,:date)";
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("description", opportunity.getDescription())
                .addValue("location", opportunity.getLocation())
                .addValue("skills", opportunity.getSkills())
                .addValue("userEmail",opportunity.getUserEmail())
                .addValue("minExperience",opportunity.getMinExperience())
                .addValue("demand",opportunity.getDemand())
                .addValue("date",opportunity.getDate());
        namedParameterJdbcTemplate.update(insertSQL,parameters,holder);
        return holder.getKey().intValue();
    }

    @Override
    public int update(Opportunity opportunity) {
        String SQL = "UPDATE opportunity SET description=?, location=?, skills=?, minExperience=?, demand=? , date = ? WHERE id=?";
        return jdbcTemplate.update(SQL,new Object[]{
                opportunity.getDescription(),
                opportunity.getLocation(),
                opportunity.getSkills(),
                opportunity.getMinExperience(),
                opportunity.getDemand(),
                opportunity.getDate(),
                opportunity.getId()
        });
    }

    @Override
    public int delete(int id) {
        String SQL = "DELETE FROM opportunity WHERE id =?";
        return jdbcTemplate.update(SQL,new Object[]{id});
    }

    @Override
    public List<Opportunity> searchOpportunity(String colName, String val) {
        List<Opportunity> list = null;
        String SQL = "";
        if(colName.equals("description"))  SQL = "SELECT * FROM Opportunity WHERE description LIKE '%"+val+"%';";
        if(colName.equals("location")) SQL = "SELECT * FROM Opportunity  WHERE location LIKE '%"+val+"%';";
        if(colName.equals("skills"))  SQL = "SELECT * FROM Opportunity  WHERE skills LIKE '%"+val+"%';";
        if(SQL != ""){
           list = jdbcTemplate.query(SQL,new OpportunityMapper());
        }
        return list;
    }

    @Override
    public Opportunity getOpportunityById(int id) {
        String SQL = "Select * FROM opportunity WHERE id = " + id;
        return jdbcTemplate.queryForObject(SQL,new OpportunityMapper());
    }
}
