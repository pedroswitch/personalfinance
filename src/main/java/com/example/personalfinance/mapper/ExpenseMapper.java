package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.ExpenseFactory;
import com.example.personalfinance.domain.expense.InvoiceRegistration;
import com.example.personalfinance.domain.expense.RecurringBill;
import com.example.personalfinance.domain.valueobjects.*;
import com.example.personalfinance.persistence.datamodel.ExpenseDataModel;
import com.example.personalfinance.persistence.datamodel.InvoiceRegistrationDataModel;
import com.example.personalfinance.persistence.datamodel.RecurringBillDataModel;

import java.util.ArrayList;
import java.util.List;

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

        if (type.getType().equals(REC_BILL)) {
            RecurringBillDataModel expense = (RecurringBillDataModel) expenseDataModel;
            Date initialDate = new Date(expense.getInitialDate());
            Date finalDate = new Date(expense.getFinalDate());
            return expenseFactory.createExpense(id, type, supplier, category, value, initialDate, finalDate);
        }

        return expenseFactory.createExpense(id, type, supplier, category, value);
    }

    public static Iterable<Expense> expensesDataModelToDomain(Iterable<ExpenseDataModel> expenseDataModels)
    {
        List<Expense> expenses = new ArrayList();
        expenseDataModels.forEach(
                dataModel -> expenses.add(expenseDataModelToDomain(dataModel))
        );
        return expenses;
    }

    public static ExpenseDto invoiceToExpenseDto(InvoiceRegistration invoice) {
        return new ExpenseDto(invoice.getId(), invoice.getType(), invoice.getSupplier(), invoice.getCategory(), invoice.getValue(), invoice.getNumber(), invoice.getDate(), invoice.getStatus());
    }

    public static ExpenseDto recBillToExpenseDto(RecurringBill recBill) {
        return new ExpenseDto(recBill.getId(), recBill.getType(), recBill.getSupplier(), recBill.getCategory(), recBill.getValue(), recBill.getInitialDate(), recBill.getFinalDate());
    }

    public static Iterable<ExpenseDto> invoiceListToDto(Iterable<InvoiceRegistration> invoices) {
        List<ExpenseDto> expenseListDto = new ArrayList<>();

        invoices.forEach(invoice -> {
            ExpenseDto expenseDto = invoiceToExpenseDto(invoice);
            expenseListDto.add(expenseDto);
        });

        return expenseListDto;
    }

    public static Iterable<ExpenseDto> recBillListToDto(Iterable<RecurringBill> recBills) {
        List<ExpenseDto> expenseListDto = new ArrayList<>();

        recBills.forEach(recBill -> {
            ExpenseDto expenseDto = recBillToExpenseDto(recBill);
            expenseListDto.add(expenseDto);
        });

        return expenseListDto;
    }

    public static Expense dtoToInvoice(ExpenseDto dto)
    {
        ExpenseFactory expenseFactory = new ExpenseFactory();
        ExpenseId id = new ExpenseId(dto.id.getId());
        ExpenseType type = new ExpenseType(dto.type.getType());
        ExpenseSupplier supplier = new ExpenseSupplier(dto.supplier.getName());
        ExpenseCategory category = new ExpenseCategory(dto.category.getCategory());
        Values value = new Values(dto.value.getValue());
        InvoiceNumber number = new InvoiceNumber(dto.number.getNumber());
        Date date = new Date(dto.date.getDate());
        ExpenseStatus status = new ExpenseStatus(dto.status.isStatus());
        return expenseFactory.createExpense(id, type, supplier, category, value, number, date, status);
    }

    public static Expense dtoToRecBill(ExpenseDto dto)
    {
        ExpenseFactory expenseFactory = new ExpenseFactory();
        ExpenseId id = new ExpenseId(dto.id.getId());
        ExpenseType type = new ExpenseType(dto.type.getType());
        ExpenseSupplier supplier = new ExpenseSupplier(dto.supplier.getName());
        ExpenseCategory category = new ExpenseCategory(dto.category.getCategory());
        Values value = new Values(dto.value.getValue());
        Date initialDate = new Date(dto.initialDate.getDate());
        Date finalDate = new Date(dto.finalDate.getDate());
        return expenseFactory.createExpense(id, type, supplier, category, value, initialDate, finalDate);
    }

}
