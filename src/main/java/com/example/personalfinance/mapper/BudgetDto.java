package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.valueobjects.BudgetCategory;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.domain.valueobjects.Values;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class BudgetDto extends RepresentationModel<BudgetDto>
{
    public BudgetId id;
    public BudgetCategory category;
    public Values value;

    public BudgetDto(BudgetId id, BudgetCategory category, Values value)
    {
        this.id = id;
        this.category = category;
        this.value = value;
    }

    public BudgetDto(Link initialLink, BudgetId id, BudgetCategory category, Values value)
    {
        super(initialLink);
        this.id = id;
        this.category = category;
        this.value = value;
    }

    public BudgetDto(Iterable<Link> initialLinks, BudgetId id, BudgetCategory category, Values value)
    {
        super(initialLinks);
        this.id = id;
        this.category = category;
        this.value = value;
    }
}
