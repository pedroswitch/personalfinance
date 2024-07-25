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
        Optional<InvestmentDataModel> model = investmentInterface.findById(id.id);

        if (model.isPresent()) {
            return Optional.of(InvestmentMapper.investmentDataModelToDomain(model.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(InvestmentId id)
    {
        return investmentInterface.existsById(id.id);
    }

    @Override
    public Iterable<Investment> findByCategory(String category)
    {
        Iterable<InvestmentDataModel> investments = investmentInterface.findByCategory(category);
        return InvestmentMapper.investmentsDataModelToDomain(investments);
    }

    @Override
    public boolean delete(long id)
    {
        if (investmentInterface.existsById(id)) {
            investmentInterface.deleteById(id);
            return !investmentInterface.existsById(id);
        }
        return false;
    }
}
