package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.ExpenseFactory;
import com.example.personalfinance.domain.valueobjects.*;
import com.example.personalfinance.persistence.datamodel.ExpenseDataModel;
import com.example.personalfinance.persistence.datamodel.InvoiceRegistrationDataModel;
import com.example.personalfinance.persistence.datamodel.PaymentDataModel;
import com.example.personalfinance.persistence.datamodel.RecurringBillDataModel;

import static com.example.personalfinance.utils.Constants.*;

public class ExpenseMapper
{
    public static Expense expenseDataModelToDomain(ExpenseDataModel expenseDataModel)
    {
        ExpenseFactory expenseFactory = new ExpenseFactory();
        ExpenseId id = new ExpenseId(expenseDataModel.getId());
        ExpenseType type = new ExpenseType((expenseDataModel.getType()));
        ExpenseSupplier supplier = new ExpenseSupplier(expenseDataModel.getSupplier());
        ExpenseCategory category = new ExpenseCategory(expenseDataModel.getCategory());
        Values value = new Values(expenseDataModel.getValue());

        if (type.getType().equals(INVOICE_REG)) {
            InvoiceRegistrationDataModel expense = (InvoiceRegistrationDataModel) expenseDataModel;
            InvoiceNumber number = new InvoiceNumber(expense.getNumber());
            Date date = new Date(expense.getDate());
            ExpenseStatus status = new ExpenseStatus(expense.isStatus());
            return expenseFactory.createExpense(id, type, supplier, category, value, number, date, status);
        }

        if (type.getType().equals(PAYMENT)) {
            PaymentDataModel expense = (PaymentDataModel) expenseDataModel;
            Date date = new Date(expense.getInvoiceDate());
            InvoiceNumber number = new InvoiceNumber(expense.getInvoiceNumber());
            Date paymentDate = new Date(expense.getPaymentDate());
            return expenseFactory.createExpense(id, type, supplier, category, value, date, number, paymentDate);
        }

        if (type.getType().equals(REC_BILL)) {
            RecurringBillDataModel expense = (RecurringBillDataModel) expenseDataModel;
            Date initialDate = new Date(expense.getInitialDate());
            Date finalDate = new Date(expense.getFinalDate());
            return expenseFactory.createExpense(id, type, supplier, category, value, initialDate, finalDate);
        }

        return expenseFactory.createExpense(id, type, supplier, category, value);
    }
}
