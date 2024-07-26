package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.persistence.datamodel.PaymentDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepoSpringDataInterface extends JpaRepository<PaymentDataModel, Long>
{
    @Query("SELECT p FROM PaymentDataModel p JOIN p.expense e WHERE e.supplier = :supplier")
    Iterable<PaymentDataModel> findPaymentsBySupplier(@Param("supplier") String supplier);
}
