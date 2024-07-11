package com.example.personalfinance.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceNumberTest
{
    @Test
    void invoiceNumber()
    {
        // Act
        InvoiceNumber number = new InvoiceNumber("1234");

        // Assert
        assertNotNull(number);
    }

    @ParameterizedTest
    @ValueSource(strings = {"     ", "  \n  "})
    @NullAndEmptySource
    void invalidInvoiceNumber(String input)
    {
        // Arrange
        String expectedMessage = "Invoice number cannot be null or empty!";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new InvoiceNumber(input));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
