package com.example.personalfinance.persistence.datamodel;

import com.example.personalfinance.domain.income.Income;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "income")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@Getter
public class IncomeDataModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private int version;

    private String type;
    private LocalDate date;

    @Column(name = "amount")
    private double value;

    public IncomeDataModel()
    {
    }

    public IncomeDataModel(Income income)
    {
        this.id = income.identity().getId();
        this.type = income.getType().getType();
        this.date = income.getDate().getDate();
        this.value = income.getValue().getValue();
    }
}
