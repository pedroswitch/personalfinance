package com.example.personalfinance.domain.payment;

import com.example.personalfinance.domain.valueobjects.Date;
import com.example.personalfinance.domain.valueobjects.ExpenseId;
import com.example.personalfinance.domain.valueobjects.PaymentId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest
{
    PaymentId id;
    ExpenseId expenseId;
    Date paymentDate;

    @BeforeEach
    void setup()
    {
        id = new PaymentId(1L);
        expenseId = new ExpenseId(1L);
        paymentDate = new Date(LocalDate.of(2024, 7, 25));
    }

    @Test
    void createPayment()
    {
        // Act
        Payment payment = new Payment(id, expenseId, paymentDate);

        // Assert
        assertNotNull(payment);
    }

    @Test
    void shouldBeSameAs()
    {
        // Arrange
        PaymentId id2 = new PaymentId(1L);
        ExpenseId expenseId2 = new ExpenseId(1L);
        Date paymentDate2 = new Date(LocalDate.of(2024, 7, 25));
        Payment payment2 = new Payment(id2, expenseId2, paymentDate2);
        Payment payment = new Payment(id, expenseId, paymentDate);

        // Act
        boolean result = payment.sameAs(payment2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotBeSameAs()
    {
        // Arrange
        PaymentId id2 = new PaymentId(2L);
        ExpenseId expenseId2 = new ExpenseId(1L);
        Date paymentDate2 = new Date(LocalDate.of(2024, 7, 25));
        Payment payment2 = new Payment(id2, expenseId2, paymentDate2);
        Payment payment = new Payment(id, expenseId, paymentDate);

        // Act
        boolean result = payment.sameAs(payment2);

        // Assert
        assertFalse(result);
    }
}
