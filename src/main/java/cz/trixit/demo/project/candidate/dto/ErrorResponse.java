package cz.trixit.demo.project.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ErrorResponse {
	@Getter
	private String title;
	@Getter
	private String description;
	@Getter
	private String path;
}
