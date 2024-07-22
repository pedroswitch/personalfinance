package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.domain.investment.Investment;
import com.example.personalfinance.domain.repository.InvestmentRepo;
import com.example.personalfinance.domain.valueobjects.InvestmentId;
import com.example.personalfinance.mapper.InvestmentMapper;
import com.example.personalfinance.persistence.datamodel.InvestmentDataModel;

import java.util.Optional;

public class InvestmentRepoSpringData implements InvestmentRepo
{
    private final InvestmentRepoSpringDataInterface _investmentInterface;

    public InvestmentRepoSpringData(InvestmentRepoSpringDataInterface investmentInterface)
    {
        this._investmentInterface = investmentInterface;
    }

    @Override
    public Investment save(Investment investment)
    {
        InvestmentDataModel investmentDataModel = new InvestmentDataModel(investment);
        InvestmentDataModel result = _investmentInterface.save(investmentDataModel);
        return InvestmentMapper.investmentDataModelToDomain(result);
    }

    @Override
    public Iterable<Investment> findAll()
    {
        Iterable<InvestmentDataModel> investments = _investmentInterface.findAll();
        return InvestmentMapper.investmentsDataModelToDomain(investments);
    }

    @Override
    public Optional<Investment> findById(InvestmentId id)
    {
        Optional<InvestmentDataModel> model = this._investmentInterface.findById(id.id);

        if (model.isPresent()) {
            return Optional.of(InvestmentMapper.investmentDataModelToDomain(model.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(InvestmentId id)
    {
        return this._investmentInterface.existsById(id.id);
    }
}
