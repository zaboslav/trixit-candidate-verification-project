package cz.trixit.demo.project.candidate.service.impl;

import org.springframework.stereotype.Component;

import cz.trixit.demo.project.candidate.model.enums.AuditLogEventType;
import cz.trixit.demo.project.candidate.service.AuditLogService;

@Component
/**
 * This component is here only for development purpose, implements own class from scratch.
 */
public class DummyAuditLogService implements AuditLogService {

	@Override
	public void write(AuditLogEventType event, Object... objects) {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeError(AuditLogEventType event, Exception e, Object... objects) {
		// TODO Auto-generated method stub
	}

}
