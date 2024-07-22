package com.example.personalfinance.domain.income;

import com.example.personalfinance.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SalaryTest
{
    IncomeId id;
    IncomeType type;
    Date date;
    EmployerName name;
    Values value;

    @BeforeEach
    void setuo()
    {
        id = new IncomeId(1L);
        type = new IncomeType("Salary");
        date = new Date(LocalDate.of(2024, 7, 10));
        name = new EmployerName("Isep");
        value = new Values(1500.00);
    }

    @Test
    void salary()
    {
        // Act
        Salary salary = new Salary(id, type, date, value, name);

        // Assert
        assertNotNull(salary);
    }

    @Test
    void shoudlBeSameAs()
    {
        // Arrange
        IncomeId id2 = new IncomeId(1L);
        IncomeType type2 = new IncomeType("Salary");
        Date date2 = new Date(LocalDate.of(2024, 7, 10));
        EmployerName name2 = new EmployerName("Isep");
        Values value2 = new Values(1500.00);
        Salary salary2 = new Salary(id2, type2, date2, value2, name2);
        Salary salary = new Salary(id, type, date, value, name);

        // Act
        boolean result = salary.sameAs(salary2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shoudlNotBeSameAs()
    {
        // Arrange
        IncomeId id2 = new IncomeId(2L);
        IncomeType type2 = new IncomeType("Salary");
        Date date2 = new Date(LocalDate.of(2024, 7, 10));
        EmployerName name2 = new EmployerName("Isep");
        Values value2 = new Values(1500.00);
        Salary salary2 = new Salary(id2, type2, date2, value2, name2);
        Salary salary = new Salary(id, type, date, value, name);

        // Act
        boolean result = salary.sameAs(salary2);

        // Assert
        assertFalse(result);
    }
}
