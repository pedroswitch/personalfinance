package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
public class ExpenseDto extends RepresentationModel<ExpenseDto>
{
    public ExpenseId id;
    public ExpenseType type;
    public ExpenseSupplier supplier;
    public ExpenseCategory category;
    public Values value;
    public InvoiceNumber number;
    public Date date;
    public ExpenseStatus status;
    public Date initialDate;
    public Date finalDate;

    // Invoice Registration

    public ExpenseDto(ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, InvoiceNumber number, Date date, ExpenseStatus status)
    {
        this.id = id;
        this.type = type;
        this.supplier = supplier;
        this.category = category;
        this.value = value;
        this.number = number;
        this.date = date;
        this.status = status;
    }

    public ExpenseDto(Link initialLink, ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, InvoiceNumber number, Date date, ExpenseStatus status)
    {
        super(initialLink);
        this.id = id;
        this.type = type;
        this.supplier = supplier;
        this.category = category;
        this.value = value;
        this.number = number;
        this.date = date;
        this.status = status;
    }

    public ExpenseDto(Iterable<Link> initialLinks, ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, InvoiceNumber number, Date date, ExpenseStatus status)
    {
        super(initialLinks);
        this.id = id;
        this.type = type;
        this.supplier = supplier;
        this.category = category;
        this.value = value;
        this.number = number;
        this.date = date;
        this.status = status;
    }

    // Recurring Bill

    public ExpenseDto(ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, Date initialDate, Date finalDate)
    {
        this.id = id;
        this.type = type;
        this.supplier = supplier;
        this.category = category;
        this.value = value;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public ExpenseDto(Link initialLink, ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, Date initialDate, Date finalDate)
    {
        super(initialLink);
        this.id = id;
        this.type = type;
        this.supplier = supplier;
        this.category = category;
        this.value = value;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public ExpenseDto(Iterable<Link> initialLinks, ExpenseId id, ExpenseType type, ExpenseSupplier supplier, ExpenseCategory category, Values value, Date initialDate, Date finalDate)
    {
        super(initialLinks);
        this.id = id;
        this.type = type;
        this.supplier = supplier;
        this.category = category;
        this.value = value;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }
}
