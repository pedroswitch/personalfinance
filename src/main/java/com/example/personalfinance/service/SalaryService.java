package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.Salary;

import java.util.List;
import java.util.Optional;

public class SalaryService implements IncomeOperations<Salary>
{

    @Override
    public List<Salary> findByName(String name)
    {
        return List.of();
    }

    @Override
    public Optional<Salary> add(Salary item)
    {
        return Optional.empty();
    }

    @Override
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public List<Salary> findAll()
    {
        return List.of();
    }
}
