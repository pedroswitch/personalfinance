package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.IncomeFactory;
import com.example.personalfinance.domain.repository.IncomeRepo;
import com.example.personalfinance.domain.valueobjects.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.Optional;

public class SideGigService implements IncomeOperations<Income>
{
    private final IncomeRepo incomeRepo;
    private final IncomeFactory incomeFactory;

    public SideGigService(@Qualifier("incomeRepoSpringData") IncomeRepo incomeRepo, IncomeFactory incomeFactory)
    {
        this.incomeRepo = incomeRepo;
        this.incomeFactory = incomeFactory;
    }

    public Income add(IncomeId id, IncomeType type, Date date, Values value, SideGigName name)
    {
        Income sideGigToSave = incomeFactory.createIncome(id, type, date, value, name);
        return incomeRepo.save(sideGigToSave);
    }

    @Override
    public Iterable<Income> findByName(String name)
    {
        Optional<SideGigName> sideGigName = incomeFactory.createSideGigName(name);
        return sideGigName.map(value -> incomeRepo.findByName(value.getName()))
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
        Optional<IncomeId> sideGigId = incomeFactory.createIncomeId(id);

        if (sideGigId.isPresent())
        {
            Optional<Income> income = incomeRepo.findById(sideGigId.get());
            return income.orElse(null);
        }

        return null;
    }
}
