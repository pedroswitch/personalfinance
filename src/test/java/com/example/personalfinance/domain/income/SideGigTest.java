package com.example.personalfinance.domain.income;

import com.example.personalfinance.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SideGigTest
{
    IncomeId id;
    Date date;
    SideGigType type;
    SideGigName name;
    Values value;

    @BeforeEach
    void setup()
    {
        id = new IncomeId(2L);
        date = new Date(LocalDate.of(2024,07,10));
        type = new SideGigType("Financial");
        name = new SideGigName("Not Intended");
        value = new Values(100.00);
    }

    @Test
    void sideGig()
    {
        // Act
        SideGig sideGig = new SideGig(id, date, value, type, name);

        // Assert
        assertNotNull(sideGig);
    }

    @Test
    void shouldBeSameAs()
    {
        // Arrange
        IncomeId id2 = new IncomeId(2L);
        Date date2 = new Date(LocalDate.of(2024,07,10));
        SideGigType type2 = new SideGigType("Financial");
        SideGigName name2 = new SideGigName("Not Intended");
        Values value2 = new Values(100.00);
        SideGig sideGig2 = new SideGig(id2, date2, value2, type2, name2);
        SideGig sideGig = new SideGig(id, date, value, type, name);

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
        Date date2 = new Date(LocalDate.of(2024,07,10));
        SideGigType type2 = new SideGigType("Financial");
        SideGigName name2 = new SideGigName("Not Intended");
        Values value2 = new Values(100.00);
        SideGig sideGig2 = new SideGig(id2, date2, value2, type2, name2);
        SideGig sideGig = new SideGig(id, date, value, type, name);

        // Act
        boolean result = sideGig.sameAs(sideGig2);

        // Assert
        assertFalse(result);
    }
}
