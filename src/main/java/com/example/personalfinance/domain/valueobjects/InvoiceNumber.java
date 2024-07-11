package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class InvoiceNumber extends ValueObject
{
    private final String number;

    public InvoiceNumber(String number)
    {
        if (number == null || number.isEmpty() || number.isBlank())
            throw new IllegalArgumentException("Invoice number cannot be null or empty!");
        this.number = number;
    }
}
