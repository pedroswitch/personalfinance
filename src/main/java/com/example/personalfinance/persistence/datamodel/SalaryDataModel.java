package com.example.personalfinance.persistence.datamodel;

import com.example.personalfinance.domain.income.Income;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("salary")
@Getter
public class SalaryDataModel extends IncomeDataModel
{

    public SalaryDataModel()
    {
    }

    public SalaryDataModel(Income income, String employerName)
    {
        super(income);
        setName(employerName);
    }
}
