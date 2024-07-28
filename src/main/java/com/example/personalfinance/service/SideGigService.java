package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.SideGig;

import java.util.List;

public class SideGigService implements IncomeOperations<Income>
{
    public Income add(SideGig item)
    {
        return null;
    }

    @Override
    public Iterable<Income> findByName(String name)
    {
        return List.of();
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public Iterable<Income> findAll()
    {
        return List.of();
    }
}
