package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.Income;

public interface IncomeOperations<T extends Income> extends Operations<T>
{
    Iterable<Income> findByName(String name);
}
