package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Expense;

public interface ExpenseOperations<T extends Expense> extends Operations<T>
{
    Iterable<Expense> findBySupplier(String supplier);
}
