package cz.trixit.demo.project.candidate.exception;

public class ConflictException extends ApiResponseException {

	private static final long serialVersionUID = 1681609723721389566L;

	public ConflictException(String title, String description) {
		super(409, title, description);
	}

}
