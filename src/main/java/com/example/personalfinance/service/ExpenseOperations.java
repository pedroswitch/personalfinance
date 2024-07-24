package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Expense;

import java.util.List;

public interface ExpenseOperations<T extends Expense> extends Operations<T>
{
    Iterable<T> findBySupplier(String supplier);
}
