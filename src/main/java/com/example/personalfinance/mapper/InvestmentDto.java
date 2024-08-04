package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
public class InvestmentDto extends RepresentationModel<InvestmentDto>
{
    public InvestmentId id;
    public InvestmentCategory category;
    public InvestmentDescription description;
    public Date purchaseDate;
    public Date saleDate;
    public InvestmentQty qty;
    public Values value;

    public InvestmentDto(InvestmentId id, InvestmentCategory category, InvestmentDescription description, Date purchaseDate, Date saleDate, InvestmentQty qty, Values value)
    {
        this.id = id;
        this.category = category;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.saleDate = saleDate;
        this.qty = qty;
        this.value = value;
    }

    public InvestmentDto(Link initialLink, InvestmentId id, InvestmentCategory category, InvestmentDescription description, Date purchaseDate, Date saleDate, InvestmentQty qty, Values value)
    {
        super(initialLink);
        this.id = id;
        this.category = category;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.saleDate = saleDate;
        this.qty = qty;
        this.value = value;
    }

    public InvestmentDto(Iterable<Link> initialLinks, InvestmentId id, InvestmentCategory category, InvestmentDescription description, Date purchaseDate, Date saleDate, InvestmentQty qty, Values value)
    {
        super(initialLinks);
        this.id = id;
        this.category = category;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.saleDate = saleDate;
        this.qty = qty;
        this.value = value;
    }
}
