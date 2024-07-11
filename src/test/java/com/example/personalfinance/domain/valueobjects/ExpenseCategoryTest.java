package com.example.personalfinance.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseCategoryTest
{
    @Test
    void expenseCategory()
    {
        // Act
        ExpenseCategory category = new ExpenseCategory("Communications");

        // Assert
        assertNotNull(category);
    }

    @ParameterizedTest
    @ValueSource(strings = {"     ", "  \n  "})
    @NullAndEmptySource
    void invalidExpenseCategory(String input)
    {
        // Arrange
        String expectedMessage = "Category cannot be null or empty!";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ExpenseCategory(input));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
