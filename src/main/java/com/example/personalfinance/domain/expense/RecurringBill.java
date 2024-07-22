package com.example.personalfinance.domain.expense;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@Getter
public class RecurringBill extends Expense
{
    private final Date initialDate;
    private final Date finalDate;

    public RecurringBill(ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, Date initialDate, Date finalDate)
    {
        super(id, type, supplier, category, value);
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    @Override
    public boolean sameAs(Object object)
    {
        if ( this == object ) return true;
        if ( object == null || getClass() != object.getClass() ) return false;

        RecurringBill that = (RecurringBill) object;

        if (!getId().equals(that.getId())) return false;
        if (!getInitialDate().equals(that.getInitialDate())) return false;
        if (!getFinalDate().equals(that.getFinalDate())) return false;
        if (!getSupplier().equals(that.getSupplier())) return false;
        if (!getCategory().equals(that.getCategory())) return false;
        return (getValue().equals(that.getValue()));
    }
}
