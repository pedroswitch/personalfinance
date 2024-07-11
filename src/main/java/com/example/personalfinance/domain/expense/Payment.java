package com.example.personalfinance.domain.expense;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@Getter
public class Payment extends Expense
{
    private final Date date;
    private final InvoiceNumber number;
    private final Date paymentDate;

    public Payment(ExpenseId id, ExpenseSupplier supplier, ExpenseCategory category, Values value, Date date, InvoiceNumber number, Date paymentDate)
    {
        super(id, supplier, category, value);
        this.date = date;
        this.number = number;
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean sameAs(Object object)
    {
        if ( this == object ) return true;
        if ( object == null || getClass() != object.getClass() ) return false;

        Payment that = (Payment) object;

        if (!getId().equals(that.getId())) return false;
        if (!getDate().equals(that.getDate())) return false;
        if (!getSupplier().equals(that.getSupplier())) return false;
        if (!getNumber().equals(that.getNumber())) return false;
        if (!getCategory().equals(that.getCategory())) return false;
        if (!getValue().equals(that.getValue())) return false;
        return (getPaymentDate().equals(that.getPaymentDate()));
    }
}
