package com.example.personalfinance.domain.investment;

import com.example.personalfinance.domain.valueobjects.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InvestmentFactory
{
    public Investment createInvestment(InvestmentId id, InvestmentCategory category, InvestmentDescription description, Date purchaseDate, Date saleDate, InvestmentQty qty, Values value)
    {
        return new Investment(id, category, description, purchaseDate, saleDate, qty, value);
    }

    public Optional<InvestmentId> createInvestmentId(long id)
    {
        try {
            return Optional.of(new InvestmentId(id));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<InvestmentCategory> createInvestmentCategory(String category)
    {
        try {
            return Optional.of(new InvestmentCategory(category));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<InvestmentDescription> createInvestmentDescription(String description)
    {
        try {
            return Optional.of(new InvestmentDescription((description)));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<Date> createDate(LocalDate date)
    {
        try {
            return Optional.of(new Date(date));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<InvestmentQty> createInvestmentQty(int qty)
    {
        try {
            return Optional.of(new InvestmentQty(qty));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<Values> createValue(double value)
    {
        try {
            return Optional.of(new Values(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
