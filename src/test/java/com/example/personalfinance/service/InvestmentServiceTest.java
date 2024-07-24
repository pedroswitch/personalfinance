package com.example.personalfinance.service;

import com.example.personalfinance.domain.investment.Investment;
import com.example.personalfinance.domain.investment.InvestmentFactory;
import com.example.personalfinance.domain.investment.InvestmentTest;
import com.example.personalfinance.domain.repository.InvestmentRepo;
import com.example.personalfinance.domain.valueobjects.*;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvestmentServiceTest
{
    @Mock
    private InvestmentRepo investmentRepo;

    @Mock
    private InvestmentFactory investmentFactory;

    @Mock
    Investment investment;

    @InjectMocks
    InvestmentService investmentService;

    @Test
    void shouldAddInvestment(){
        // Arrange
        InvestmentId id = new InvestmentId(1L);
        InvestmentCategory category = new InvestmentCategory("Stocks");
        InvestmentDescription description = new InvestmentDescription("MSFT");
        Date purchaseDate = new Date(LocalDate.of(2024, 07, 24));
        Date saleDate = new Date(LocalDate.of(1900, 1, 1));
        InvestmentQty qty = new InvestmentQty(100);
        Values value = new Values(10000.00);
        when(investment.getId()).thenReturn(id);
        when(investment.getCategory()).thenReturn(category);
        when(investment.getDescription()).thenReturn(description);
        when(investment.getPurchaseDate()).thenReturn(purchaseDate);
        when(investment.getSaleDate()).thenReturn(saleDate);
        when(investment.getQty()).thenReturn(qty);
        when(investment.getValue()).thenReturn(value);
        when(investmentFactory.createInvestment(id, category, description, purchaseDate, saleDate, qty, value)).thenReturn(investment);
        when(investmentRepo.save(investment)).thenReturn(investment);

        // Act
        Investment result = investmentService.add(id, category, description, purchaseDate, saleDate, qty, value);

        // Assert
        assertEquals(1, result.getId().getId());
        assertEquals("Stocks", result.getCategory().getCategory());
        assertEquals("MSFT", result.getDescription().getDescription());
        assertEquals(LocalDate.of(2024, 07, 24), result.getPurchaseDate().getDate());
        assertEquals(LocalDate.of(1900, 1, 1), result.getSaleDate().getDate());
        assertEquals(100, result.getQty().getQty());
        assertEquals(10000.00, result.getValue().getValue());
    }

    @Test
    void shouldFindAllByCategory()
    {
        String categoryString = "Stocks";
        InvestmentCategory category = new InvestmentCategory(categoryString);
        Investment investment1 = mock(Investment.class);
        Investment investment2 = mock(Investment.class);
        List<Investment> expectedInvestments = Arrays.asList(investment1, investment2);
        when(investmentFactory.createInvestmentCategory(categoryString)).thenReturn(Optional.of(category));
        when(investmentRepo.findAllByCategory(categoryString)).thenReturn(expectedInvestments);

        // Act
        Iterable<Investment> result = investmentService.findAllByCategory(categoryString);

        // Assert
        assertIterableEquals(expectedInvestments, result);
    }

    @Test
    void shouldReturnsEmptyListFindAllByCategory()
    {
        // Arrange
        String invalidCategory = "InvalidCategory";
        when(investmentFactory.createInvestmentCategory(invalidCategory)).thenReturn(Optional.empty());

        // Act
        Iterable<Investment> result = investmentService.findAllByCategory(invalidCategory);

        // Act
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldDelete()
    {
        // Arrange
        long investmentId = 1L;
        when(investmentRepo.delete(investmentId)).thenReturn(true);

        // Act
        boolean result = investmentRepo.delete(investmentId);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldNotDelete()
    {
        // Arrange
        long nonExistingBudgetId = 999L;
        when(investmentRepo.delete(nonExistingBudgetId)).thenReturn(false);

        // Act
        boolean result = investmentRepo.delete(nonExistingBudgetId);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldFindAll()
    {
        // Arrange
        Investment investment1 = mock(Investment.class);
        Investment investment2 = mock(Investment.class);
        List<Investment> expectedInvestments = Arrays.asList(investment1, investment2);
        when(investmentRepo.findAll()).thenReturn(expectedInvestments);

        // Act
        Iterable<Investment> result = investmentRepo.findAll();

        // Assert
        assertIterableEquals(expectedInvestments, result);
    }

    @Test
    void shouldNotFindAll()
    {
        // Act
        Iterable<Investment> result = investmentRepo.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }
}
