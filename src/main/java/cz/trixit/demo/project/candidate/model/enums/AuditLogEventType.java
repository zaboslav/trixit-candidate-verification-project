package cz.trixit.demo.project.candidate.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AuditLogEventType {
	CUSTOMER_CREATED("CUSTOMER_CREATED", "Customer {} has been created."), 
	CUSTOMER_DELETED("CUSTOMER_DELETED", "Customer {} has been deleted."),
	CUSTOMER_UPDATED("CUSTOMER_UPDATED", "Customer {} has been updated to {}"),
	CURRENCY_ADDED("CURRENCY_ADDED", "Adding a new currency {} for customer {}."),
	CURRENCY_REMOVED("CURRENCY_REMOVED", "Removing currency {} from customer {}."),
	CONVERSION("CONVERSION", "Conversion from {} to {}")
	;
	
	@Getter
	private String type;
	
	@Getter
	private String message;
	
	public String formatMessage(Object... objects) {
		// TODO : Implements message formatting in log4j style where {} is replaced with value 
		return null;
	}
}
