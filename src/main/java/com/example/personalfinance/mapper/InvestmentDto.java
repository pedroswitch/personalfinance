package com.example.personalfinance.mapper;

import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@NoArgsConstructor
public class InvestmentDto extends RepresentationModel<InvestmentDto>
{
    public long id;
    public String category;
    public String description;
    public LocalDate purchaseDate;
    public LocalDate saleDate;
    public int qty;
    public double value;

    public InvestmentDto(long id, String category, String description, LocalDate purchaseDate, LocalDate saleDate, int qty, double value)
    {
        this.id = id;
        this.category = category;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.saleDate = saleDate;
        this.qty = qty;
        this.value = value;
    }

    public InvestmentDto(Link initialLink, long id, String category, String description, LocalDate purchaseDate, LocalDate saleDate, int qty, double value)
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

    public InvestmentDto(Iterable<Link> initialLinks, long id, String category, String description, LocalDate purchaseDate, LocalDate saleDate, int qty, double value)
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
