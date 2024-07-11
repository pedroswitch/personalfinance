package com.example.personalfinance.domain.income;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.Getter;

@Getter
public class SideGig extends Income
{

    private final SideGigType type;
    private final SideGigName name;

    public SideGig(IncomeId id, Date date, Values value, SideGigType type, SideGigName name)
    {
        super(id, date, value);
        this.type = type;
        this.name = name;
    }

    @Override
    public boolean sameAs(Object object)
    {
        if ( this == object ) return true;
        if ( object == null || getClass() != object.getClass() ) return false;

        SideGig that = (SideGig) object;

        if (!getId().equals(that.getId())) return false;
        if (!getDate().equals(that.getDate())) return false;
        if (!getType().equals(that.getType())) return false;
        if (!getName().equals(that.getName())) return false;
        return (getValue().equals(that.getValue()));
    }
}
