package com.example.personalfinance.service;

import com.example.personalfinance.domain.investment.Investment;

import java.util.List;
import java.util.Optional;

public class InvestmentService implements InvestmentOperations<Investment>
{

    @Override
    public List<Investment> findByCategory(String category)
    {
        return List.of();
    }

    @Override
    public Optional<Investment> add(Investment item)
    {
        return Optional.empty();
    }

    @Override
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public List<Investment> findAll()
    {
        return List.of();
    }
}
