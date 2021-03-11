package com.accolite.opportunitymanagement.service.Impl;

import com.accolite.opportunitymanagement.mapper.AuditMapper;
import com.accolite.opportunitymanagement.model.Audit;
import com.accolite.opportunitymanagement.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository("AuditService")
@Transactional
public class AuditServiceImpl implements AuditService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Audit> getAllAudit() {
        String SQL = "Select * from Audit";
        List<Audit> auditList = jdbcTemplate.query(SQL,new AuditMapper());
        return auditList;
    }

    @Override
    public int insertAudit(Audit audit) {
        String SQL = "INSERT INTO audit (date, user_name, user_email, operation, old_values, new_values) VALUES (?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(SQL,new Object[]{
                audit.getDate(),
                audit.getUserName(),
                audit.getUserEmail(),
                audit.getOperation(),
                audit.getOldValues(),
                audit.getNewValues()
        });
        return result;
    }
}
