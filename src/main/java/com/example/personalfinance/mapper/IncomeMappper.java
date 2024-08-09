package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.IncomeFactory;
import com.example.personalfinance.domain.income.Salary;
import com.example.personalfinance.domain.income.SideGig;
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
            SideGigName name = new SideGigName(income.getName());
            return incomeFactory.createIncome(id, type, date, value, name);
        }

        return null;
    }

    public static Iterable<Income> incomesDataModelToDomain(Iterable<IncomeDataModel> incomeDataModel)
    {
        List<Income> incomes = new ArrayList<>();
        incomeDataModel.forEach(
                dataModel -> incomes.add(incomeDataModelToDomain(dataModel))
        );
        return incomes;
    }

    public static IncomeDto salaryToIncomeDto(Income income)
    {
        Salary salary = (Salary) income;
        return new IncomeDto(salary.getId().getId(), salary.getType().getType(), salary.getDate().getDate(), salary.getName().getName(), salary.getValue().getValue());
    }

    public static IncomeDto sideGigToIncomeDto(Income income)
    {
        SideGig sideGig = (SideGig) income;
        return new IncomeDto(sideGig.getId().getId(), sideGig.getType().getType(), sideGig.getDate().getDate(), sideGig.getValue().getValue(), sideGig.getName().getName());
    }

    public static Iterable<IncomeDto> salaryListToDto(Iterable<Income> salaries)
    {
        List<IncomeDto> incomeListDto = new ArrayList<>();

        salaries.forEach(salary -> {
            IncomeDto incomeDto = salaryToIncomeDto(salary);
            incomeListDto.add(incomeDto);
        });

        return incomeListDto;
    }

    public static Iterable<IncomeDto> sideGigListToDto(Iterable<Income> sideGigs)
    {
        List<IncomeDto> incomeListDto = new ArrayList<>();

        sideGigs.forEach(sideGig -> {
            IncomeDto incomeDto = sideGigToIncomeDto(sideGig);
            incomeListDto.add(incomeDto);
        });

        return incomeListDto;
    }

    public static Income dtoToSalary(IncomeDto dto)
    {
        IncomeFactory incomeFactory = new IncomeFactory();
        IncomeId id = new IncomeId(dto.id);
        IncomeType type = new IncomeType(dto.type);
        Date date = new Date(dto.date);
        Values value = new Values(dto.value);
        EmployerName name = new EmployerName(dto.employerName);
        return incomeFactory.createIncome(id, type, date, value, name);
    }

    public static Income dtoToSideGig(IncomeDto dto)
    {
        IncomeFactory incomeFactory = new IncomeFactory();
        IncomeId id = new IncomeId(dto.id);
        IncomeType type = new IncomeType(dto.type);
        Date date = new Date(dto.date);
        Values value = new Values(dto.value);
        SideGigName name = new SideGigName(dto.sideGigName);
        return incomeFactory.createIncome(id, type, date, value, name);
    }
}
