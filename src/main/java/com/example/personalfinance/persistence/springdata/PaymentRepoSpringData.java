package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.domain.payment.Payment;
import com.example.personalfinance.domain.repository.PaymentRepo;
import com.example.personalfinance.domain.valueobjects.PaymentId;
import com.example.personalfinance.mapper.PaymentMapper;
import com.example.personalfinance.persistence.datamodel.PaymentDataModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PaymentRepoSpringData implements PaymentRepo
{
    private final PaymentRepoSpringDataInterface paymentInterface;

    public PaymentRepoSpringData(PaymentRepoSpringDataInterface paymentInterface)
    {
        this.paymentInterface = paymentInterface;
    }

    @Override
    public Payment save(Payment payment)
    {
        PaymentDataModel paymentDataModel = new PaymentDataModel(payment);
        PaymentDataModel result = paymentInterface.save(paymentDataModel);
        return PaymentMapper.paymentDataModelToDomain(result);
    }

    @Override
    public Iterable<Payment> findAll()
    {
        Iterable<PaymentDataModel> payments = paymentInterface.findAll();
        return PaymentMapper.paymentsDataModelToDomain(payments);
    }

    @Override
    public Optional<Payment> findById(PaymentId id)
    {
        Optional<PaymentDataModel> model = paymentInterface.findById(id.id);

        if (model.isPresent()) {
            return Optional.of(PaymentMapper.paymentDataModelToDomain(model.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(PaymentId id)
    {
        return paymentInterface.existsById(id.id);
    }

    @Override
    public Iterable<Payment> findPaymentsBySupplier(String supplier) {
        Iterable<PaymentDataModel> payments = paymentInterface.findPaymentsBySupplier(supplier);
        return PaymentMapper.paymentsDataModelToDomain(payments);
    }

    @Override
    public boolean delete(long id)
    {
        if (paymentInterface.existsById(id)) {
            paymentInterface.deleteById(id);
            return !paymentInterface.existsById(id);
        }
        return false;
    }

}
