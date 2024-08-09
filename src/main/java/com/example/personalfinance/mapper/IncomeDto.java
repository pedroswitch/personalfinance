package com.example.personalfinance.mapper;

import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@NoArgsConstructor
public class IncomeDto extends RepresentationModel<IncomeDto>
{
    public long id;
    public String type;
    public LocalDate date;
    public double value;
    public String employerName;
    public String sideGigName;

    // Salary
    public IncomeDto(long id, String type, LocalDate date, String employerName, double value)
    {
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.employerName = employerName;
    }

    public IncomeDto(Link initialLink, long id, String type, LocalDate date, String employerName, double value)
    {
        super(initialLink);
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.employerName = employerName;
    }

    public IncomeDto(Iterable<Link> initialLinks, long id, String type, LocalDate date, String employerName, double value)
    {
        super(initialLinks);
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.employerName = employerName;
    }

    // SideGig
    public IncomeDto(long id, String type, LocalDate date, double value, String sideGigName)
    {
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.sideGigName = sideGigName;
    }

    public IncomeDto(Link initialLink, long id, String type, LocalDate date, double value, String sideGigName)
    {
        super(initialLink);
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.sideGigName = sideGigName;
    }

    public IncomeDto(Iterable<Link> initialLinks, long id, String type, LocalDate date, double value, String sideGigName)
    {
        super(initialLinks);
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.sideGigName = sideGigName;
    }
}
