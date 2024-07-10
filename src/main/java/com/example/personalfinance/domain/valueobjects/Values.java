package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class Values extends ValueObject
{
    private final Double value;

    public Values(Double value)
    {
        if (value <= 0) throw new IllegalArgumentException("Value cannot be less or equal to zero!");
        this.value = value;
    }
}
