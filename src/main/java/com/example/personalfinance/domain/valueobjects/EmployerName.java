package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class EmployerName extends ValueObject
{
    private final String name;

    public EmployerName(String name)
    {
        if (name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("Employer name cannot be null or empty!");
        this.name = name;
    }
}
