package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.service.Impl.AuditServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class TrendsServiceTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    AuditServiceImpl trendsServiceImpl;


}
