package com.example.personalfinance.domain.expense;

import com.example.personalfinance.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest
{
    ExpenseId id;
    Date date;
    ExpenseSupplier supplier;
    InvoiceNumber number;
    ExpenseCategory category;
    Values value;
    Date paymentDate;

    @BeforeEach
    void setup()
    {
        id = new ExpenseId(1L);
        date = new Date(LocalDate.of(2024, 7, 10));
        supplier = new ExpenseSupplier("Zara");
        number = new InvoiceNumber("1234");
        category = new ExpenseCategory("Clothes");
        value = new Values(39.99);
        paymentDate = new Date(LocalDate.of(2024, 8, 10));
    }

    @Test
    void invoiceRegistration()
    {
        // Act
        Payment payment = new Payment(id, supplier, category, value, date, number, paymentDate);

        // Assert
        assertNotNull(payment);
    }

    @Test
    void shouldBeSameAs()
    {
        // Arrange
        ExpenseId id2 = new ExpenseId(1L);
        Date date2 = new Date(LocalDate.of(2024, 7, 10));
        ExpenseSupplier supplier2 = new ExpenseSupplier("Zara");
        InvoiceNumber number2 = new InvoiceNumber("1234");
        ExpenseCategory category2 = new ExpenseCategory("Clothes");
        Values value2 = new Values(39.99);
        Date paymentDate2 = new Date(LocalDate.of(2024, 8, 10));
        Payment payment2 = new Payment(id2, supplier2, category2, value2, date2, number2, paymentDate2);
        Payment payment = new Payment(id, supplier, category, value, date, number, paymentDate);

        // Act
        boolean result = payment.sameAs(payment2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotBeSameAs()
    {
        // Arrange
        ExpenseId id2 = new ExpenseId(1L);
        Date date2 = new Date(LocalDate.of(2024, 7, 10));
        ExpenseSupplier supplier2 = new ExpenseSupplier("Zara");
        InvoiceNumber number2 = new InvoiceNumber("4321");
        ExpenseCategory category2 = new ExpenseCategory("Clothes");
        Values value2 = new Values(39.99);
        Date paymentDate2 = new Date(LocalDate.of(2024, 8, 10));
        Payment payment2 = new Payment(id2, supplier2, category2, value2, date2, number2, paymentDate2);
        Payment payment = new Payment(id, supplier, category, value, date, number, paymentDate);

        // Act
        boolean result = payment.sameAs(payment2);

        // Assert
        assertFalse(result);
    }
}
