package com.example.personalfinance.service;

import com.example.personalfinance.domain.expense.ExpenseFactory;
import com.example.personalfinance.domain.payment.Payment;
import com.example.personalfinance.domain.payment.PaymentFactory;
import com.example.personalfinance.domain.repository.PaymentRepo;
import com.example.personalfinance.domain.valueobjects.Date;
import com.example.personalfinance.domain.valueobjects.ExpenseId;
import com.example.personalfinance.domain.valueobjects.ExpenseSupplier;
import com.example.personalfinance.domain.valueobjects.PaymentId;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PaymentService implements PaymentOperations<Payment>
{
    private final PaymentRepo paymentRepo;
    private final PaymentFactory paymentFactory;
    private final ExpenseFactory expenseFactory;

    public PaymentService(@Qualifier("paymentRepoSpringData") PaymentRepo paymentRepo, PaymentFactory paymentFactory,
                          ExpenseFactory expenseFactory)
    {
        this.paymentRepo = paymentRepo;
        this.paymentFactory = paymentFactory;
        this.expenseFactory = expenseFactory;
    }

    public Payment add(PaymentId id, ExpenseId expenseId, Date paymentDate)
    {
        Payment paymentToSave = paymentFactory.createPayment(id, expenseId, paymentDate);
        return paymentRepo.save(paymentToSave);
    }

    @Override
    public Iterable<Payment> findBySupplier(String supplier) {
        Optional<ExpenseSupplier> expenseSupplier = expenseFactory.createExpenseSupplier(supplier);
        return expenseSupplier.map(value -> paymentRepo.findPaymentsBySupplier(value.getName()))
                .orElseGet(Collections::emptyList);
    }

    @Override
    public boolean delete(long id)
    {
        return paymentRepo.delete(id);
    }

    @Override
    public Iterable<Payment> findAll()
    {
        return paymentRepo.findAll();
    }
}
