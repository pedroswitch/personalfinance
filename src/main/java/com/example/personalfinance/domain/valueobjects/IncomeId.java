package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.DomainId;
import lombok.Data;

@Data
public class IncomeId implements DomainId
{
    public final long id;
}
