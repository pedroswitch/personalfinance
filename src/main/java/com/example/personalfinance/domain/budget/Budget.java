package com.example.personalfinance.domain.budget;

import com.example.personalfinance.ddd.AggregateRoot;
import com.example.personalfinance.domain.valueobjects.BudgetCategory;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.domain.valueobjects.Values;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Budget implements AggregateRoot<BudgetId>
{
    private final BudgetId id;
    private final BudgetCategory category;
    private final Values value;

    @Override
    public BudgetId identity()
    {
        return this.id;
    }

    @Override
    public boolean sameAs(Object object)
    {
        if ( this == object ) return true;
        if ( object == null || getClass() != object.getClass() ) return false;

        Budget that = (Budget) object;

        if (!getId().equals(that.getId())) return false;
        if (!getCategory().equals(that.getCategory())) return false;
        return (getValue().equals(that.getValue()));
    }
}
