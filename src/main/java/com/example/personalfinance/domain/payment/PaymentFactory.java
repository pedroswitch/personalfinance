package com.example.personalfinance.domain.payment;

import com.example.personalfinance.domain.valueobjects.Date;
import com.example.personalfinance.domain.valueobjects.ExpenseId;
import com.example.personalfinance.domain.valueobjects.PaymentId;

import java.util.Optional;

public class PaymentFactory
{
    public Payment createPayment(PaymentId id, ExpenseId expenseId, Date paymentDate)
    {
        return new Payment(id, expenseId, paymentDate);
    }

    public Optional<PaymentId> createPaymentId(long id)
    {
        try {
            return Optional.of(new PaymentId(id));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
