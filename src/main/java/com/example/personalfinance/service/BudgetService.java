package com.example.personalfinance.service;

import com.example.personalfinance.domain.budget.Budget;
import com.example.personalfinance.domain.budget.BudgetFactory;
import com.example.personalfinance.domain.repository.BudgetRepo;
import com.example.personalfinance.domain.valueobjects.BudgetCategory;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.domain.valueobjects.Values;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService implements BudgetOperations<Budget>
{
    private final BudgetRepo budgetRepo;
    private final BudgetFactory budgetFactory;

    public BudgetService(@Qualifier("budgetRepoSpringData") BudgetRepo budgetRepo, BudgetFactory budgetFactory)
    {
        this.budgetRepo = budgetRepo;
        this.budgetFactory = budgetFactory;
    }

    public Budget add(BudgetId id, BudgetCategory category, Values value)
    {
        Budget budgetToSave = budgetFactory.createBudget(id, category, value);
        return budgetRepo.save(budgetToSave);
    }

    @Override
    public Iterable<Budget> findAllByCategory(String category)
    {
        Optional<BudgetCategory> budgetCategory = budgetFactory.createBudgetCategory(category);
        return budgetCategory.map(value -> this.budgetRepo.findAllByCategory(value.getCategory()))
                .orElseGet(List::of);
    }

    public boolean delete(long id) {
        return budgetRepo.delete(id);
    }

    @Override
    public Iterable<Budget> findAll()
    {
        return this.budgetRepo.findAll();
    }
}
