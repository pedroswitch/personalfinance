package com.example.personalfinance.persistence.datamodel;

import com.example.personalfinance.domain.expense.Expense;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("recurring_bill")
@Getter
public class RecurringBillDataModel extends com.example.personalfinance.persistence.ExpenseDataModel
{
    @Column(name = "initial_date")
    private LocalDate initialDate;

    @Column(name = "final_date")
    private LocalDate finalDate;

    public RecurringBillDataModel()
    {
    }

    public RecurringBillDataModel(Expense expense, LocalDate initialDate, LocalDate finalDate)
    {
        super(expense);
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }
}
