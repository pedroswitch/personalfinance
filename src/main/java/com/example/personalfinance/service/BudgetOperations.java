package com.example.personalfinance.service;

import com.example.personalfinance.domain.budget.Budget;

public interface BudgetOperations<T extends Budget> extends Operations<T> {
    Iterable<T> findAllByCategory(String category);
}
