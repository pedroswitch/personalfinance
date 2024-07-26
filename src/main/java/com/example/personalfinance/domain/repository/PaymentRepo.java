package com.example.personalfinance.domain.repository;

import com.example.personalfinance.ddd.Repository;
import com.example.personalfinance.domain.payment.Payment;
import com.example.personalfinance.domain.valueobjects.PaymentId;

public interface PaymentRepo extends Repository<PaymentId, Payment>
{
    Iterable<Payment> findPaymentsBySupplier(String supplier);
    boolean delete(long id);
}
