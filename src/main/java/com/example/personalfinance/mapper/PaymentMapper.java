package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.payment.Payment;
import com.example.personalfinance.domain.payment.PaymentFactory;
import com.example.personalfinance.domain.valueobjects.Date;
import com.example.personalfinance.domain.valueobjects.ExpenseId;
import com.example.personalfinance.domain.valueobjects.PaymentId;
import com.example.personalfinance.persistence.datamodel.PaymentDataModel;

import java.util.ArrayList;
import java.util.List;

public class PaymentMapper
{
    public static Payment paymentDataModelToDomain(PaymentDataModel paymentDataModel)
    {
        PaymentFactory paymentFactory = new PaymentFactory();
        PaymentId id = new PaymentId(paymentDataModel.getId());
        ExpenseId expenseId = new ExpenseId(paymentDataModel.getExpense().getId());
        Date paymentDate = new Date(paymentDataModel.getPaymentDate());
        return paymentFactory.createPayment(id, expenseId, paymentDate);
    }

    public static Iterable<Payment> paymentsDataModelToDomain(Iterable<PaymentDataModel> paymentDataModels)
    {
        List<Payment> payments = new ArrayList<>();
        paymentDataModels.forEach(
                dataModel -> payments.add(paymentDataModelToDomain(dataModel))
        );
        return payments;
    }

    public static PaymentDto paymentToDto(Payment payment)
    {
        return new PaymentDto(payment.getId().getId(), payment.getExpenseId().getId(), payment.getPaymentDate().getDate());
    }

    public static Iterable<PaymentDto> paymentListToDto(Iterable<Payment> payments)
    {
        List<PaymentDto> paymentListDto = new ArrayList<>();

        payments.forEach(payment -> {
            PaymentDto paymentDto = paymentToDto(payment);
            paymentListDto.add(paymentDto);
        });

        return paymentListDto;
    }

    public static Payment dtoToPayment(PaymentDto dto)
    {
        PaymentFactory paymentFactory = new PaymentFactory();
        PaymentId id = new PaymentId(dto.id);
        ExpenseId expenseId = new ExpenseId(dto.expenseId);
        Date paymentDate = new Date(dto.paymentDate);
        return paymentFactory.createPayment(id, expenseId, paymentDate);
    }
}
