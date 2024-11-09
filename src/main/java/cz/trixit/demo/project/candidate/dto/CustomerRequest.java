package cz.trixit.demo.project.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequest {

	@NotEmpty
	@Size(max = 255)
	@Schema(description = "Customer name, max length 255 character", nullable = false)
	private String customerName;
}
