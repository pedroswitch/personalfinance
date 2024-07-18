package com.example.personalfinance.domain.investment;

import com.example.personalfinance.ddd.AggregateRoot;
import com.example.personalfinance.domain.valueobjects.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Investment implements AggregateRoot<InvestmentId>
{
    private final InvestmentId id;
    private final InvestmentCategory category;
    private final InvestmentDescription description;
    private final Date purchaseDate;
    private final Date saleDate;
    private final InvestmentQty qty;
    private final Values value;


    @Override
    public InvestmentId identity()
    {
        return this.id;
    }

    @Override
    public boolean sameAs(Object object)
    {
        if ( this == object ) return true;
        if ( object == null || getClass() != object.getClass() ) return false;

        Investment that = (Investment) object;

        if (!getId().equals(that.getId())) return false;
        if (!getCategory().equals(that.getCategory())) return false;
        if (!getDescription().equals(that.getDescription())) return false;
        if (!getPurchaseDate().equals(that.getPurchaseDate())) return false;
        if (!getSaleDate().equals(that.getSaleDate())) return false;
        if (!getQty().equals(that.getQty())) return false;
        return (getValue().equals(that.getValue()));
    }
}
