package com.example.personalfinance.persistence;

import com.example.personalfinance.domain.expense.Expense;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "expense")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@Getter
public class ExpenseDataModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private int version;

    private String type;
    private String supplier;
    private String category;
    private double value;

    public ExpenseDataModel()
    {
    }

    public ExpenseDataModel(Expense expense)
    {
        this.id = expense.identity().getId();
        this.type = expense.getType().getType();
        this.supplier = expense.getType().getType();
        this.category = expense.getCategory().getCategory();
        this.value = expense.getValue().getValue();
    }
}
