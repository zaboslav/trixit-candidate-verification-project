package cz.trixit.demo.project.candidate.exception;

public class BadRequestException extends ApiResponseException {

	private static final long serialVersionUID = -7465778268963721996L;

	public BadRequestException(String title, String description) {
		super(400, title, description);
	}
}
