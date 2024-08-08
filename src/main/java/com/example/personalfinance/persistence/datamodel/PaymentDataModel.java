package com.example.personalfinance.persistence.datamodel;

import com.example.personalfinance.domain.payment.Payment;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name="payment")
@Getter
public class PaymentDataModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private int version;

    @ManyToOne
    @JoinColumn(name = "expenseid", nullable = false)
    private ExpenseDataModel expense;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    public PaymentDataModel()
    {
    }

    public PaymentDataModel(Payment payment, Optional<ExpenseDataModel> expenseDataModel)
    {
        this.id = payment.identity().getId();
        if (expenseDataModel.isPresent()) this.expense = expenseDataModel.get();
        this.paymentDate = payment.getPaymentDate().getDate();
    }
}
