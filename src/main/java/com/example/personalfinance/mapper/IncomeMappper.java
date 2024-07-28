package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.IncomeFactory;
import com.example.personalfinance.domain.valueobjects.*;
import com.example.personalfinance.persistence.datamodel.IncomeDataModel;
import com.example.personalfinance.persistence.datamodel.SalaryDataModel;
import com.example.personalfinance.persistence.datamodel.SideGigDataModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.personalfinance.utils.Constants.SALARY;
import static com.example.personalfinance.utils.Constants.SIDE_GIG;

public class IncomeMappper
{
    public static Income incomeDataModelToDomain(IncomeDataModel incomeDataModel)
    {
        IncomeFactory incomeFactory = new IncomeFactory();
        IncomeId id = new IncomeId(incomeDataModel.getId());
        IncomeType type = new IncomeType(incomeDataModel.getType());
        Date date = new Date(incomeDataModel.getDate());
        Values value = new Values(incomeDataModel.getValue());

        if (incomeDataModel.getType().equals(SALARY)) {
            SalaryDataModel income = (SalaryDataModel) incomeDataModel;
            EmployerName name = new EmployerName(income.getName());
            return incomeFactory.createIncome(id, type, date, value, name);
        }

        if (incomeDataModel.getType().equals(SIDE_GIG)) {
            SideGigDataModel income = (SideGigDataModel) incomeDataModel;
            SideGigName name = new SideGigName(income.getSidegigName());
            return incomeFactory.createIncome(id, type, date, value, name);
        }

        return incomeFactory.createIncome(id, type, date, value);
    }

    public static Iterable<Income> incomesDataModelToDomain(Iterable<IncomeDataModel> incomeDataModel)
    {
        List<Income> incomes = new ArrayList<>();
        incomeDataModel.forEach(
                dataModel -> incomes.add(incomeDataModelToDomain(dataModel))
        );
        return incomes;
    }
}
