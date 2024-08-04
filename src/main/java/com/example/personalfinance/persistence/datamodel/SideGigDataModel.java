package com.example.personalfinance.persistence.datamodel;

import com.example.personalfinance.domain.income.Income;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("sidegig")
@Getter
public class SideGigDataModel extends IncomeDataModel
{

     public SideGigDataModel()
    {
    }

    public SideGigDataModel(Income income, String sidegigName)
    {
        super(income);
        setName(sidegigName);
    }
}
