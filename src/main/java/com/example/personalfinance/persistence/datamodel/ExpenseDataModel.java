package com.example.personalfinance.persistence.datamodel;

import com.example.personalfinance.domain.expense.Expense;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentDataModel> payments = new ArrayList<>();

    private String type;
    private String supplier;
    private String category;

    @Column(name = "amount")
    private double value;

    public ExpenseDataModel()
    {
    }

    public ExpenseDataModel(Expense expense)
    {
        this.id = expense.identity().getId();
        this.type = expense.getType().getType();
        this.supplier = expense.getSupplier().getName();
        this.category = expense.getCategory().getCategory();
        this.value = expense.getValue().getValue();
    }

    public void addPayment(PaymentDataModel payment) {
        payments.add(payment);
    }

    public void removePayment(PaymentDataModel payment) {
        payments.remove(payment);
    }
}
