package com.example.personalfinance.domain.repository;

import com.example.personalfinance.ddd.Repository;
import com.example.personalfinance.domain.budget.Budget;
import com.example.personalfinance.domain.valueobjects.BudgetId;

public interface BudgetRepo extends Repository<BudgetId, Budget>
{
}
