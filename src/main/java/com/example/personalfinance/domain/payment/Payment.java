package com.example.personalfinance.domain.payment;

import com.example.personalfinance.ddd.AggregateRoot;
import com.example.personalfinance.domain.valueobjects.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Payment implements AggregateRoot<PaymentId>
{
    private final PaymentId id;
    private final ExpenseId expenseId;
    private final Date paymentDate;

    @Override
    public PaymentId identity()
    {
        return this.id;
    }

    @Override
    public boolean sameAs(Object object)
    {
        if ( this == object ) return true;
        if ( object == null || getClass() != object.getClass() ) return false;

        Payment that = (Payment) object;

        if (!getId().equals(that.getId())) return false;
        if (!getExpenseId().equals(that.getExpenseId())) return false;
        return (getPaymentDate().equals(that.getPaymentDate()));
    }
}
