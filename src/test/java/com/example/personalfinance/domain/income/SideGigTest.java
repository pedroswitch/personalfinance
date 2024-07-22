package com.example.personalfinance.domain.income;

import com.example.personalfinance.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SideGigTest
{
    IncomeId id;
    IncomeType type;
    Date date;
    SideGigName name;
    Values value;

    @BeforeEach
    void setup()
    {
        id = new IncomeId(2L);
        type = new IncomeType("SideGig");
        date = new Date(LocalDate.of(2024,07,10));
        name = new SideGigName("Not Intended");
        value = new Values(100.00);
    }

    @Test
    void sideGig()
    {
        // Act
        SideGig sideGig = new SideGig(id, type, date, value, name);

        // Assert
        assertNotNull(sideGig);
    }

    @Test
    void shouldBeSameAs()
    {
        // Arrange
        IncomeId id2 = new IncomeId(2L);
        IncomeType type2 = new IncomeType("SideGig");
        Date date2 = new Date(LocalDate.of(2024,07,10));
        SideGigName name2 = new SideGigName("Not Intended");
        Values value2 = new Values(100.00);
        SideGig sideGig2 = new SideGig(id2, type2, date2, value2, name2);
        SideGig sideGig = new SideGig(id, type, date, value, name);

        // Act
        boolean result = sideGig.sameAs(sideGig2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotBeSameAs()
    {
        // Arrange
        IncomeId id2 = new IncomeId(3L);
        IncomeType type2 = new IncomeType("SideGig");
        Date date2 = new Date(LocalDate.of(2024,07,10));
        SideGigName name2 = new SideGigName("Not Intended");
        Values value2 = new Values(100.00);
        SideGig sideGig2 = new SideGig(id2, type2, date2, value2, name2);
        SideGig sideGig = new SideGig(id, type, date, value, name);

        // Act
        boolean result = sideGig.sameAs(sideGig2);

        // Assert
        assertFalse(result);
    }
}
