package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.ValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Date extends ValueObject
{
    private final Date d;
}
