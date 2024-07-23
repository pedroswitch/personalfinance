package com.example.personalfinance.service;

import com.example.personalfinance.domain.budget.Budget;

import java.util.List;
import java.util.Optional;

public class BudgetService implements BudgetOperations<Budget>
{

    @Override
    public List<Budget> findByCategory(String category)
    {
        return List.of();
    }

    @Override
    public Optional<Budget> add(Budget item)
    {
        return Optional.empty();
    }

    @Override
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public List<Budget> findAll()
    {
        return List.of();
    }
}
