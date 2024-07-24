package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Payment;

import java.util.List;

public class PaymentsService implements ExpenseOperations<Payment>
{
    public Payment add(Payment item)
    {
        return null;
    }

    @Override
    public Iterable<Payment> findBySupplier(String supplier)
    {
        return List.of();
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public Iterable<Payment> findAll()
    {
        return List.of();
    }
}
