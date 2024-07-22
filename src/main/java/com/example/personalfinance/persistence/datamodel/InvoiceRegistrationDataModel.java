package com.example.personalfinance.persistence.datamodel;

import com.example.personalfinance.domain.expense.Expense;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("invoice_registration")
@Getter
public class InvoiceRegistrationDataModel extends com.example.personalfinance.persistence.datamodel.ExpenseDataModel
{
    @Column(name = "number")
    private String number;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private boolean status;

    public InvoiceRegistrationDataModel()
    {
    }

    public InvoiceRegistrationDataModel(Expense expense, String number, LocalDate date, boolean status)
    {
        super(expense);
        this.number = number;
        this.date = date;
        this.status = status;
    }
}
