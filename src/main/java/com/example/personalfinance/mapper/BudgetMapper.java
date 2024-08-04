package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.budget.Budget;
import com.example.personalfinance.domain.budget.BudgetFactory;
import com.example.personalfinance.domain.valueobjects.BudgetCategory;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.domain.valueobjects.Values;
import com.example.personalfinance.persistence.datamodel.BudgetDataModel;

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

    public static BudgetDto budgetToDto(Budget budget)
    {
        return new BudgetDto(budget.getId().getId(), budget.getCategory().getCategory(), budget.getValue().getValue());
    }

    public static Iterable<BudgetDto> budgetListToDto(Iterable<Budget> budgets)
    {
        List<BudgetDto> budgetListDto = new ArrayList<>();

        budgets.forEach(budget -> {
            BudgetDto budgetDto = budgetToDto(budget);
            budgetListDto.add(budgetDto);
        });

        return budgetListDto;
    }

    public static Budget dtoToBudget(BudgetDto dto)
    {
        BudgetFactory budgetFactory = new BudgetFactory();
        BudgetId id = new BudgetId(dto.id);
        BudgetCategory category = new BudgetCategory(dto.category);
        Values value = new Values(dto.value);
        return budgetFactory.createBudget(id, category, value);
    }
}
