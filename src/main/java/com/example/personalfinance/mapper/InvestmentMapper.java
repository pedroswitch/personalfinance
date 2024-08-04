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

    public static InvestmentDto investmentToDto(Investment investment)
    {
        return new InvestmentDto(investment.getId().getId(), investment.getCategory().getCategory(), investment.getDescription().getDescription(), investment.getPurchaseDate().getDate(), investment.getSaleDate().getDate(), investment.getQty().getQty(), investment.getValue().getValue());
    }

    public static Iterable<InvestmentDto> investmentListToDto(Iterable<Investment> investments)
    {
        List<InvestmentDto> investmentListDto = new ArrayList<>();

        investments.forEach(investment -> {
            InvestmentDto investmentDto = investmentToDto(investment);
            investmentListDto.add(investmentDto);
        });

        return investmentListDto;
    }

    public static Investment dtoToInvestment(InvestmentDto dto)
    {
        InvestmentFactory investmentFactory = new InvestmentFactory();
        InvestmentId id = new InvestmentId(dto.id);
        InvestmentCategory category = new InvestmentCategory(dto.category);
        InvestmentDescription description = new InvestmentDescription(dto.description);
        Date purchaseDate = new Date(dto.purchaseDate);
        Date saleDate = new Date(dto.saleDate);
        InvestmentQty qty = new InvestmentQty(dto.qty);
        Values value = new Values(dto.value);
        return investmentFactory.createInvestment(id, category, description, purchaseDate, saleDate, qty, value);
    }
}
