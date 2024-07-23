package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.RecurringBill;

import java.util.List;
import java.util.Optional;

public class RecurringBillService implements ExpenseOperations<RecurringBill>
{

    @Override
    public List<RecurringBill> findBySupplier(String supplier)
    {
        return List.of();
    }

    @Override
    public Optional<RecurringBill> add(RecurringBill item)
    {
        return Optional.empty();
    }

    @Override
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public List<RecurringBill> findAll()
    {
        return List.of();
    }
}
