package cz.trixit.demo.project.candidate.exception;

import lombok.Getter;

public class ApiResponseException extends RuntimeException {
	
	private static final long serialVersionUID = 8984828735181483784L;

	public ApiResponseException(int httpStatusCode, String title, String description) {
		super(httpStatusCode + ": " + title + " -> " + description);
		this.httpStatusCode = httpStatusCode;
		this.title = title;
		this.description = description;
	}
	
	@Getter
	private final int httpStatusCode;
	@Getter
	private final String title;
	@Getter
	private final String description;
}
