package cz.trixit.demo.project.candidate.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cz.trixit.demo.project.candidate.api.dto.Response;
import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;

@Component
public class ExchangeRateApi {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${candidate.testing.apiUrl}")
	private String currentApi;

	@Value("${candidate.testing.baseCurrency}")
	private SupportedCurrencyType baseCurrency;

	public Response getRates() {
		return getRates(LocalDate.now());
	}
	
	public Response getRates(LocalDate date) {
		// Headers
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		// Entity
		HttpEntity<?> entity = new HttpEntity<>(headers);
		// Params
		Map<String, Object> params =  new HashMap<>();
		params.put("date", date.format(DateTimeFormatter.ISO_DATE));
		params.put("base", this.baseCurrency);
		return this.restTemplate.exchange(
				currentApi,
				HttpMethod.GET,
				entity,
				Response.class,
				params
		).getBody();

	}
	
	
}
