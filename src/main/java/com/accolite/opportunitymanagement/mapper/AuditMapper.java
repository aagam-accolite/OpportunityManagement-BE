package com.accolite.opportunitymanagement.mapper;

import com.accolite.opportunitymanagement.model.Audit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditMapper implements RowMapper<Audit> {

    @Override
    public Audit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Audit audit = new Audit();
        audit.setId(rs.getInt("id"));
        audit.setDate(rs.getDate("date"));
        audit.setUserName(rs.getString("user_name"));
        audit.setUserEmail(rs.getString("user_email"));
        audit.setOperation(rs.getString("operation"));
        audit.setNewValues(rs.getString("new_values"));
        audit.setOldValues(rs.getString("old_values"));
        return audit;
    }
}
