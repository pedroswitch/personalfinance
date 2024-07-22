package com.example.personalfinance.persistence.springdata;

import com.example.personalfinance.domain.expense.Expense;
import com.example.personalfinance.domain.expense.InvoiceRegistration;
import com.example.personalfinance.domain.expense.Payment;
import com.example.personalfinance.domain.expense.RecurringBill;
import com.example.personalfinance.domain.repository.ExpenseRepo;
import com.example.personalfinance.domain.valueobjects.ExpenseId;
import com.example.personalfinance.mapper.ExpenseMapper;
import com.example.personalfinance.persistence.datamodel.ExpenseDataModel;
import com.example.personalfinance.persistence.datamodel.InvoiceRegistrationDataModel;
import com.example.personalfinance.persistence.datamodel.PaymentDataModel;
import com.example.personalfinance.persistence.datamodel.RecurringBillDataModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.personalfinance.utils.Constants.*;

@Repository
public class ExpenseRepoSpringData implements ExpenseRepo
{
    private final ExpenseRepoSringDataInterface _expenseInterface;

    public ExpenseRepoSpringData(ExpenseRepoSringDataInterface expenseInterface)
    {
        this._expenseInterface = expenseInterface;
    }

    @Override
    public Expense save(Expense expense) {
        ExpenseDataModel expenseDataModel;
        if (expense instanceof InvoiceRegistration invoiceRegistration) {
            expenseDataModel = new InvoiceRegistrationDataModel(expense, invoiceRegistration.getNumber().getNumber(), invoiceRegistration.getDate().getDate(), invoiceRegistration.getStatus().isStatus());
        } else if (expense instanceof Payment payment) {
            expenseDataModel = new PaymentDataModel(expense, payment.getDate().getDate(), payment.getNumber().getNumber(), payment.getPaymentDate().getDate());
        } else if (expense instanceof RecurringBill recurringBill) {
            expenseDataModel = new RecurringBillDataModel(expense, recurringBill.getInitialDate().getDate(), recurringBill.getFinalDate().getDate());
        } else {
            expenseDataModel = new ExpenseDataModel(expense);
        }
        ExpenseDataModel result = _expenseInterface.save(expenseDataModel);
        return ExpenseMapper.expenseDataModelToDomain(result);
    }

    @Override
    public Iterable<Expense> findAll()
    {
        Iterable<ExpenseDataModel> expenses = _expenseInterface.findAll();
        return ExpenseMapper.expensesDataModelToDomain(expenses);
    }

    @Override
    public Optional<Expense> findById(ExpenseId id)
    {
        Optional<ExpenseDataModel> model = this._expenseInterface.findById(id.id);
        if (model.isPresent()) {
            return Optional.of(ExpenseMapper.expenseDataModelToDomain(model.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(ExpenseId id)
    {
        return this._expenseInterface.existsById(id.id);
    }
}
