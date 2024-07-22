package com.example.personalfinance.domain.expense;

import com.example.personalfinance.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RecurringBillTest
{

    ExpenseId id;
    ExpenseType type;
    ExpenseSupplier supplier;
    ExpenseCategory category;
    Values value;
    Date initialDate;
    Date finalDate;

    @BeforeEach
    void setuo()
    {
        id = new ExpenseId(2L);
        type = new ExpenseType("RecurringBill");
        supplier = new ExpenseSupplier("Vodafone");
        category = new ExpenseCategory("Communications");
        value = new Values(29.99);
        initialDate = new Date(LocalDate.of(2024, 7, 10));
        finalDate = new Date(LocalDate.of(2025, 6, 10));
    }

    @Test
    void recurringBill()
    {
        // Act
        RecurringBill recurringBill = new RecurringBill(id, type, supplier, category, value, initialDate, finalDate);

        // Assert
        assertNotNull(recurringBill);
    }

    @Test
    void shouldBeSameAs()
    {
        // Arrange
        ExpenseId id2 = new ExpenseId(2L);
        ExpenseType type2 = new ExpenseType("RecurringBill");
        ExpenseSupplier supplier2 = new ExpenseSupplier("Vodafone");
        ExpenseCategory category2 = new ExpenseCategory("Communications");
        Values value2 = new Values(29.99);
        Date initialDate2 = new Date(LocalDate.of(2024, 7, 10));
        Date finalDate2 = new Date(LocalDate.of(2025, 6, 10));
        RecurringBill recurringBill2 = new RecurringBill(id2, type2, supplier2, category2, value2, initialDate2, finalDate2);
        RecurringBill recurringBill = new RecurringBill(id, type, supplier, category, value, initialDate, finalDate);

        // Act
        boolean result = recurringBill.sameAs(recurringBill2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotBeSameAs()
    {
        // Arrange
        ExpenseId id2 = new ExpenseId(3L);
        ExpenseType type2 = new ExpenseType("RecurringBill");
        ExpenseSupplier supplier2 = new ExpenseSupplier("Vodafone");
        ExpenseCategory category2 = new ExpenseCategory("Communications");
        Values value2 = new Values(29.99);
        Date initialDate2 = new Date(LocalDate.of(2024, 7, 10));
        Date finalDate2 = new Date(LocalDate.of(2025, 6, 10));
        RecurringBill recurringBill2 = new RecurringBill(id2, type2, supplier2, category2, value2, initialDate2, finalDate2);
        RecurringBill recurringBill = new RecurringBill(id, type, supplier, category, value, initialDate, finalDate);

        // Act
        boolean result = recurringBill.sameAs(recurringBill2);

        // Assert
        assertFalse(result);
    }
}
