package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Expense;

import java.util.List;

public interface ExpenseOperations<T extends Expense> extends Operations<T>
{
    List<T> findBySupplier(String supplier);
}
