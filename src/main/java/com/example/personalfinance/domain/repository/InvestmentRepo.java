package com.example.personalfinance.domain.repository;

import com.example.personalfinance.ddd.Repository;
import com.example.personalfinance.domain.investment.Investment;
import com.example.personalfinance.domain.valueobjects.InvestmentId;

public interface InvestmentRepo extends Repository<InvestmentId, Investment>
{
    Iterable<Investment> findByCategory(String category);
    boolean delete(long id);
}
