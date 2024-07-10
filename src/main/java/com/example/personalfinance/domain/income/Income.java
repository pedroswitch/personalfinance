package com.example.personalfinance.domain.income;

import com.example.personalfinance.ddd.AggregateRoot;
import com.example.personalfinance.domain.valueobjects.Date;
import com.example.personalfinance.domain.valueobjects.IncomeId;
import com.example.personalfinance.domain.valueobjects.Values;
import lombok.Data;

@Data
public abstract class Income implements AggregateRoot<IncomeId>
{
    private final IncomeId id;
    private final Date date;
    private final Values value;

    public IncomeId identity() {return this.id;}
}
