package com.example.personalfinance.mapper;

import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@NoArgsConstructor
public class PaymentDto extends RepresentationModel<PaymentDto>
{
    public long id;
    public long expenseId;
    public LocalDate paymentDate;

    public PaymentDto(long id, long expenseId, LocalDate paymentDate)
    {
        this.id = id;
        this.expenseId = expenseId;
        this.paymentDate = paymentDate;
    }

    public PaymentDto(Link initialLink, long id, long expenseId, LocalDate paymentDate)
    {
        super(initialLink);
        this.id = id;
        this.expenseId = expenseId;
        this.paymentDate = paymentDate;
    }

    public PaymentDto(Iterable<Link> initialLinks, long id, long expenseId, LocalDate paymentDate)
    {
        super(initialLinks);
        this.id = id;
        this.expenseId = expenseId;
        this.paymentDate = paymentDate;
    }
}
