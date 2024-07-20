package com.example.personalfinance.domain.budget;

import com.example.personalfinance.domain.valueobjects.BudgetCategory;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.domain.valueobjects.Values;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetFactory
{
    public Budget createBudget(BudgetId id, BudgetCategory category, Values value)
    {
        return new Budget(id, category, value);
    }

    public Optional<BudgetId> createBudgetId(long id)
    {
        try {
            return Optional.of(new BudgetId(id));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<BudgetCategory> createBudgetCategory(String category) {
        try {
            return Optional.of(new BudgetCategory(category));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<Values> createValue(double value) {
        try {
            return Optional.of(new Values(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
