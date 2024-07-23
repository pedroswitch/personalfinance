package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.Income;

import java.util.List;

public interface IncomeOperations<T extends Income> extends Operations<T>
{
    List<T> findByName(String name);
}
