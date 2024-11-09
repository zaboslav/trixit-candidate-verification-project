package cz.trixit.demo.project.candidate.model.enums;

import java.util.Arrays;

public enum SupportedCurrencyType {
	EUR,
	USD,
	JPY,
	CYP,
	CZK,
	DKK,
	EEK,
	GBP,
	HUF,
	LTL,
	LVL,
	MTL,
	PLN,
	ROL,
	SEK,
	SIT,
	SKK,
	CHF,
	ISK,
	NOK,
	TRL,
	AUD,
	CAD,
	HKD,
	KRW,
	NZD,
	SGD,
	ZAR;
	
	public static SupportedCurrencyType valueOfSafe(String value) {
		return Arrays.stream(SupportedCurrencyType.values()).filter(it -> it.name().equals(value)).findFirst().orElse(null);
	}
}
