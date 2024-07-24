package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.SideGig;

import java.util.List;

public class SideGigService implements IncomeOperations<SideGig>
{
    public SideGig add(SideGig item)
    {
        return null;
    }

    @Override
    public Iterable<SideGig> findByName(String name)
    {
        return List.of();
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public Iterable<SideGig> findAll()
    {
        return List.of();
    }
}
