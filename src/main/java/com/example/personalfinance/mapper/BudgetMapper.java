package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.budget.Budget;
import com.example.personalfinance.domain.budget.BudgetFactory;
import com.example.personalfinance.domain.valueobjects.BudgetCategory;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.domain.valueobjects.Values;
import com.example.personalfinance.persistence.BudgetDataModel;

import java.util.ArrayList;
import java.util.List;

public class BudgetMapper
{
    public static Budget budgetDataModelToDomain(BudgetDataModel budgetDataModel)
    {
        BudgetFactory budgetFactory = new BudgetFactory();
        BudgetId id = new BudgetId(budgetDataModel.getId());
        BudgetCategory category = new BudgetCategory(budgetDataModel.getCategory());
        Values value = new Values(budgetDataModel.getValue());
        return budgetFactory.createBudget(id, category, value);
    }

    public static Iterable<Budget> budgetsDataModelToDomain(Iterable<BudgetDataModel> budgetDataModels)
    {
        List<Budget> budgets = new ArrayList<>();
        budgetDataModels.forEach(
                dataModel -> budgets.add(budgetDataModelToDomain(dataModel))
        );
        return budgets;
    }
}
