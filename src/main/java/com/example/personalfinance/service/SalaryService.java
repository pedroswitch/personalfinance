package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.Salary;

import java.util.List;

public class SalaryService implements IncomeOperations<Salary>
{
    public Salary add(Salary item)
    {
        return null;
    }

    @Override
    public Iterable<Salary> findByName(String name)
    {
        return List.of();
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public Iterable<Salary> findAll()
    {
        return List.of();
    }
}
