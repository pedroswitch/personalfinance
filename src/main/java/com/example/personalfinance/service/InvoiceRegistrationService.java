package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.ExpenseFactory;
import com.example.personalfinance.domain.repository.ExpenseRepo;
import com.example.personalfinance.domain.valueobjects.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

public class InvoiceRegistrationService implements ExpenseOperations<Expense>
{
    private final ExpenseRepo expenseRepo;
    private final ExpenseFactory expenseFactory;

    public InvoiceRegistrationService(@Qualifier("expenseRepoSpringData") ExpenseRepo expenseRepo, ExpenseFactory expenseFactory)
    {
        this.expenseRepo = expenseRepo;
        this.expenseFactory = expenseFactory;
    }

    public Expense add(ExpenseId id, ExpenseType type, Date date, ExpenseSupplier supplier, InvoiceNumber number, ExpenseCategory category, Values value, ExpenseStatus status)
    {
        Expense invoiceToSave = expenseFactory.createExpense(id, type, supplier, category, value, number, date, status);
        return expenseRepo.save(invoiceToSave);
    }

    @Override
    public Iterable<Expense> findBySupplier(String supplier)
    {
        Optional<ExpenseSupplier> invoiceSupplier = expenseFactory.createExpenseSupplier(supplier);
        return invoiceSupplier.map(value -> expenseRepo.findBySupplier(value.getName()))
                .orElseGet(List::of);
    }

    @Override
    public boolean delete(long id)
    {
        return expenseRepo.delete(id);
    }

    @Override
    public Iterable<Expense> findAll()
    {
        return this.expenseRepo.findAll();
    }
}
