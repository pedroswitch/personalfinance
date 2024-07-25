package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.persistence.datamodel.InvestmentDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepoSpringDataInterface extends JpaRepository<InvestmentDataModel, Long>
{
    Iterable<InvestmentDataModel> findByCategory(String category);
}
