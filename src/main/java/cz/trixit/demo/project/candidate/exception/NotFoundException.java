package cz.trixit.demo.project.candidate.exception;

public class NotFoundException extends ApiResponseException {

	private static final long serialVersionUID = -3614028884478733772L;

	public NotFoundException(String title, String description) {
		super(404, title, description);
	}


}
