package com.example.personalfinance.persistence.datamodel;

import com.example.personalfinance.domain.investment.Investment;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name="investment")
@Getter
public class InvestmentDataModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private int version;

    private String category;
    private String description;
    private LocalDate purchaseDate;
    private LocalDate saleDate;
    private int qty;

    @Column(name = "amount")
    private double value;

    public InvestmentDataModel()
    {
    }

    public InvestmentDataModel(Investment investment)
    {
        this.id = investment.getId().getId();
        this.category = investment.getCategory().getCategory();
        this.description = investment.getDescription().getDescription();
        this.purchaseDate = investment.getPurchaseDate().getDate();
        this.saleDate = investment.getSaleDate().getDate();
        this.qty = investment.getQty().getQty();
        this.value = investment.getValue().getValue();
    }

}
