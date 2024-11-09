package cz.trixit.demo.project.candidate.rest.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.trixit.demo.project.candidate.rest.RateHistoryController;
import cz.trixit.demo.project.candidate.service.RateHistoryService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/rates")
@AllArgsConstructor
public class RateHistoryControllerImpl implements RateHistoryController {
	
	private RateHistoryService rateHistoryService;

	@Override
	@GetMapping("/rates")
	public ResponseEntity<Void> reloadRates() {
		rateHistoryService.reloadAllRates();
		return ResponseEntity.noContent().build();
	}

	
}
