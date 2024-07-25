package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.Payment;

import java.util.List;

public class PaymentsService implements ExpenseOperations<Expense>
{
    public Payment add(Payment item)
    {
        return null;
    }

    @Override
    public Iterable<Expense> findBySupplier(String supplier)
    {
        return List.of();
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public Iterable<Expense> findAll()
    {
        return List.of();
    }
}
