package com.example.personalfinance.domain.budget;

import com.example.personalfinance.domain.valueobjects.BudgetCategory;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.domain.valueobjects.Values;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTest
{
    BudgetId id;
    BudgetCategory category;
    Values value;

    @BeforeEach
    void setup()
    {
        id = new BudgetId(1L);
        category = new BudgetCategory("Viaturas");
        value = new Values(120.00);
    }

    @Test
    void createBudget()
    {
        // Act
        Budget budget = new Budget(id, category, value);

        // Assert
        assertNotNull(budget);
    }

    @Test
    void shouldBeSameAs()
    {
        // Arrange
        BudgetId id2 = new BudgetId(1L);
        BudgetCategory category2 = new BudgetCategory("Viaturas");
        Values value2 = new Values(120.00);
        Budget budget2 = new Budget(id2, category2, value2);
        Budget budget = new Budget(id, category, value);

        // Act
        boolean result = budget.sameAs(budget2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotBeSameAs()
    {
        // Arrange
        BudgetId id2 = new BudgetId(2L);
        BudgetCategory category2 = new BudgetCategory("Viaturas");
        Values value2 = new Values(120.00);
        Budget budget2 = new Budget(id2, category2, value2);
        Budget budget = new Budget(id, category, value);

        // Act
        boolean result = budget.sameAs(budget2);

        // Assert
        assertFalse(result);
    }
}
