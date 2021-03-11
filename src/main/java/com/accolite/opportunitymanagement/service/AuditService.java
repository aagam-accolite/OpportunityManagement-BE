package com.accolite.opportunitymanagement.service;

import com.accolite.opportunitymanagement.model.Audit;

import java.util.List;

public interface AuditService {

    public List<Audit> getAllAudit();

    public int insertAudit(Audit audit);
}
