package com.example.personalfinance.service;

import com.example.personalfinance.domain.budget.Budget;
import com.example.personalfinance.domain.budget.BudgetFactory;
import com.example.personalfinance.domain.repository.BudgetRepo;
import com.example.personalfinance.domain.valueobjects.BudgetCategory;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.domain.valueobjects.Values;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
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
    public Iterable<Budget> findByCategory(String category)
    {
        Optional<BudgetCategory> budgetCategory = budgetFactory.createBudgetCategory(category);
        return budgetCategory.map(value -> budgetRepo.findByCategory(value.getCategory()))
                .orElseGet(Collections::emptyList);
    }

    public boolean delete(long id) {
        return budgetRepo.delete(id);
    }

    @Override
    public Iterable<Budget> findAll()
    {
        return budgetRepo.findAll();
    }

    public Budget findById(long id)
    {
        Optional<BudgetId> budgetId = budgetFactory.createBudgetId(id);

        if (budgetId.isPresent()) {
            Optional<Budget> budget = budgetRepo.findById(budgetId.get());
            return budget.orElse(null);
        }

        return null;
    }
}
