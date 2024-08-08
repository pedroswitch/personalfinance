package com.example.personalfinance.controller.rest_controllers;

import com.example.personalfinance.controller.rest_controllers.resources.PaymentResourceHandler;
import com.example.personalfinance.domain.payment.Payment;
import com.example.personalfinance.domain.payment.PaymentFactory;
import com.example.personalfinance.mapper.PaymentDto;
import com.example.personalfinance.mapper.PaymentMapper;
import com.example.personalfinance.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
public class PaymentController
{
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> addPayment(@RequestBody PaymentDto dto)
    {
        Payment payment = PaymentMapper.dtoToPayment(dto);
        Payment paymentToAdd = paymentService.add(payment.getId(), payment.getExpenseId(), payment.getPaymentDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                PaymentResourceHandler.manageAdd(PaymentMapper.paymentToDto(paymentToAdd))
        );
    }

    @GetMapping(params = "supplier")
    public ResponseEntity<Iterable<PaymentDto>> findPaymentsBySupplier(@RequestParam String supplier)
    {
        Iterable<Payment> payments = paymentService.findBySupplier(supplier);
        Iterable<PaymentDto> paymentDtos = PaymentResourceHandler.manageFindAll(PaymentMapper.paymentListToDto(payments));
        return ResponseEntity.status(HttpStatus.OK).body(paymentDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id)
    {
        boolean deleted = paymentService.delete(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping
    public ResponseEntity<Iterable<PaymentDto>> findAll()
    {
        Iterable<Payment> payments = paymentService.findAll();
        Iterable<PaymentDto> paymentDtos = PaymentResourceHandler.manageFindAll(PaymentMapper.paymentListToDto(payments));
        return ResponseEntity.status(HttpStatus.OK).body(paymentDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPaymentById(@PathVariable long id)
    {
        Payment payment = paymentService.findById(id);
        if (payment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                PaymentResourceHandler.manageFindById(PaymentMapper.paymentToDto(payment))
        );
    }
}
