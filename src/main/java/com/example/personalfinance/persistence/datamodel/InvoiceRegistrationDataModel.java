package com.example.personalfinance.persistence;

import com.example.personalfinance.domain.expense.Expense;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("invoice_registration")
@Getter
public class InvoiceRegistrationDataModel extends com.example.personalfinance.persistence.ExpenseDataModel
{
    @Column(name = "number")
    private int number;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private boolean status;

    public InvoiceRegistrationDataModel()
    {
    }

    public InvoiceRegistrationDataModel(Expense expense, int number, LocalDate date, boolean status)
    {
        super(expense);
        this.number = number;
        this.date = date;
        this.status = status;
    }
}
