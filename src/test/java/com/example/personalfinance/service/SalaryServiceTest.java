package com.example.personalfinance.service;

import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.IncomeFactory;
import com.example.personalfinance.domain.income.Salary;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SalaryServiceTest
{
    @Mock
    IncomeRepo incomeRepo;

    @Mock
    IncomeFactory incomeFactory;

    @InjectMocks
    SalaryService salaryService;

    @Test
    void shouldAddSalary()
    {
        // Arrange
        IncomeId id = new IncomeId(1L);
        IncomeType type = new IncomeType("Salary");
        Date date = new Date(LocalDate.of(2024,7,28));
        Values value = new Values(1300.00);
        EmployerName name = new EmployerName("XPTO");
        Salary salary = new Salary(id, type, date, value, name);
        when(incomeFactory.createIncome(id, type, date, value, name)).thenReturn(salary);
        when(incomeRepo.save(salary)).thenReturn(salary);

        // Act
        Salary result = (Salary) salaryService.add(id, type, date, value, name);

        // Assert
        assertEquals(1, result.getId().getId());
        assertEquals("Salary", result.getType().getType());
        assertEquals(LocalDate.of(2024,7,28), result.getDate().getDate());
        assertEquals(1300.00, result.getValue().getValue());
        assertEquals("XPTO", result.getName().getName());
    }

    @Test
    void shouldFindByName()
    {
        // Arrange
        String employerName = "XPTO";
        IncomeId id = new IncomeId(1L);
        IncomeType type = new IncomeType("Salary");
        Date date = new Date(LocalDate.of(2024,7,28));
        Values value = new Values(1300.00);
        EmployerName name = new EmployerName(employerName);
        Salary salary1 = new Salary(id, type, date, value, name);
        List<Income> expectedSalaries = Arrays.asList(salary1);
        when(incomeFactory.createEmployerName(employerName)).thenReturn(Optional.of(name));
        when(incomeRepo.findByName(employerName)).thenReturn(expectedSalaries);

        // Act
        Iterable<Income> result = salaryService.findByName(employerName);

        // Assert
        assertIterableEquals(expectedSalaries, result);
    }

    @Test
    void shouldReturnsEmptyListFindByName()
    {
        // Arrange
        String invalidName = "OTPX";
        EmployerName name = new EmployerName(invalidName);
        when(incomeFactory.createEmployerName(invalidName)).thenReturn(Optional.of(name));
        when(incomeRepo.findByName(name.getName())).thenReturn(Collections.EMPTY_LIST);

        // Act
        Iterable<Income> result = salaryService.findByName(invalidName);

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
        boolean result = salaryService.delete(incomeId);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotDelete()
    {
        // Arrange
        long nonExistingIncomeId = 999L;
        when(incomeRepo.delete(nonExistingIncomeId)).thenReturn(false);

        // Act
        boolean result = salaryService.delete(nonExistingIncomeId);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldFindAll()
    {
        // Arrange
        IncomeId id1 = new IncomeId(1L);
        IncomeType type1 = new IncomeType("Salary");
        Date date1 = new Date(LocalDate.of(2024,7,28));
        Values value1 = new Values(1300.00);
        EmployerName name1 = new EmployerName("XPTO");
        IncomeId id2 = new IncomeId(2L);
        IncomeType type2 = new IncomeType("Salary");
        Date date2 = new Date(LocalDate.of(2024,7,28));
        Values value2 = new Values(1300.00);
        EmployerName name2 = new EmployerName("XPTO");
        Salary salary1 = new Salary(id1, type1, date1, value1, name1);
        Salary salary2 = new Salary(id2, type2, date2, value2, name2);
        List<Income> expectedSalaries = Arrays.asList(salary1, salary2);
        when(incomeRepo.findAll()).thenReturn(expectedSalaries);

        // Act
        Iterable<Income> result = salaryService.findAll();

        // Assert
        assertIterableEquals(expectedSalaries, result);
    }

    @Test
    void shouldReturnsEmptyListFindAll()
    {
        // Act
        Iterable<Income> result = salaryService.findAll();

        // Act
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    void shouldFindById()
    {
        // Arrange
        long idLong = 1L;
        IncomeId id = new IncomeId(idLong);
        IncomeType type = new IncomeType("Salary");
        Date date = new Date(LocalDate.of(2024,7,28));
        Values value = new Values(1300.00);
        EmployerName name = new EmployerName("XPTO");
        Salary salary = new Salary(id, type, date, value, name);
        when(incomeFactory.createIncomeId(idLong)).thenReturn(Optional.of(id));
        when(incomeRepo.findById(id)).thenReturn(Optional.of(salary));

        // Act
        Income result = salaryService.findById(idLong);

        // Assert
        assertEquals(salary, result);
    }

    @Test
    void shouldReturnsEmptyListFindById()
    {
        // Arrange
        long nonExistingSalaryId = 999L;
        IncomeId id = new IncomeId(nonExistingSalaryId);
        when(incomeFactory.createIncomeId(nonExistingSalaryId)).thenReturn(Optional.of(id));
        when(incomeRepo.findById(id)).thenReturn(Optional.empty());

        // Act
        Income result = salaryService.findById(nonExistingSalaryId);

        // Assert
        assertNull(result);
    }

}
