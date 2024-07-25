package com.example.personalfinance.service;

import com.example.personalfinance.domain.budget.Budget;
import com.example.personalfinance.domain.budget.BudgetFactory;
import com.example.personalfinance.domain.repository.BudgetRepo;
import com.example.personalfinance.domain.valueobjects.BudgetCategory;
import com.example.personalfinance.domain.valueobjects.BudgetId;
import com.example.personalfinance.domain.valueobjects.Values;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BudgetServiceTest {

    @Mock
    private BudgetRepo budgetRepo;

    @Mock
    private BudgetFactory budgetFactory;

    @Mock
    private Budget budget;

    @InjectMocks
    private BudgetService budgetService;

    @Test
    void shouldAddBudget()
    {
        // Arrange
        BudgetId id = new BudgetId(1);
        BudgetCategory category = new BudgetCategory("House");
        Values value = new Values(1000.00);
        when(budget.getId()).thenReturn(id);
        when(budget.getCategory()).thenReturn(category);
        when(budget.getValue()).thenReturn(value);
        when(budgetFactory.createBudget(id, category, value)).thenReturn(budget);
        when(budgetRepo.save(budget)).thenReturn(budget);

        // Act
        Budget result = budgetService.add(id, category, value);

        // Assert
        assertEquals(1, result.getId().getId());
        assertEquals("House", result.getCategory().getCategory());
        assertEquals(1000.00, result.getValue().getValue());
    }

    @Test
    void shouldFindByCategory()
    {
        // Arrange
        String categoryString = "House";
        BudgetCategory category = new BudgetCategory(categoryString);
        Budget budget1 = mock(Budget.class);
        Budget budget2 = mock(Budget.class);
        List<Budget> expectedBudgets = Arrays.asList(budget1, budget2);
        when(budgetFactory.createBudgetCategory(categoryString)).thenReturn(Optional.of(category));
        when(budgetRepo.findByCategory(categoryString)).thenReturn(expectedBudgets);

        // Act
        Iterable<Budget> result = budgetService.findByCategory(categoryString);

        // Assert
        assertIterableEquals(expectedBudgets, result);
    }

    @Test
    void shouldReturnsEmptyListFindByCategory()
    {
        // Arrange
        String invalidCategory = "InvalidCategory";
        when(budgetFactory.createBudgetCategory(invalidCategory)).thenReturn(Optional.empty());

        // Act
        Iterable<Budget> result = budgetService.findByCategory(invalidCategory);

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldDelete() {
        // Arrange
        long budgetId = 1L;
        when(budgetRepo.delete(budgetId)).thenReturn(true);

        // Act
        boolean result = budgetService.delete(budgetId);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotDelete() {
        // Arrange
        long nonExistingBudgetId = 999L;
        when(budgetRepo.delete(nonExistingBudgetId)).thenReturn(false);

        // Act
        boolean result = budgetService.delete(nonExistingBudgetId);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldFindAll()
    {
        // Arrange
        Budget budget1 = mock(Budget.class);
        Budget budget2 = mock(Budget.class);
        List<Budget> expectedBudgets = Arrays.asList(budget1, budget2);
        when(budgetRepo.findAll()).thenReturn(expectedBudgets);

        // Act
        Iterable<Budget> result = budgetService.findAll();

        // Assert
        assertIterableEquals(expectedBudgets, result);
    }

    @Test
    void shouldReturnsEmptyListFindAll()
    {
        // Act
        Iterable<Budget> result = budgetService.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }
}
