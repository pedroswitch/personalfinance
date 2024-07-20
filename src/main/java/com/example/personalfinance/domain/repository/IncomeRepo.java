package com.example.personalfinance.domain.repository;

import com.example.personalfinance.ddd.Repository;
import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.valueobjects.IncomeId;

public interface IncomeRepo extends Repository<IncomeId, Income>
{
}
