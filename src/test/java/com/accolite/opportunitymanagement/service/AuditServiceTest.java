package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.mapper.AuditMapper;
import com.accolite.opportunitymanagement.mapper.OpportunityMapper;
import com.accolite.opportunitymanagement.model.Audit;
import com.accolite.opportunitymanagement.model.Opportunity;
import com.accolite.opportunitymanagement.service.Impl.AuditServiceImpl;
import com.accolite.opportunitymanagement.service.Impl.OpportunityServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.ArrayList;

@SpringBootTest
public class AuditServiceTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    AuditServiceImpl auditServiceImpl;

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
    public void getAllTest(){
        ArrayList<Audit> auditArrayList = new ArrayList<>();
        Audit audit = setAuditObj();
        auditArrayList.add(audit);
        Mockito.when(jdbcTemplate.query(
                Mockito.anyString(),
                Mockito.any(AuditMapper.class))).thenReturn(auditArrayList);
        ArrayList<Audit> auditArrayList1 = (ArrayList<Audit>) auditServiceImpl.getAllAudit();
        Assert.assertEquals(auditArrayList1.size(),auditArrayList1.size());
    }

    @Test
    public void insertTest() {
        int expectedVal = 1;
        Audit audit = setAuditObj();
        Mockito.when(jdbcTemplate.update(
                Mockito.anyString(),
                (Object[]) Mockito.any()
        )).thenReturn(expectedVal);
        int resultVal = auditServiceImpl.insertAudit(audit);
        Assert.assertEquals(expectedVal, resultVal);
    }
}
