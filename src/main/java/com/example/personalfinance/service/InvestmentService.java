package com.example.personalfinance.service;

import com.example.personalfinance.domain.investment.Investment;
import com.example.personalfinance.domain.investment.InvestmentFactory;
import com.example.personalfinance.domain.repository.InvestmentRepo;
import com.example.personalfinance.domain.valueobjects.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class InvestmentService implements InvestmentOperations<Investment>
{
    private final InvestmentRepo investmentRepo;
    private final InvestmentFactory investmentFactory;

    public InvestmentService(@Qualifier("investmentRepoSpringData") InvestmentRepo investmentRepo, InvestmentFactory investmentFactory)
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
    public Iterable<Investment> findByCategory(String category)
    {
        Optional<InvestmentCategory> invCategory = investmentFactory.createInvestmentCategory(category);
        return invCategory.map(value -> investmentRepo.findByCategory(value.getCategory()))
                .orElseGet(Collections::emptyList);
    }

    @Override
    public boolean delete(long id)
    {
        return investmentRepo.delete(id);
    }

    @Override
    public Iterable<Investment> findAll()
    {
        return investmentRepo.findAll();
    }

    public Investment findById(long id)
    {
        Optional<InvestmentId> investmentId = investmentFactory.createInvestmentId(id);

        if (investmentId.isPresent())
        {
            Optional<Investment> investment = investmentRepo.findById(investmentId.get());
            return investment.orElse(null);
        }

        return null;
    }
}
