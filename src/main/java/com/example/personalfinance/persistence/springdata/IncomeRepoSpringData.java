package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.Salary;
import com.example.personalfinance.domain.income.SideGig;
import com.example.personalfinance.domain.repository.IncomeRepo;
import com.example.personalfinance.domain.valueobjects.IncomeId;
import com.example.personalfinance.mapper.IncomeMappper;
import com.example.personalfinance.persistence.datamodel.IncomeDataModel;
import com.example.personalfinance.persistence.datamodel.SalaryDataModel;
import com.example.personalfinance.persistence.datamodel.SideGigDataModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IncomeRepoSpringData implements IncomeRepo
{
    private final IncomeRepoSpringDataInterface incomeInterface;

    public IncomeRepoSpringData(IncomeRepoSpringDataInterface incomeInterface)
    {
        this.incomeInterface = incomeInterface;
    }

    @Override
    public Income save(Income income)
    {
        IncomeDataModel incomeDataModel;
        if (income instanceof Salary salary) {
            incomeDataModel = new SalaryDataModel(income, salary.getName().getName());
        } else if (income instanceof SideGig sideGig) {
            incomeDataModel = new SideGigDataModel(income, sideGig.getName().getName());
        } else {
            incomeDataModel = new IncomeDataModel(income);
        }
        IncomeDataModel result = incomeInterface.save(incomeDataModel);
        return IncomeMappper.incomeDataModelToDomain(result);
    }

    @Override
    public Iterable<Income> findAll()
    {
        Iterable<IncomeDataModel> incomes = incomeInterface.findAll();
        return IncomeMappper.incomesDataModelToDomain(incomes);
    }

    @Override
    public Iterable<Income> findByName(String name)
    {
        Iterable<IncomeDataModel> incomes = incomeInterface.findByName(name);
        return IncomeMappper.incomesDataModelToDomain(incomes);
    }

    @Override
    public boolean delete(long id)
    {
        if (incomeInterface.existsById(id)) {
            incomeInterface.deleteById(id);
            return !incomeInterface.existsById(id);
        }
        return false;
    }

    @Override
    public Optional<Income> findById(IncomeId id)
    {
        Optional<IncomeDataModel> model = this.incomeInterface.findById(id.id);
        if (model.isPresent()) {
            return Optional.of(IncomeMappper.incomeDataModelToDomain(model.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(IncomeId id)
    {
        return this.incomeInterface.existsById(id.id);
    }
}
