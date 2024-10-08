package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class IncomeType  extends ValueObject
{
    private final String type;

    public IncomeType(String type)
    {
        if (type == null || type.isEmpty() || type.isBlank())
            throw new IllegalArgumentException("Type cannot be null or empty!");
        this.type = type;
    }
}
