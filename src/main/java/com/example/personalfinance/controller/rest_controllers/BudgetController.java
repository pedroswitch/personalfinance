package com.example.personalfinance.controller.rest_controllers;

import com.example.personalfinance.controller.rest_controllers.resources.BudgetResourceHandler;
import com.example.personalfinance.domain.budget.Budget;
import com.example.personalfinance.mapper.BudgetDto;
import com.example.personalfinance.mapper.BudgetMapper;
import com.example.personalfinance.service.BudgetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/budget")
@AllArgsConstructor
public class BudgetController
{
    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<Object> addBudget(@RequestBody BudgetDto dto)
    {
        Budget budget = BudgetMapper.dtoToBudget(dto);
        Budget budgetToAdd = budgetService.add(budget.getId(), budget.getCategory(), budget.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BudgetResourceHandler.manageAdd(BudgetMapper.budgetToDto(budgetToAdd))
        );
    }

    @GetMapping(params = "category")
    public ResponseEntity<Iterable<BudgetDto>> findBudgetsByCategory(@RequestParam String category)
    {
        Iterable<Budget> budgets = budgetService.findByCategory(category);
        Iterable<BudgetDto> budgetDtos = BudgetResourceHandler.manageFindAll(BudgetMapper.budgetListToDto(budgets));
        return ResponseEntity.status(HttpStatus.OK).body(budgetDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id) {
        boolean deleted = budgetService.delete(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping
    public ResponseEntity<Iterable<BudgetDto>> findAll()
    {
        Iterable<Budget> budgets = budgetService.findAll();
        Iterable<BudgetDto> budgetDtos = BudgetResourceHandler.manageFindAll(BudgetMapper.budgetListToDto(budgets));
        return ResponseEntity.status(HttpStatus.OK).body(budgetDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBudgetById(@PathVariable long id)
    {
        Budget budget = budgetService.findById(id);
        if (budget == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Budget with id " + id + " not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(BudgetResourceHandler.manageFindById(BudgetMapper.budgetToDto(budget)));
    }

}
