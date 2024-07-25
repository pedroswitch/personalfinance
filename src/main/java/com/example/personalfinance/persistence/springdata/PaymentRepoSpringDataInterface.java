package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.persistence.datamodel.PaymentDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepoSpringDataInterface extends JpaRepository<PaymentDataModel, Long>
{
    Iterable<PaymentDataModel> findBySupplier(String supplier);
}
