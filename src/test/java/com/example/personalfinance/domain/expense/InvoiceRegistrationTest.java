package com.example.personalfinance.domain.expense;

import com.example.personalfinance.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceRegistrationTest
{

    ExpenseId id;
    ExpenseType type;
    Date date;
    ExpenseSupplier supplier;
    InvoiceNumber number;
    ExpenseCategory category;
    Values value;
    ExpenseStatus status;

    @BeforeEach
    void setup()
    {
        id = new ExpenseId(1L);
        type = new ExpenseType("InvoiceRegistration");
        date = new Date(LocalDate.of(2024, 7, 10));
        supplier = new ExpenseSupplier("Zara");
        number = new InvoiceNumber("1234");
        category = new ExpenseCategory("Clothes");
        value = new Values(39.99);
        status = new ExpenseStatus(false);
    }

    @Test
    void invoiceRegistration()
    {
        // Act
        InvoiceRegistration invoice = new InvoiceRegistration(id, type, date, supplier, number, category, value, status);

        // Assert
        assertNotNull(invoice);
    }

    @Test
    void shouldBeSameAs()
    {
        // Arrange
        ExpenseId id2 = new ExpenseId(1L);
        ExpenseType type2 = new ExpenseType("InvoiceRegistration");
        Date date2 = new Date(LocalDate.of(2024, 7, 10));
        ExpenseSupplier supplier2 = new ExpenseSupplier("Zara");
        InvoiceNumber number2 = new InvoiceNumber("1234");
        ExpenseCategory category2 = new ExpenseCategory("Clothes");
        Values value2 = new Values(39.99);
        ExpenseStatus status2 = new ExpenseStatus(false);
        InvoiceRegistration invoice2 = new InvoiceRegistration(id2, type2, date2, supplier2, number2, category2, value2, status2);
        InvoiceRegistration invoice = new InvoiceRegistration(id, type, date, supplier, number, category, value, status);

        // Act
        boolean result = invoice.sameAs(invoice2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotBeSameAs()
    {
        // Arrange
        ExpenseId id2 = new ExpenseId(1L);
        ExpenseType type2 = new ExpenseType("InvoiceRegistration");
        Date date2 = new Date(LocalDate.of(2024, 7, 10));
        ExpenseSupplier supplier2 = new ExpenseSupplier("Zara");
        InvoiceNumber number2 = new InvoiceNumber("4321");
        ExpenseCategory category2 = new ExpenseCategory("Clothes");
        Values value2 = new Values(39.99);
        ExpenseStatus status2 = new ExpenseStatus(false);
        InvoiceRegistration invoice2 = new InvoiceRegistration(id2, type2, date2, supplier2, number2, category2, value2, status2);
        InvoiceRegistration invoice = new InvoiceRegistration(id, type, date, supplier, number, category, value, status);

        // Act
        boolean result = invoice.sameAs(invoice2);

        // Assert
        assertFalse(result);
    }
}
