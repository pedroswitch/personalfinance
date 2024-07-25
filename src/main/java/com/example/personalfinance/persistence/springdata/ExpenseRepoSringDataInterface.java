package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.persistence.datamodel.ExpenseDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepoSringDataInterface extends JpaRepository<ExpenseDataModel, Long>
{
    Iterable<ExpenseDataModel> findBySupplier(String supplier);
}
