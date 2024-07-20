package com.example.personalfinance.persistence;

import com.example.personalfinance.domain.budget.Budget;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="budget")
@Getter
public class BudgetDataModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private int version;

    private String category;
    private double value;

    public BudgetDataModel()
    {
    }

    public BudgetDataModel(Budget budget)
    {
        this.id = budget.identity().getId();
        this.category = budget.getCategory().getCategory();
        this.value = budget.getValue().getValue();
    }
}
