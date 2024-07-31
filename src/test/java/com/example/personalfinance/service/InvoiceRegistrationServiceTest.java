package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.ExpenseFactory;
import com.example.personalfinance.domain.expense.InvoiceRegistration;
import com.example.personalfinance.domain.repository.ExpenseRepo;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceRegistrationServiceTest
{
    @Mock
    ExpenseRepo expenseRepo;

    @Mock
    ExpenseFactory expenseFactory;

    @InjectMocks
    InvoiceRegistrationService invoiceRegistrationService;

    @Test
    void shouldAddInvoiceRegistration()
    {
        // Arrange
        ExpenseId id = new ExpenseId(1L);
        ExpenseType type = new ExpenseType("InvoiceRegistration");
        Date date = new Date(LocalDate.of(2024, 7, 24));
        ExpenseSupplier supplier = new ExpenseSupplier("Vodafone");
        InvoiceNumber number = new InvoiceNumber("1234");
        ExpenseCategory category = new ExpenseCategory("House");
        Values value = new Values(40.00);
        ExpenseStatus status = new ExpenseStatus(false);
        InvoiceRegistration invoiceRegistration = new InvoiceRegistration(id, type, date, supplier, number, category, value, status);
        when(expenseFactory.createExpense(id, type, supplier, category, value, number, date, status)).thenReturn(invoiceRegistration);
        when(expenseRepo.save(invoiceRegistration)).thenReturn(invoiceRegistration);

        // Act
        InvoiceRegistration result = (InvoiceRegistration) invoiceRegistrationService.add(id, type, date, supplier, number, category, value, status);

        // Assert
        assertEquals(1, result.getId().getId());
        assertEquals("InvoiceRegistration", result.getType().getType());
        assertEquals(LocalDate.of(2024, 7, 24), result.getDate().getDate());
        assertEquals("Vodafone", result.getSupplier().getName());
        assertEquals("1234", result.getNumber().getNumber());
        assertEquals("House", result.getCategory().getCategory());
        assertEquals(40.00, result.getValue().getValue());
    }

    @Test
    void shouldFindBySupplier()
    {
        // Arrange
        String supplierName = "Vodafone";
        ExpenseId id = new ExpenseId(1L);
        ExpenseType type = new ExpenseType("InvoiceRegistration");
        Date date = new Date(LocalDate.of(2024, 7, 24));
        ExpenseSupplier supplier = new ExpenseSupplier(supplierName);
        InvoiceNumber number = new InvoiceNumber("1234");
        ExpenseCategory category = new ExpenseCategory("House");
        Values value = new Values(40.00);
        ExpenseStatus status = new ExpenseStatus(false);
        InvoiceRegistration invoice1 = new InvoiceRegistration(id, type, date, supplier, number, category, value, status);
        List<Expense> expectedInvoices = Arrays.asList(invoice1);
        when(expenseFactory.createExpenseSupplier(supplierName)).thenReturn(Optional.of(supplier));
        when(expenseRepo.findBySupplier(supplierName)).thenReturn(expectedInvoices);

        // Act
        Iterable<Expense> result = invoiceRegistrationService.findBySupplier(supplierName);

        // Assert
        assertIterableEquals(expectedInvoices, result);
    }

    @Test
    void shouldReturnsEmptyListFindBySupplier()
    {
        // Arrange
        String nonExistingName = "XPTO";
        ExpenseSupplier supplier = new ExpenseSupplier(nonExistingName);
        when(expenseFactory.createExpenseSupplier(nonExistingName)).thenReturn(Optional.of(supplier));
        when(expenseRepo.findBySupplier(supplier.getName())).thenReturn(Collections.EMPTY_LIST);

        // Act
        Iterable<Expense> result = invoiceRegistrationService.findBySupplier(nonExistingName);

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldDelete()
    {
        // Arrange
        long expenseId = 1L;
        when(expenseRepo.delete(expenseId)).thenReturn(true);

        // Act
        boolean result = expenseRepo.delete(expenseId);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotDelete()
    {
        // Arrange
        long nonExistingExpenseId = 999L;
        when(expenseRepo.delete(nonExistingExpenseId)).thenReturn(false);

        // Act
        boolean result = expenseRepo.delete(nonExistingExpenseId);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldFinAll()
    {
        // Arrange
        ExpenseId id1 = new ExpenseId(1L);
        ExpenseType type1 = new ExpenseType("InvoiceRegistration");
        Date date1 = new Date(LocalDate.of(2024, 7, 24));
        ExpenseSupplier supplier1 = new ExpenseSupplier("Vodafone");
        InvoiceNumber number1 = new InvoiceNumber("1234");
        ExpenseCategory category1 = new ExpenseCategory("House");
        Values value1 = new Values(40.00);
        ExpenseStatus status1 = new ExpenseStatus(false);
        ExpenseId id2 = new ExpenseId(2L);
        ExpenseType type2 = new ExpenseType("InvoiceRegistration");
        Date date2 = new Date(LocalDate.of(2024, 7, 24));
        ExpenseSupplier supplier2 = new ExpenseSupplier("Vodafone");
        InvoiceNumber number2 = new InvoiceNumber("4321");
        ExpenseCategory category2 = new ExpenseCategory("House");
        Values value2 = new Values(40.00);
        ExpenseStatus status2 = new ExpenseStatus(false);
        InvoiceRegistration invoice1 = new InvoiceRegistration(id1, type1, date1, supplier1, number1, category1, value1, status1);
        InvoiceRegistration invoice2 = new InvoiceRegistration(id2, type2, date2, supplier2, number2, category2, value2, status2);
        List<Expense> expectedInvoices = Arrays.asList(invoice1, invoice2);
        when(expenseRepo.findAll()).thenReturn(expectedInvoices);

        // Act
        Iterable<Expense> result = invoiceRegistrationService.findAll();

        // Assert
        assertIterableEquals(expectedInvoices, result);
    }

    @Test
    void shouldReturnsEmptyListFindAll()
    {
        // Act
        Iterable<Expense> result = invoiceRegistrationService.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldFindById()
    {
        // Arrange
        long idLong = 1L;
        ExpenseId id = new ExpenseId(idLong);
        ExpenseType type = new ExpenseType("InvoiceRegistration");
        Date date = new Date(LocalDate.of(2024, 7, 24));
        ExpenseSupplier supplier = new ExpenseSupplier("Vodafone");
        InvoiceNumber number = new InvoiceNumber("1234");
        ExpenseCategory category = new ExpenseCategory("House");
        Values value = new Values(40.00);
        ExpenseStatus status = new ExpenseStatus(false);
        InvoiceRegistration invoice = new InvoiceRegistration(id, type, date, supplier, number, category, value, status);
        when(expenseFactory.createExpenseId(idLong)).thenReturn(Optional.of(id));
        when(expenseRepo.findById(id)).thenReturn(Optional.of(invoice));

        // Act
        Expense result = invoiceRegistrationService.findById(idLong);

        // Assert
        assertEquals(invoice, result);
    }

    @Test
    void shouldReturnsEmptyListFindById()
    {
        // Arrange
        long nonExistingInvoiceId = 999L;
        ExpenseId id = new ExpenseId(nonExistingInvoiceId);
        when(expenseFactory.createExpenseId(nonExistingInvoiceId)).thenReturn(Optional.of(id));
        when(expenseRepo.findById(id)).thenReturn(Optional.empty());

        // Act
        Expense result = invoiceRegistrationService.findById(nonExistingInvoiceId);

        // Assert
        assertNull(result);
    }
}
