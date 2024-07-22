package com.example.personalfinance.domain.expense;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@Getter
public class InvoiceRegistration extends Expense
{
    private final Date date;
    private final InvoiceNumber number;
    private final ExpenseStatus status;

    public InvoiceRegistration(ExpenseId id, ExpenseType type, Date date, ExpenseSupplier supplier, InvoiceNumber number, ExpenseCategory category, Values value, ExpenseStatus status)
    {
        super(id, type, supplier, category, value);
        this.number = number;
        this.date = date;
        this.status = status;
    }

    @Override
    public boolean sameAs(Object object)
    {
        if ( this == object ) return true;
        if ( object == null || getClass() != object.getClass() ) return false;

        InvoiceRegistration that = (InvoiceRegistration) object;

        if (!getId().equals(that.getId())) return false;
        if (!getDate().equals(that.getDate())) return false;
        if (!getSupplier().equals(that.getSupplier())) return false;
        if (!getNumber().equals(that.getNumber())) return false;
        if (!getCategory().equals(that.getCategory())) return false;
        if (!getValue().equals(that.getValue())) return false;
        return (getStatus().equals(that.getStatus()));
    }
}
