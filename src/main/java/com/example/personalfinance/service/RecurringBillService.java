package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.RecurringBill;

import java.util.List;
import java.util.Optional;

public class RecurringBillService implements ExpenseOperations<RecurringBill>
{
    public RecurringBill add(RecurringBill item)
    {
        return null;
    }

    @Override
    public Iterable<RecurringBill> findBySupplier(String supplier)
    {
        return List.of();
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public Iterable<RecurringBill> findAll()
    {
        return List.of();
    }
}
