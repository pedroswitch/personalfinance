package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.InvoiceRegistration;

import java.util.List;

public class InvoiceRegistrationService implements ExpenseOperations<InvoiceRegistration>
{
    public InvoiceRegistration add(InvoiceRegistration item)
    {
        return null;
    }

    @Override
    public Iterable<InvoiceRegistration> findBySupplier(String supplier)
    {
        return List.of();
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public Iterable<InvoiceRegistration> findAll()
    {
        return List.of();
    }
}
