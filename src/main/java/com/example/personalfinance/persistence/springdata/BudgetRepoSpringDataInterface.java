package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.persistence.datamodel.BudgetDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepoSpringDataInterface extends JpaRepository<BudgetDataModel, Long>
{
}
