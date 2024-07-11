package com.example.personalfinance.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseSupplierTest
{
    @Test
    void expenseSupplier()
    {
        // Act
        ExpenseSupplier supplier = new ExpenseSupplier("Vodafone");

        // Assert
        assertNotNull(supplier);
    }

    @ParameterizedTest
    @ValueSource(strings = {"     ", "  \n  "})
    @NullAndEmptySource
    void invalidExpenseSupplier(String input)
    {
        // Arrange
        String expectedMessage = "Name cannot be null or empty!";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ExpenseSupplier(input));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
