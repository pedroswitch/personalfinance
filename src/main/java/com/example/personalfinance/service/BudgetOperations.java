package com.example.personalfinance.service;

import com.example.personalfinance.domain.budget.Budget;

import java.util.List;

public interface BudgetOperations<T extends Budget> extends Operations<T> {
    List<T> findByCategory(String category);
}
