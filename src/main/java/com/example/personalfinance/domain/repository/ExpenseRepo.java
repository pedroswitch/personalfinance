package com.example.personalfinance.domain.repository;

import com.example.personalfinance.ddd.Repository;
import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.valueobjects.ExpenseId;

public interface ExpenseRepo extends Repository<ExpenseId, Expense>
{
}
