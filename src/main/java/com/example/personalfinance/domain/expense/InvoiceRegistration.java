package com.example.personalfinance.domain.expense;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.Getter;

@Getter
public class InvoiceRegistration extends Expense
{
    private final Date date;

    public InvoiceRegistration(ExpenseId id, Date date, ExpenseSupplier supplier, ExpenseCategory category, Values value, ExpenseStatus status)
    {
        super(id, supplier, category, value, status);
        this.date = date;
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
        if (!getCategory().equals(that.getCategory())) return false;
        if (!getValue().equals(that.getValue())) return false;
        return (!getStatus().equals(that.getStatus()));
    }
}
