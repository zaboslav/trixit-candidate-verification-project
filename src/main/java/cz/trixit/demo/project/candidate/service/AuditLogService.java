package cz.trixit.demo.project.candidate.service;

import cz.trixit.demo.project.candidate.model.enums.AuditLogEventType;

public interface AuditLogService {
	
	void write(AuditLogEventType event, Object...objects);

	void writeError(AuditLogEventType event, Exception e, Object...objects);
}
