package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.ExpenseFactory;
import com.example.personalfinance.domain.expense.RecurringBill;
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
public class RecurringBillServiceTest
{
    @Mock
    ExpenseRepo expenseRepo;

    @Mock
    ExpenseFactory expenseFactory;

    @InjectMocks
    RecurringBillService recurringBillService;

    @Test
    void shouldAddRecurringBill()
    {
        // Arrange
        ExpenseId id = new ExpenseId(1L);
        ExpenseType type = new ExpenseType("RecurringBill");
        ExpenseSupplier supplier = new ExpenseSupplier("Vodafone");
        ExpenseCategory category = new ExpenseCategory("House");
        Values value = new Values(40.00);
        Date initialDate = new Date(LocalDate.of(2024,7,27));
        Date finalDate = new Date(LocalDate.of(2025, 6, 30));
        RecurringBill recurringBill = new RecurringBill(id, type, supplier, category, value, initialDate, finalDate);
        when(expenseFactory.createExpense(id, type, supplier, category, value, initialDate, finalDate)).thenReturn(recurringBill);
        when(expenseRepo.save(recurringBill)).thenReturn(recurringBill);

        // Act
        RecurringBill result = (RecurringBill) recurringBillService.add(id, type, supplier, category, value, initialDate, finalDate);

        // Assert
        assertEquals(1, result.getId().getId());
        assertEquals("RecurringBill", result.getType().getType());
        assertEquals("Vodafone", result.getSupplier().getName());
        assertEquals("House", result.getCategory().getCategory());
        assertEquals(40.00, result.getValue().getValue());
        assertEquals(LocalDate.of(2024, 7, 27), result.getInitialDate().getDate());
        assertEquals(LocalDate.of(2025, 6, 30), result.getFinalDate().getDate());
    }

    @Test
    void shouldFindBySupplier()
    {
        // Arrange
        String supplierName = "Vodafone";
        ExpenseId id = new ExpenseId(1L);
        ExpenseType type = new ExpenseType("RecurringBill");
        ExpenseSupplier supplier = new ExpenseSupplier(supplierName);
        ExpenseCategory category = new ExpenseCategory("House");
        Values value = new Values(40.00);
        Date initialDate = new Date(LocalDate.of(2024,7,27));
        Date finalDate = new Date(LocalDate.of(2025, 6, 30));
        RecurringBill recurringBill1 = new RecurringBill(id, type, supplier, category, value, initialDate, finalDate);
        List<Expense> expectedRecBills = Arrays.asList(recurringBill1);
        when(expenseFactory.createExpenseSupplier(supplierName)).thenReturn(Optional.of(supplier));
        when(expenseRepo.findBySupplier(supplierName)).thenReturn(expectedRecBills);

        // Act
        Iterable<Expense> result = recurringBillService.findBySupplier(supplierName);

        // Assert
        assertIterableEquals(expectedRecBills, result);
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
        Iterable<Expense> result = recurringBillService.findBySupplier(nonExistingName);

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
    void shouldFindAll()
    {
        // Arrange
        ExpenseId id1 = new ExpenseId(1L);
        ExpenseType type1 = new ExpenseType("RecurringBill");
        ExpenseSupplier supplier1 = new ExpenseSupplier("Vodafone");
        ExpenseCategory category1 = new ExpenseCategory("House");
        Values value1 = new Values(40.00);
        Date initialDate1 = new Date(LocalDate.of(2024,7,27));
        Date finalDate1 = new Date(LocalDate.of(2025, 6, 30));
        ExpenseId id2 = new ExpenseId(2L);
        ExpenseType type2 = new ExpenseType("RecurringBill");
        ExpenseSupplier supplier2 = new ExpenseSupplier("Vodafone");
        ExpenseCategory category2 = new ExpenseCategory("Car");
        Values value2 = new Values(20.00);
        Date initialDate2 = new Date(LocalDate.of(2024,7,27));
        Date finalDate2 = new Date(LocalDate.of(2025, 6, 30));
        RecurringBill recurringBill1 = new RecurringBill(id1, type1, supplier1, category1, value1, initialDate1, finalDate1);
        RecurringBill recurringBill2 = new RecurringBill(id2, type2, supplier2, category2, value2, initialDate2, finalDate2);
        List<Expense> expectedRecBills = Arrays.asList(recurringBill1, recurringBill2);
        when(expenseRepo.findAll()).thenReturn(expectedRecBills);

        // Act
        Iterable<Expense> result = recurringBillService.findAll();

        // Assert
        assertIterableEquals(expectedRecBills, result);
    }

    @Test
    void shouldReturnsEmptyListFindAll()
    {
        // Act
        Iterable<Expense> result = recurringBillService.findAll();

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
        ExpenseType type = new ExpenseType("RecurringBill");
        ExpenseSupplier supplier = new ExpenseSupplier("Vodafone");
        ExpenseCategory category = new ExpenseCategory("House");
        Values value = new Values(40.00);
        Date initialDate = new Date(LocalDate.of(2024,7,27));
        Date finalDate = new Date(LocalDate.of(2025, 6, 30));
        RecurringBill recurringBill = new RecurringBill(id, type, supplier, category, value, initialDate, finalDate);
        when(expenseFactory.createExpenseId(id.id)).thenReturn(Optional.of(id));
        when(expenseRepo.findById(id)).thenReturn(Optional.of(recurringBill));

        // Act
        Expense result = recurringBillService.findById(idLong);

        // Assert
        assertEquals(recurringBill, result);
    }

    @Test
    void shouldReturnsEmptyListFindById()
    {
        // Arrange
        long nonExistingRecBillId = 999L;
        ExpenseId id = new ExpenseId(nonExistingRecBillId);
        when(expenseFactory.createExpenseId(nonExistingRecBillId)).thenReturn(Optional.of(id));
        when(expenseRepo.findById(id)).thenReturn(Optional.empty());

        // Act
        Expense result = recurringBillService.findById(nonExistingRecBillId);

        // Assert
        assertNull(result);
    }

}
