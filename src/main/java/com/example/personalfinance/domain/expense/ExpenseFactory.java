package com.example.personalfinance.domain.expense;

import com.example.personalfinance.domain.valueobjects.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Component
public class ExpenseFactory
{

    public static final String PATH = "com.example.personalfinance.domain.expense.";

    // InvoiceRegistration
    public Expense createExpense(ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, InvoiceNumber number, Date date, ExpenseStatus status)
    {
        try {
            String fullPath = PATH + type.getType();

            return (Expense) Class.forName(fullPath)
                    .getConstructor(ExpenseId.class, ExpenseType.class, Date.class, ExpenseSupplier.class, InvoiceNumber.class, ExpenseCategory.class, Values.class, ExpenseStatus.class)
                    .newInstance(id, type, date, supplier, number, category, value, status);
        } catch (ClassNotFoundException | InstantiationException |
                 NoSuchMethodException | NullPointerException |
                 InvocationTargetException | IllegalArgumentException |
                 IllegalAccessException e) {

            e.printStackTrace();

            return null;
        }
    }

    // RecurringBill
    public Expense createExpense(ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, Date initialDate, Date finalDate)
    {
        try {
            String fullPath = PATH + type.getType();

            return (Expense) Class.forName(fullPath)
                    .getConstructor(ExpenseId.class, ExpenseType.class, ExpenseSupplier.class, ExpenseCategory.class, Values.class, Date.class, Date.class)
                    .newInstance(id, type, supplier, category, value, initialDate, finalDate);
        } catch (ClassNotFoundException | InstantiationException |
                 NoSuchMethodException | NullPointerException |
                 InvocationTargetException | IllegalArgumentException |
                 IllegalAccessException e) {

            e.printStackTrace();

            return null;
        }
    }

    public Optional<ExpenseId> createExpenseId(long id)
    {
        try {
            return Optional.of(new ExpenseId(id));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<ExpenseType> createExpenseType(String type) {
        try {
            return Optional.of(new ExpenseType(type));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<ExpenseSupplier> createExpenseSupplier(String supplier) {
        try {
            return Optional.of(new ExpenseSupplier(supplier));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<ExpenseCategory> createExpenseCategory(String category) {
        try {
            return Optional.of(new ExpenseCategory(category));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<Values> createValue(double value) {
        try {
            return Optional.of(new Values(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
