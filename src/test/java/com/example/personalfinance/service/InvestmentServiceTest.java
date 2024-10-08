package com.example.personalfinance.service;

import com.example.personalfinance.domain.investment.Investment;
import com.example.personalfinance.domain.investment.InvestmentFactory;
import com.example.personalfinance.domain.repository.InvestmentRepo;
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
    void shouldFindByCategory()
    {
        String categoryString = "Stocks";
        InvestmentId id = new InvestmentId(1L);
        InvestmentCategory category = new InvestmentCategory(categoryString);
        InvestmentDescription description = new InvestmentDescription("MSFT");
        Date purchaseDate = new Date(LocalDate.of(2024, 07, 24));
        Date saleDate = new Date(LocalDate.of(1900, 1, 1));
        InvestmentQty qty = new InvestmentQty(100);
        Values value = new Values(10000.00);
        Investment investment1 = new Investment(id, category, description, purchaseDate, saleDate, qty, value);
        List<Investment> expectedInvestments = Arrays.asList(investment1);
        when(investmentFactory.createInvestmentCategory(categoryString)).thenReturn(Optional.of(category));
        when(investmentRepo.findByCategory(categoryString)).thenReturn(expectedInvestments);

        // Act
        Iterable<Investment> result = investmentService.findByCategory(categoryString);

        // Assert
        assertIterableEquals(expectedInvestments, result);
    }

    @Test
    void shouldReturnsEmptyListFindByCategory()
    {
        // Arrange
        String nonExistingCategory = "nonExistingCategory";
        InvestmentCategory category = new InvestmentCategory(nonExistingCategory);
        when(investmentFactory.createInvestmentCategory(nonExistingCategory)).thenReturn(Optional.of(category));
        when(investmentRepo.findByCategory(category.getCategory())).thenReturn((Collections.EMPTY_LIST));

        // Act
        Iterable<Investment> result = investmentService.findByCategory(nonExistingCategory);

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
        long nonExistingInvestmentId = 999L;
        when(investmentRepo.delete(nonExistingInvestmentId)).thenReturn(false);

        // Act
        boolean result = investmentRepo.delete(nonExistingInvestmentId);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldFindAll()
    {
        // Arrange
        String categoryString = "Stocks";
        InvestmentId id1 = new InvestmentId(1L);
        InvestmentCategory category1 = new InvestmentCategory(categoryString);
        InvestmentDescription description1 = new InvestmentDescription("MSFT");
        Date purchaseDate1 = new Date(LocalDate.of(2024, 07, 24));
        Date saleDate1 = new Date(LocalDate.of(1900, 1, 1));
        InvestmentQty qty1 = new InvestmentQty(100);
        Values value1 = new Values(10000.00);
        InvestmentId id2 = new InvestmentId(2L);
        InvestmentCategory category2 = new InvestmentCategory(categoryString);
        InvestmentDescription description2 = new InvestmentDescription("ORCL");
        Date purchaseDate2 = new Date(LocalDate.of(2024, 07, 24));
        Date saleDate2 = new Date(LocalDate.of(1900, 1, 1));
        InvestmentQty qty2 = new InvestmentQty(100);
        Values value2 = new Values(10000.00);
        Investment investment1 = new Investment(id1, category1, description1, purchaseDate1, saleDate1, qty1, value1);
        Investment investment2 = new Investment(id2, category2, description2, purchaseDate2, saleDate2, qty2, value2);
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

    @Test
    void shouldFindById()
    {
        // Arrange
        long idLong = 1L;
        InvestmentId id = new InvestmentId(idLong);
        InvestmentCategory category = new InvestmentCategory("Stock");
        InvestmentDescription description = new InvestmentDescription("MSFT");
        Date purchaseDate = new Date(LocalDate.of(2024, 07, 24));
        Date saleDate = new Date(LocalDate.of(1900, 1, 1));
        InvestmentQty qty = new InvestmentQty(100);
        Values value = new Values(10000.00);
        Investment investment = new Investment(id, category, description, purchaseDate, saleDate, qty, value);
        when(investmentFactory.createInvestmentId(idLong)).thenReturn(Optional.of(id));
        when(investmentRepo.findById(id)).thenReturn(Optional.of(investment));

        // Act
        Investment result = investmentService.findById(idLong);

        // Assert
        assertEquals(investment, result);
    }

    @Test
    void shouldReturnsEmptyListFindById()
    {
        // Arrange
        long nonExistingInvestmentId = 999L;
        InvestmentId id = new InvestmentId(nonExistingInvestmentId);
        when(investmentFactory.createInvestmentId(nonExistingInvestmentId)).thenReturn(Optional.of(id));
        when(investmentRepo.findById(id)).thenReturn(Optional.empty());

        // Act
        Investment result = investmentService.findById(nonExistingInvestmentId);

        // Assert
        assertNull(result);
    }
}
