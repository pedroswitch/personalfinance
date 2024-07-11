package com.example.personalfinance.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValuesTest
{
    @Test
    void values()
    {
        // Act
        Values value = new Values(100.00);

        // Assert
        assertNotNull(value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.00, 0})
    void invalidInvoiceNumber(Double input)
    {
        // Arrange
        String expectedMessage = "Value cannot be less or equal to zero!";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Values(input));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
