package com.example.personalfinance.controller.rest_controllers;

import com.example.personalfinance.controller.rest_controllers.resources.InvoiceRegistrationResourceHandler;
import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.InvoiceRegistration;
import com.example.personalfinance.mapper.ExpenseDto;
import com.example.personalfinance.mapper.ExpenseMapper;
import com.example.personalfinance.service.InvoiceRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoiceregistration")
@AllArgsConstructor
public class InvoiceRegistrationController
{
    private final InvoiceRegistrationService invoiceRegistrationService;

    @PostMapping
    public ResponseEntity<ExpenseDto> addInvoice(@RequestBody ExpenseDto dto)
    {
        InvoiceRegistration invoice = (InvoiceRegistration) ExpenseMapper.dtoToInvoice(dto);
        InvoiceRegistration invoiceToAdd = (InvoiceRegistration) invoiceRegistrationService.add(invoice.getId(), invoice.getType(), invoice.getDate(), invoice.getSupplier(), invoice.getNumber(), invoice.getCategory(), invoice.getValue(), invoice.getStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                InvoiceRegistrationResourceHandler.manageAdd(ExpenseMapper.invoiceToExpenseDto(invoiceToAdd))
        );
    }

    @GetMapping(params = "supplier")
    public ResponseEntity<Iterable<ExpenseDto>> findInvoicesBySupplier(@RequestParam String supplier)
    {
        Iterable<Expense> invoices = invoiceRegistrationService.findBySupplier(supplier);
        Iterable<ExpenseDto> invoiceDtos = InvoiceRegistrationResourceHandler.manageFindAll(ExpenseMapper.invoiceListToDto(invoices));
        return ResponseEntity.status(HttpStatus.OK).body(invoiceDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id)
    {
        boolean deleted = invoiceRegistrationService.delete(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping
    public ResponseEntity<Iterable<ExpenseDto>> findAll()
    {
        Iterable<Expense> invoices = invoiceRegistrationService.findAll();
        Iterable<ExpenseDto> invoiceDtos = InvoiceRegistrationResourceHandler.manageFindAll(ExpenseMapper.invoiceListToDto(invoices));
        return ResponseEntity.status(HttpStatus.OK).body(invoiceDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findBudgetById(@PathVariable long id)
    {
        Expense invoice = invoiceRegistrationService.findById(id);
        if (invoice == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invoice with id " + id + " not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(InvoiceRegistrationResourceHandler.manageFindId(ExpenseMapper.invoiceToExpenseDto(invoice)));
    }
}
