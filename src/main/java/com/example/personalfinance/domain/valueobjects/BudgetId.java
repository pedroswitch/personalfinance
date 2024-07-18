package com.example.personalfinance.domain.valueobjects;

import com.example.personalfinance.ddd.DomainId;
import lombok.Data;

@Data
public class BudgetId implements DomainId
{
    public final long id;
}
