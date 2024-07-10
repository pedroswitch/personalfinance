package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class SideGigName extends ValueObject
{
    private final String name;

    public SideGigName(String name)
    {
        if (name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty!");
        this.name = name;
    }

}
