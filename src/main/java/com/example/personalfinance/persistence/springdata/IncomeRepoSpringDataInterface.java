package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.persistence.datamodel.IncomeDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepoSpringDataInterface extends JpaRepository<IncomeDataModel, Long>
{
    Iterable<IncomeDataModel> findByName(String name);
}
