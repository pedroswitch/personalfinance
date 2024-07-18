package com.example.personalfinance.domain.investment;

import com.example.personalfinance.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class InvestmentTest
{
    InvestmentId id;
    InvestmentCategory category;
    InvestmentDescription description;
    Date purchaseDate;
    Date saleDate;
    InvestmentQty qty;
    Values value;

    @BeforeEach
    void setup()
    {
        id = new InvestmentId(1L);
        category = new InvestmentCategory("Stock");
        description = new InvestmentDescription("MSFT");
        purchaseDate = new Date(LocalDate.of(2024, 7, 10));
        saleDate = new Date(LocalDate.of(1900, 1, 1));
        qty = new InvestmentQty(10);
        value = new Values(100.00);
    }

    @Test
    void shouldCreateInvestment()
    {
        // Act
        Investment stock = new Investment(id, category, description, purchaseDate, saleDate, qty, value);

        // Assert
        assertNotNull(stock);
    }

    @Test
    void shouldBeSameAs()
    {
        // Arrange
        InvestmentId id2 = new InvestmentId(1L);
        InvestmentCategory category2 = new InvestmentCategory("Stock");
        InvestmentDescription description2 = new InvestmentDescription("MSFT");
        Date purchaseDate2 = new Date(LocalDate.of(2024, 7, 10));
        Date saleDate2 = new Date(LocalDate.of(1900, 1, 1));
        InvestmentQty qty2 = new InvestmentQty(10);
        Values value2 = new Values(100.00);
        Investment stock2 = new Investment(id2, category2, description2, purchaseDate2, saleDate2, qty2, value2);
        Investment stock = new Investment(id, category, description, purchaseDate, saleDate, qty, value);

        // Act
        boolean result = stock.sameAs(stock2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotBeSameAs()
    {
        // Arrange
        InvestmentId id2 = new InvestmentId(2L);
        InvestmentCategory category2 = new InvestmentCategory("Stock");
        InvestmentDescription description2 = new InvestmentDescription("MSFT");
        Date purchaseDate2 = new Date(LocalDate.of(2024, 7, 10));
        Date saleDate2 = new Date(LocalDate.of(1900, 1, 1));
        InvestmentQty qty2 = new InvestmentQty(10);
        Values value2 = new Values(100.00);
        Investment stock2 = new Investment(id2, category2, description2, purchaseDate2, saleDate, qty2, value2);
        Investment stock = new Investment(id, category, description, purchaseDate, saleDate, qty, value);

        // Act
        boolean result = stock.sameAs(stock2);

        // Assert
        assertFalse(result);
    }
}
