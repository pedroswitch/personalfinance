package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.ValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvestmentDescription extends ValueObject
{
    private final String description;

    public InvestmentDescription(String description)
    {
        if (description == null || description.isEmpty() || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or empty!");
        this.description = description;
    }
}
