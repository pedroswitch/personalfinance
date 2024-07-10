package com.example.personalfinance.ddd;

import com.example.personalfinance.domain.valueobjects.IncomeId;

public interface DomainEntity<ID extends DomainId> {

	ID identity();

	boolean sameAs(Object object);
}
