package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.domain.budget.Budget;
import com.example.personalfinance.domain.repository.BudgetRepo;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.mapper.BudgetMapper;
import com.example.personalfinance.persistence.datamodel.BudgetDataModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BudgetRepoSpringData implements BudgetRepo
{

    private final BudgetRepoSpringDataInterface budgetInterface;

    public BudgetRepoSpringData(BudgetRepoSpringDataInterface budgetInterface)
    {
        this.budgetInterface = budgetInterface;
    }

    @Override
    public Budget save(Budget budget)
    {
        BudgetDataModel budgetDataModel = new BudgetDataModel(budget);
        BudgetDataModel result = budgetInterface.save(budgetDataModel);
        return BudgetMapper.budgetDataModelToDomain(result);
    }

    @Override
    public Iterable<Budget> findAll()
    {
        Iterable<BudgetDataModel> budgets = this.budgetInterface.findAll();
        return BudgetMapper.budgetsDataModelToDomain(budgets);
    }

    @Override
    public Optional<Budget> findById(BudgetId id)
    {
        Optional<BudgetDataModel> model = this.budgetInterface.findById(id.id);

        if (model.isPresent()) {
            return Optional.of(BudgetMapper.budgetDataModelToDomain(model.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(BudgetId id)
    {
        return this.budgetInterface.existsById(id.id);
    }

    public Iterable<Budget> findAllByCategory(String category)
    {
        Iterable<BudgetDataModel> budgets = this.budgetInterface.findAllByCategory(category);

        return BudgetMapper.budgetsDataModelToDomain(budgets);
    }

    public boolean delete(long id) {
        if (this.budgetInterface.existsById(id)) {
            this.budgetInterface.deleteById(id);
            return !this.budgetInterface.existsById(id);
        }
        return false;
    }

}
