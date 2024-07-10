package com.example.personalfinance.domain.income;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.Getter;

@Getter
public class Salary extends Income
{
    private final EmployerName name;

    public Salary(IncomeId id, Date date, Values value, EmployerName name)
    {
        super(id, date, value);
        this.name = name;
    }

    @Override
    public boolean sameAs(Object object)
    {
        if ( this == object ) return true;
        if ( object == null || getClass() != object.getClass() ) return false;

        Salary that = (Salary) object;

        if (!getId().equals(that.getId())) return false;
        if (!getDate().equals(that.getDate())) return false;
        if (!getName().equals(that.getName())) return false;
        return (!getValue().equals(that.getValue()));
    }
}
