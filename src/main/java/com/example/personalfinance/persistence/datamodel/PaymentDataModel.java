package com.example.personalfinance.persistence.datamodel;

import com.example.personalfinance.domain.expense.Expense;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("payment")
@Getter
public class PaymentDataModel extends com.example.personalfinance.persistence.datamodel.ExpenseDataModel
{
    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "invoice_number")
    private int invoiceNumber;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    public PaymentDataModel()
    {
    }

    public PaymentDataModel(Expense expense, LocalDate invoiceDate, int invoiceNumber, LocalDate paymentDate)
    {
        super(expense);
        this.invoiceDate = invoiceDate;
        this.invoiceNumber = invoiceNumber;
        this.paymentDate = paymentDate;
    }
}
