package com.example.personalfinance.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepoSpringDataInterface extends JpaRepository<BudgetDataModel, Long>
{
}
