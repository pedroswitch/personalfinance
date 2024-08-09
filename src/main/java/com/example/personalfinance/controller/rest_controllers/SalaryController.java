package com.example.personalfinance.controller.rest_controllers;

import com.example.personalfinance.controller.rest_controllers.resources.SalaryResourceHandler;
import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.Salary;
import com.example.personalfinance.domain.valueobjects.*;
import com.example.personalfinance.mapper.IncomeDto;
import com.example.personalfinance.mapper.IncomeMappper;
import com.example.personalfinance.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/salary")
@AllArgsConstructor
public class SalaryController
{
    private final SalaryService salaryService;

    @PostMapping
    public ResponseEntity<IncomeDto> addSalary(@RequestBody IncomeDto dto)
    {
        Salary salary = (Salary) IncomeMappper.dtoToSalary(dto);
        Salary salaryToAdd = (Salary) salaryService.add(salary.getId(), salary.getType(), salary.getDate(), salary.getValue(), salary.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                SalaryResourceHandler.manageAdd(IncomeMappper.salaryToIncomeDto(salaryToAdd))
        );
    }

    @GetMapping(params = "employerName")
    public ResponseEntity<Iterable<IncomeDto>> findSalariesByEmployerName(@RequestParam String employerName)
    {
        Iterable<Income> salaries = salaryService.findByName(employerName);
        Iterable<IncomeDto> salaryDtos = SalaryResourceHandler.manageFindAll(IncomeMappper.salaryListToDto(salaries));
        return ResponseEntity.status(HttpStatus.OK).body(salaryDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id)
    {
        boolean deleted = salaryService.delete(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping
    public ResponseEntity<Iterable<IncomeDto>> findAll()
    {
        Iterable<Income> salaries = salaryService.findAll();
        Iterable<IncomeDto> salaryDtos = SalaryResourceHandler.manageFindAll(IncomeMappper.salaryListToDto(salaries));
        return ResponseEntity.status(HttpStatus.OK).body(salaryDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findSalaryById(@PathVariable long id)
    {
        Income salary = salaryService.findById(id);
        if (salary == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                SalaryResourceHandler.manageFindById(IncomeMappper.salaryToIncomeDto(salary))
        );
    }
}
