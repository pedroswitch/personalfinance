package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.ExpenseFactory;
import com.example.personalfinance.domain.payment.Payment;
import com.example.personalfinance.domain.payment.PaymentFactory;
import com.example.personalfinance.domain.repository.PaymentRepo;
import com.example.personalfinance.domain.valueobjects.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest
{
    @Mock
    private PaymentRepo paymentRepo;

    @Mock
    private PaymentFactory paymentFactory;

    @Mock
    private ExpenseFactory expenseFactory;

    @Mock
    private Payment payment;

    @InjectMocks
    private PaymentService paymentService;

    @InjectMocks
    InvoiceRegistrationService invoiceRegistrationService;

    @Test
    void shouldAddPayment()
    {
        // Arrange
        PaymentId id = new PaymentId(1L);
        ExpenseId expenseId = new ExpenseId(1L);
        Date paymentDate = new Date(LocalDate.of(2024,7,26));
        when(payment.getId()).thenReturn(id);
        when(payment.getExpenseId()).thenReturn(expenseId);
        when(payment.getPaymentDate()).thenReturn(paymentDate);
        when(paymentFactory.createPayment(id, expenseId, paymentDate)).thenReturn(payment);
        when(paymentRepo.save(payment)).thenReturn(payment);

        // Act
        Payment result = paymentService.add(id, expenseId, paymentDate);

        // Assert
        assertEquals(1, result.getId().getId());
        assertEquals(1, result.getExpenseId().getId());
        assertEquals(LocalDate.of(2024,7,26), result.getPaymentDate().getDate());
    }

    @Test
    void shouldFindBySupplier()
    {
        // Arrange
        String supplierString = "Vodafone";
        ExpenseSupplier supplier = new ExpenseSupplier(supplierString);
        PaymentId id = new PaymentId(1L);
        ExpenseId expenseId = new ExpenseId(1L);
        Date paymentDate = new Date(LocalDate.of(2024,7,26));
        Payment payment1 = new Payment(id, expenseId, paymentDate);
        List<Payment> expectedPayments = Arrays.asList(payment1);
        when(expenseFactory.createExpenseSupplier(supplierString)).thenReturn(Optional.of(supplier));
        when(paymentRepo.findPaymentsBySupplier(supplierString)).thenReturn(expectedPayments);

        // Act
        Iterable<Payment> result = paymentService.findBySupplier(supplierString);

        // Assert
        assertIterableEquals(expectedPayments, result);
    }

    @Test
    void shouldReturnsEmptyListFindBySupplier()
    {
        // Arrange
        String nonExistingSupplier = "nonExistingSupplier";
        ExpenseSupplier supplier = new ExpenseSupplier(nonExistingSupplier);
        when(expenseFactory.createExpenseSupplier(nonExistingSupplier)).thenReturn(Optional.of(supplier));
        when(paymentRepo.findPaymentsBySupplier(supplier.getName())).thenReturn(Collections.EMPTY_LIST);

        // Act
        Iterable<Payment> result = paymentService.findBySupplier(nonExistingSupplier);

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldDelete()
    {
        // Arrange
        long paymentId = 1L;
        when(paymentRepo.delete(paymentId)).thenReturn(true);

        // Act
        boolean result = paymentService.delete(paymentId);

        // Act
        assertTrue(result);
    }

    @Test
    void shouldNotDelete()
    {
        // Arrange
        long nonExistingPaymentId = 999L;
        when(paymentRepo.delete(nonExistingPaymentId)).thenReturn(false);

        // Act
        boolean result = paymentService.delete(nonExistingPaymentId);

        // Act
        assertFalse(result);
    }

    @Test
    void shouldFindAll()
    {
        // Arrange
        PaymentId id1 = new PaymentId(1L);
        ExpenseId expenseId1 = new ExpenseId(1L);
        Date paymentDate1 = new Date(LocalDate.of(2024,7,26));
        PaymentId id2 = new PaymentId(2L);
        ExpenseId expenseId2 = new ExpenseId(2L);
        Date paymentDate2 = new Date(LocalDate.of(2024,8,26));
        Payment payment1 = new Payment(id1, expenseId1, paymentDate1);
        Payment payment2 = new Payment(id2, expenseId2, paymentDate2);
        List<Payment> expectedPayments = Arrays.asList(payment1, payment2);
        when(paymentRepo.findAll()).thenReturn(expectedPayments);

        // Act
        Iterable<Payment> result = paymentService.findAll();

        // Assert
        assertIterableEquals(expectedPayments, result);
    }

    @Test
    void shouldReturnsEmptyListFindAll()
    {
        // Act
        Iterable<Payment> result = paymentService.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldFindById()
    {
        long idLong = 1L;
        ExpenseSupplier supplier = new ExpenseSupplier("Vodafone");
        PaymentId id = new PaymentId(idLong);
        ExpenseId expenseId = new ExpenseId(1L);
        Date paymentDate = new Date(LocalDate.of(2024,7,26));
        Payment payment = new Payment(id, expenseId, paymentDate);
        when(paymentFactory.createPaymentId(idLong)).thenReturn(Optional.of(id));
        when(paymentRepo.findById(id)).thenReturn(Optional.of(payment));

        // Act
        Payment result = paymentService.findById(idLong);

        // Assert
        assertEquals(payment, result);
    }

    @Test
    void shouldReturnsEmptyListFindById()
    {
        // Arrange
        long nonExistingPaymentId = 999L;
        PaymentId id = new PaymentId(nonExistingPaymentId);
        when(paymentFactory.createPaymentId(nonExistingPaymentId)).thenReturn(Optional.of(id));
        when(paymentRepo.findById(id)).thenReturn(Optional.empty());

        // Act
        Payment result = paymentService.findById(nonExistingPaymentId);

        // Assert
        assertNull(result);
    }

}
