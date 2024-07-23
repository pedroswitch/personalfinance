package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.InvoiceRegistration;

import java.util.List;
import java.util.Optional;

public class InvoiceRegistrationService implements ExpenseOperations<InvoiceRegistration>
{

    @Override
    public List<InvoiceRegistration> findBySupplier(String supplier)
    {
        return List.of();
    }

    @Override
    public Optional<InvoiceRegistration> add(InvoiceRegistration item)
    {
        return Optional.empty();
    }

    @Override
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public List<InvoiceRegistration> findAll()
    {
        return List.of();
    }
}
