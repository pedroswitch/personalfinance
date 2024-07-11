package com.example.personalfinance.domain.valueobjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class SideGigNameTest
{
    @Test
    void sideGigName()
    {
        // Act
        SideGigName name = new SideGigName("Finance consultant");

        // Assert
        assertNotNull(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"     ", "  \n  "})
    @NullAndEmptySource
    void invalidSideGigName(String input)
    {
        // Arrange
        String expectedMessage = "Name cannot be null or empty!";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SideGigName(input));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
