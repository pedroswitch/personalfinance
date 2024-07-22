package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.investment.Investment;
import com.example.personalfinance.domain.investment.InvestmentFactory;
import com.example.personalfinance.domain.valueobjects.*;
import com.example.personalfinance.persistence.datamodel.InvestmentDataModel;

import java.util.ArrayList;
import java.util.List;

public class InvestmentMapper
{
    public static Investment investmentDataModelToDomain(InvestmentDataModel investmentDataModel)
    {
        InvestmentFactory investmentFactory = new InvestmentFactory();
        InvestmentId id = new InvestmentId(investmentDataModel.getId());
        InvestmentCategory category = new InvestmentCategory(investmentDataModel.getCategory());
        InvestmentDescription description = new InvestmentDescription(investmentDataModel.getDescription());
        Date purchaseDate = new Date(investmentDataModel.getPurchaseDate());
        Date saleDate = new Date(investmentDataModel.getSaleDate());
        InvestmentQty qty = new InvestmentQty(investmentDataModel.getQty());
        Values value = new Values(investmentDataModel.getValue());

        return investmentFactory.createInvestment(id, category, description, purchaseDate, saleDate, qty, value);
    }

    public static Iterable<Investment> investmentsDataModelToDomain(Iterable<InvestmentDataModel> investmentDataModels)
    {
        List<Investment> investments = new ArrayList<>();
        investmentDataModels.forEach(
                dataModel -> investments.add(investmentDataModelToDomain(dataModel))
        );
        return investments;
    }
}
