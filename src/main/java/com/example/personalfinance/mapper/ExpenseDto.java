package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@NoArgsConstructor
public class ExpenseDto extends RepresentationModel<ExpenseDto>
{
    public long id;
    public String type;
    public String supplier;
    public String category;
    public double value;
    public String number;
    public LocalDate date;
    public boolean status;
    public LocalDate initialDate;
    public LocalDate finalDate;

    // Invoice
    public ExpenseDto(long id, String type, String supplier, String category, double value, String number, LocalDate date, boolean status)
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

    public ExpenseDto(Link initialLink, long id, String type, String supplier, String category, double value, String number, LocalDate date, boolean status)
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

    public ExpenseDto(Iterable<Link> initialLinks, long id, String type, String supplier, String category, double value, String number, LocalDate date, boolean status)
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
    public ExpenseDto(long id, String type, String supplier, String category, double value, LocalDate initialDate, LocalDate finalDate)
    {
        this.id = id;
        this.type = type;
        this.supplier = supplier;
        this.category = category;
        this.value = value;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public ExpenseDto(Link initialLink, long id, String type, String supplier, String category, double value, LocalDate initialDate, LocalDate finalDate)
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

    public ExpenseDto(Iterable<Link> initialLinks, long id, String type, String supplier, String category, double value, LocalDate initialDate, LocalDate finalDate)
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
