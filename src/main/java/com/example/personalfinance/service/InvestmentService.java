package com.example.personalfinance.service;

import com.example.personalfinance.domain.investment.Investment;
import com.example.personalfinance.domain.investment.InvestmentFactory;
import com.example.personalfinance.domain.repository.InvestmentRepo;
import com.example.personalfinance.domain.valueobjects.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

public class InvestmentService implements InvestmentOperations<Investment>
{
    private final InvestmentRepo investmentRepo;
    private final InvestmentFactory investmentFactory;

    public InvestmentService(@Qualifier("investmentReposSpringData") InvestmentRepo investmentRepo, InvestmentFactory investmentFactory)
    {
        this.investmentRepo = investmentRepo;
        this.investmentFactory = investmentFactory;
    }

    public Investment add(InvestmentId id, InvestmentCategory category, InvestmentDescription description, Date purchaseDate, Date saleDate, InvestmentQty qty, Values value)
    {
        Investment investmentToSave = investmentFactory.createInvestment(id, category, description, purchaseDate, saleDate, qty, value);
        return investmentRepo.save(investmentToSave);
    }

    @Override
    public Iterable<Investment> findAllByCategory(String category)
    {
        Optional<InvestmentCategory> invCategory = investmentFactory.createInvestmentCategory(category);
        return invCategory.map(value -> this.investmentRepo.findAllByCategory(value.getCategory()))
                .orElseGet(List::of);
    }

    @Override
    public boolean delete(long id)
    {
        return this.investmentRepo.delete(id);
    }

    @Override
    public Iterable<Investment> findAll()
    {
        return this.investmentRepo.findAll();
    }
}
