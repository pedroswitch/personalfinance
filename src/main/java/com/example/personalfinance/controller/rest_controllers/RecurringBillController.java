package com.example.personalfinance.controller.rest_controllers;

import com.example.personalfinance.controller.rest_controllers.resources.RecurringBillResourceHandler;
import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.RecurringBill;
import com.example.personalfinance.mapper.ExpenseDto;
import com.example.personalfinance.mapper.ExpenseMapper;
import com.example.personalfinance.service.RecurringBillService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recurringbill")
@AllArgsConstructor
public class RecurringBillController
{
    private final RecurringBillService recurringBillService;

    @PostMapping
    public ResponseEntity<ExpenseDto> addRecurringBill(@RequestBody ExpenseDto dto)
    {
        RecurringBill recBill = (RecurringBill) ExpenseMapper.dtoToRecBill(dto);
        RecurringBill recBill1ToAdd = (RecurringBill) recurringBillService.add(recBill.getId(), recBill.getType(), recBill.getSupplier(), recBill.getCategory(), recBill.getValue(), recBill.getInitialDate(), recBill.getFinalDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                RecurringBillResourceHandler.manageAdd(ExpenseMapper.recBillToExpenseDto(recBill1ToAdd))
        );
    }

    @GetMapping(params = "supplier")
    public ResponseEntity<Iterable<ExpenseDto>> findRecBillsBySupplier(@RequestParam String supplier)
    {
        Iterable<Expense> recBills = recurringBillService.findBySupplier(supplier);
        Iterable<ExpenseDto> recBillDtos = RecurringBillResourceHandler.manageFindAll(ExpenseMapper.recBillListToDto(recBills));
        return ResponseEntity.status(HttpStatus.OK).body(recBillDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id)
    {
        boolean deleted = recurringBillService.delete(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping
    public ResponseEntity<Iterable<ExpenseDto>> findAll()
    {
        Iterable<Expense> recBills = recurringBillService.findAll();
        Iterable<ExpenseDto> recBillDtos = RecurringBillResourceHandler.manageFindAll(ExpenseMapper.recBillListToDto(recBills));
        return ResponseEntity.status(HttpStatus.OK).body(recBillDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findRecBillById(@PathVariable long id)
    {
        Expense recBill = recurringBillService.findById(id);
        if (recBill == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                RecurringBillResourceHandler.manageFindId(ExpenseMapper.recBillToExpenseDto(recBill))
        );
    }
}
