package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.domain.investment.Investment;
import com.example.personalfinance.domain.repository.InvestmentRepo;
import com.example.personalfinance.domain.valueobjects.InvestmentId;
import com.example.personalfinance.mapper.InvestmentMapper;
import com.example.personalfinance.persistence.datamodel.InvestmentDataModel;

import java.util.Optional;

public class InvestmentRepoSpringData implements InvestmentRepo
{
    private final InvestmentRepoSpringDataInterface investmentInterface;

    public InvestmentRepoSpringData(InvestmentRepoSpringDataInterface investmentInterface)
    {
        this.investmentInterface = investmentInterface;
    }

    @Override
    public Investment save(Investment investment)
    {
        InvestmentDataModel investmentDataModel = new InvestmentDataModel(investment);
        InvestmentDataModel result = investmentInterface.save(investmentDataModel);
        return InvestmentMapper.investmentDataModelToDomain(result);
    }

    @Override
    public Iterable<Investment> findAll()
    {
        Iterable<InvestmentDataModel> investments = investmentInterface.findAll();
        return InvestmentMapper.investmentsDataModelToDomain(investments);
    }

    @Override
    public Optional<Investment> findById(InvestmentId id)
    {
        Optional<InvestmentDataModel> model = this.investmentInterface.findById(id.id);

        if (model.isPresent()) {
            return Optional.of(InvestmentMapper.investmentDataModelToDomain(model.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(InvestmentId id)
    {
        return this.investmentInterface.existsById(id.id);
    }

    @Override
    public Iterable<Investment> findAllByCategory(String category)
    {
        Iterable<InvestmentDataModel> investments = this.investmentInterface.findAllByCategory(category);
        return InvestmentMapper.investmentsDataModelToDomain(investments);
    }

    @Override
    public boolean delete(long id)
    {
        if (this.investmentInterface.existsById(id)) {
            this.investmentInterface.deleteById(id);
            return !this.investmentInterface.existsById(id);
        }
        return false;
    }
}
