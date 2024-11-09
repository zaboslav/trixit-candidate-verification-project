package cz.trixit.demo.project.candidate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import cz.trixit.demo.project.candidate.dto.CustomerDetailResponse;
import cz.trixit.demo.project.candidate.dto.CustomerResponse;
import cz.trixit.demo.project.candidate.dto.WatchedCurrencyResponse;
import cz.trixit.demo.project.candidate.model.CustomerEntity;
import cz.trixit.demo.project.candidate.model.WatchedCurrencyEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface CustomerMapper {

	@Mapping(target = "customerName", source = "name")
	@Mapping(target = "createdAt", source = "created")
	CustomerResponse mapCustomer(CustomerEntity entity);

	@Mapping(target = "customerName", source = "name")
	@Mapping(target = "createdAt", source = "created")
	CustomerDetailResponse mapCustomerDetail(CustomerEntity customerentity);

	@Mapping(target = "watchedFrom", source="created")
	WatchedCurrencyResponse maWatchedCurrencyResponse(WatchedCurrencyEntity entity);
}
