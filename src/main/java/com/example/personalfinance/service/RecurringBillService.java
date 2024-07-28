package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.ExpenseFactory;
import com.example.personalfinance.domain.repository.ExpenseRepo;
import com.example.personalfinance.domain.valueobjects.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class RecurringBillService implements ExpenseOperations<Expense>
{
    private final ExpenseRepo expenseRepo;
    private final ExpenseFactory expenseFactory;

    public RecurringBillService(@Qualifier("expenseRepoSpringData") ExpenseRepo expenseRepo, ExpenseFactory expenseFactory)
    {
        this.expenseRepo = expenseRepo;
        this.expenseFactory = expenseFactory;
    }

    public Expense add(ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, Date initialDate, Date finalDate)
    {
        Expense recurringBillToSave = expenseFactory.createExpense(id, type, supplier, category, value, initialDate, finalDate);
        return expenseRepo.save(recurringBillToSave);
    }

    @Override
    public Iterable<Expense> findBySupplier(String supplier)
    {
        Optional<ExpenseSupplier> recurringBillSupplier = expenseFactory.createExpenseSupplier(supplier);
        return recurringBillSupplier.map(value -> expenseRepo.findBySupplier(value.getName()))
                .orElseGet(Collections::emptyList);
    }

    @Override
    public boolean delete(long id)
    {
        return expenseRepo.delete(id);
    }

    @Override
    public Iterable<Expense> findAll()
    {
        return expenseRepo.findAll();
    }
}
