package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.IncomeFactory;
import com.example.personalfinance.domain.income.SideGig;
import com.example.personalfinance.domain.repository.IncomeRepo;
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
public class SideGigServiceTest
{
    @Mock
    IncomeRepo incomeRepo;

    @Mock
    IncomeFactory incomeFactory;

    @InjectMocks
    SideGigService sideGigService;

    @Test
    void shouldAddSideGig()
    {
        // Arrange
        IncomeId id = new IncomeId(1L);
        IncomeType type = new IncomeType("SideGig");
        Date date = new Date(LocalDate.of(2024,07,28));
        SideGigName name = new SideGigName("Game");
        Values value = new Values(100.00);
        SideGig sideGig = new SideGig(id, type, date, value, name);
        when(incomeFactory.createIncome(id, type, date, value, name)).thenReturn(sideGig);
        when(incomeRepo.save(sideGig)).thenReturn(sideGig);

        // Act
        SideGig result = (SideGig) sideGigService.add(id, type, date, value, name);

        // Assert
        assertEquals(1, result.getId().getId());
        assertEquals("SideGig", result.getType().getType());
        assertEquals(LocalDate.of(2024,7,28), result.getDate().getDate());
        assertEquals("Game", result.getName().getName());
        assertEquals(100.00, result.getValue().getValue());
    }

    @Test
    void shouldFindByName()
    {
        // Arrange
        String sideGigName = "Game";
        IncomeId id = new IncomeId(1L);
        IncomeType type = new IncomeType("SideGig");
        Date date = new Date(LocalDate.of(2024,07,28));
        SideGigName name = new SideGigName(sideGigName);
        Values value = new Values(100.00);
        SideGig sideGig1 = new SideGig(id, type, date, value, name);
        List<Income> expectedSideGigs = Arrays.asList(sideGig1);
        when(incomeFactory.createSideGigName(sideGigName)).thenReturn(Optional.of(name));
        when(incomeRepo.findByName(sideGigName)).thenReturn(expectedSideGigs);

        // Act
        Iterable<Income> result = sideGigService.findByName(sideGigName);

        // Assert
        assertIterableEquals(expectedSideGigs, result);
    }

    @Test
    void shouldReturnsEmptyListFindByName()
    {
        // Arrange
        String nonExistingName = "XPTO";
        SideGigName name = new SideGigName(nonExistingName);
        when(incomeFactory.createSideGigName(nonExistingName)).thenReturn(Optional.of(name));
        when(incomeRepo.findByName(name.getName())).thenReturn(Collections.EMPTY_LIST);

        // Act
        Iterable<Income> result = sideGigService.findByName(nonExistingName);

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldDelete()
    {
        // Arrange
        long incomeId = 1L;
        when(incomeRepo.delete(incomeId)).thenReturn(true);

        // Act
        boolean result = sideGigService.delete(incomeId);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotDelete()
    {
        // Arrange
        long nonExistingIncomeId = 999L;;
        when(incomeRepo.delete(nonExistingIncomeId)).thenReturn(false);

        // Act
        boolean result = sideGigService.delete(nonExistingIncomeId);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldFindAll()
    {
        // Arrange
        IncomeId id1 = new IncomeId(1L);
        IncomeType type1 = new IncomeType("SideGig");
        Date date1 = new Date(LocalDate.of(2024,07,28));
        SideGigName name1 = new SideGigName("Game");
        Values value1 = new Values(100.00);
        IncomeId id2 = new IncomeId(2L);
        IncomeType type2 = new IncomeType("SideGig");
        Date date2 = new Date(LocalDate.of(2024,07,28));
        SideGigName name2 = new SideGigName("Game");
        Values value2 = new Values(100.00);
        SideGig sideGig1 = new SideGig(id1, type1, date1, value1, name1);
        SideGig sideGig2 = new SideGig(id2, type2, date2, value2, name2);
        List<Income> expectedSideGigs = Arrays.asList(sideGig1, sideGig2);
        when(incomeRepo.findAll()).thenReturn(expectedSideGigs);

        // Act
        Iterable<Income> result = sideGigService.findAll();

        // Assert
        assertIterableEquals(expectedSideGigs, result);
    }

    @Test
    void shouldReturnsEmptyListFindAll()
    {
        // Act
        Iterable<Income> result = sideGigService.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }
}
