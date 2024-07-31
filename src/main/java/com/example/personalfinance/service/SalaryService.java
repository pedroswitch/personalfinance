package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.IncomeFactory;
import com.example.personalfinance.domain.repository.IncomeRepo;
import com.example.personalfinance.domain.valueobjects.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.Optional;

public class SalaryService implements IncomeOperations<Income>
{
    private final IncomeRepo incomeRepo;
    private final IncomeFactory incomeFactory;

    public SalaryService(@Qualifier("incomeRepoSpringData") IncomeRepo incomeRepo, IncomeFactory incomeFactory)
    {
        this.incomeRepo = incomeRepo;
        this.incomeFactory = incomeFactory;
    }

    public Income add(IncomeId id, IncomeType type, Date date, Values value, EmployerName name)
    {
        Income salaryToSave = incomeFactory.createIncome(id, type, date, value, name);
        return incomeRepo.save(salaryToSave);
    }

    @Override
    public Iterable<Income> findByName(String name)
    {
        Optional<EmployerName> employerName = incomeFactory.createEmployerName(name);
        return employerName.map(value -> incomeRepo.findByName(value.getName()))
                .orElseGet(Collections::emptyList);
    }

    @Override
    public boolean delete(long id)
    {
        return incomeRepo.delete(id);
    }

    @Override
    public Iterable<Income> findAll()
    {
        return incomeRepo.findAll();
    }

    public Income findById(long id)
    {
        Optional<IncomeId> salaryId = incomeFactory.createIncomeId(id);

        if (salaryId.isPresent())
        {
            Optional<Income> income = incomeRepo.findById(salaryId.get());
            return income.orElse(null);
        }

        return null;
    }
}
