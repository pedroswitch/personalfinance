package com.example.personalfinance.service;

import com.example.personalfinance.domain.payment.Payment;

public interface PaymentOperations<T extends Payment> extends Operations<T>
{
    Iterable<T> findBySupplier(String supplier);
}
