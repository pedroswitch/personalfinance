package com.example.personalfinance.service;

import com.example.personalfinance.domain.investment.Investment;

import java.util.List;

public class InvestmentService implements InvestmentOperations<Investment>
{
    public Investment add(Investment item)
    {
        return null;
    }

    @Override
    public Iterable<Investment> findByCategory(String category)
    {
        return List.of();
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public Iterable<Investment> findAll()
    {
        return List.of();
    }
}
