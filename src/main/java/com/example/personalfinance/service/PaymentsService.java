package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Payment;

import java.util.List;
import java.util.Optional;

public class PaymentsService implements ExpenseOperations<Payment>
{

    @Override
    public List<Payment> findBySupplier(String supplier)
    {
        return List.of();
    }

    @Override
    public Optional<Payment> add(Payment item)
    {
        return Optional.empty();
    }

    @Override
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public List<Payment> findAll()
    {
        return List.of();
    }
}
