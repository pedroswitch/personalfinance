package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.ValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvestmentCategory extends ValueObject
{
    private final String category;

    public InvestmentCategory(String category)
    {
        if (category == null || category.isEmpty() || category.isBlank())
            throw new IllegalArgumentException("Category cannot be null or empty!");
        this.category = category;
    }
}
