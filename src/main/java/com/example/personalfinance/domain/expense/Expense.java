package com.example.personalfinance.domain.expense;

import com.example.personalfinance.ddd.AggregateRoot;
import com.example.personalfinance.domain.valueobjects.*;
import lombok.Data;

@Data
public abstract class Expense implements AggregateRoot<ExpenseId>
{
    private final ExpenseId id;
    private final ExpenseSupplier supplier;
    private final ExpenseCategory category;
    private final Values value;
    private final ExpenseStatus status;

    public ExpenseId identity() {return this.id;}
}
