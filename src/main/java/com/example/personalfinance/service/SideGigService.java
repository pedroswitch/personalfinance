package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.SideGig;

import java.util.List;
import java.util.Optional;

public class SideGigService implements IncomeOperations<SideGig>
{

    @Override
    public List<SideGig> findByName(String name)
    {
        return List.of();
    }

    @Override
    public Optional<SideGig> add(SideGig item)
    {
        return Optional.empty();
    }

    @Override
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public List<SideGig> findAll()
    {
        return List.of();
    }
}
