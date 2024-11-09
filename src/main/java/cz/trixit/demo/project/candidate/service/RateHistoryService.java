package cz.trixit.demo.project.candidate.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.trixit.demo.project.candidate.api.ExchangeRateApi;
import cz.trixit.demo.project.candidate.api.dto.Response;
import cz.trixit.demo.project.candidate.dao.RateHistoryRepository;
import cz.trixit.demo.project.candidate.model.RateHistoryEntity;
import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;

@Service
@Transactional
public class RateHistoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(RateHistoryService.class);
	
	@Autowired
	private ExchangeRateApi exchangeRateApi;

	@Autowired
	private RateHistoryRepository rateHistoryRepository;
	
	@Value("${candidate.testing.dataFrom}")
	private LocalDate historyFrom;
	
	
	public void reloadAllRates() {
		logger.info("Reloading all rates");
		rateHistoryRepository.deleteAllRates();
		logger.info("Old rates have been deleted");
		AtomicInteger index = new AtomicInteger();
		AtomicInteger inserted = new AtomicInteger();
		this.historyFrom.datesUntil(LocalDate.now())
				.map(it -> {
					if (index.getAndIncrement() % 50 == 0) {
						logger.info("PROGRESS: Loading data for {}", it);
					}
					return this.exchangeRateApi.getRates(it);					
				})
				.distinct()
				.forEach(resp ->
					resp.getRates().entrySet().stream()
						.filter(this::filterRateEnty)
						.forEach(it -> {
							inserted.incrementAndGet();
							this.rateHistoryRepository.save(RateHistoryEntity.builder()
									.currency(SupportedCurrencyType.valueOf(it.getKey()))
									.date(resp.getDate())
									.value(it.getValue()).build());
						})
				);
		logger.info("Entries persisted: {}", inserted.get());
	}

	@Scheduled(cron = "0 0 12 * * * ?")
	public void loadCurrentRates() {
		logger.info("Loading actual rates");
		Response response = this.exchangeRateApi.getRates();
		List<RateHistoryEntity> entities = rateHistoryRepository.findByDate(response.getDate());
		List<RateHistoryEntity> newEntries = response.getRates().entrySet()
				.stream()
				.filter(this::filterRateEnty)
				.filter(it -> entities.stream().noneMatch(entity -> entity.getCurrency() == SupportedCurrencyType.valueOf(it.getKey())))
				.map(it -> RateHistoryEntity.builder()
						.currency(SupportedCurrencyType.valueOf(it.getKey()))
						.date(response.getDate())
						.value(it.getValue())
						.build()
				).toList();
		this.rateHistoryRepository.saveAll(newEntries);
	}
	
	private boolean filterRateEnty(Entry<String, BigDecimal> entry) {
		return SupportedCurrencyType.valueOfSafe(entry.getKey()) != null;
	}

}
