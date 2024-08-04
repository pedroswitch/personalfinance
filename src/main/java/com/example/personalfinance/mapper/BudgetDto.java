package com.example.personalfinance.mapper;

import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
public class BudgetDto extends RepresentationModel<BudgetDto>
{
    public long id;
    public String category;
    public double value;

    public BudgetDto(long id, String category, double value)
    {
        this.id = id;
        this.category = category;
        this.value = value;
    }

    public BudgetDto(Link initialLink, long id, String category, double value)
    {
        super(initialLink);
        this.id = id;
        this.category = category;
        this.value = value;
    }

    public BudgetDto(Iterable<Link> initialLinks, long id, String category, double value)
    {
        super(initialLinks);
        this.id = id;
        this.category = category;
        this.value = value;
    }
}
