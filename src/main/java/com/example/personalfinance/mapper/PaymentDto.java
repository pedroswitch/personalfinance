package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.valueobjects.Date;
import com.example.personalfinance.domain.valueobjects.ExpenseId;
import com.example.personalfinance.domain.valueobjects.PaymentId;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class PaymentDto extends RepresentationModel<PaymentDto>
{
    public PaymentId id;
    public ExpenseId expenseId;
    public Date paymentDate;

    public PaymentDto(PaymentId id, ExpenseId expenseId, Date paymentDate)
    {
        this.id = id;
        this.expenseId = expenseId;
        this.paymentDate = paymentDate;
    }

    public PaymentDto(Link initialLink, PaymentId id, ExpenseId expenseId, Date paymentDate)
    {
        super(initialLink);
        this.id = id;
        this.expenseId = expenseId;
        this.paymentDate = paymentDate;
    }

    public PaymentDto(Iterable<Link> initialLinks, PaymentId id, ExpenseId expenseId, Date paymentDate)
    {
        super(initialLinks);
        this.id = id;
        this.expenseId = expenseId;
        this.paymentDate = paymentDate;
    }
}
