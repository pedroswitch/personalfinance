package com.example.personalfinance.service;

import com.example.personalfinance.domain.investment.Investment;

import java.util.List;

public interface InvestmentOperations <T extends Investment> extends Operations<T> {
    List<T> findByCategory(String category);
}
