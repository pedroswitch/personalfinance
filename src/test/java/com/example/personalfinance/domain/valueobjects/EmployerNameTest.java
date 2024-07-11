package com.example.personalfinance.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class EmployerNameTest
{

    @Test
    void employerName()
    {
        // Act
        EmployerName name = new EmployerName("ISEP");

        // Assert
        assertNotNull(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"     ", "  \n  "})
    @NullAndEmptySource
    void invalidEmployerName(String input)
    {
        // Arrange
        String expectedMessage = "Employer name cannot be null or empty!";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new EmployerName(input));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
