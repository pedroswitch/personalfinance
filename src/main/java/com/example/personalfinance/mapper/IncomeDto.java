package com.example.personalfinance.mapper;

import com.example.personalfinance.domain.valueobjects.*;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
public class IncomeDto extends RepresentationModel<IncomeDto>
{
    public IncomeId id;
    public IncomeType type;
    public Date date;
    public Values value;
    public EmployerName employerName;
    public SideGigName sideGigName;

    // Salary

    public IncomeDto(IncomeId id, IncomeType type, Date date, Values value, EmployerName employerName)
    {
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.employerName = employerName;
    }

    public IncomeDto(Link initialLink, IncomeId id, IncomeType type, Date date, Values value, EmployerName employerName)
    {
        super(initialLink);
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.employerName = employerName;
    }

    public IncomeDto(Iterable<Link> initialLinks, IncomeId id, IncomeType type, Date date, Values value, EmployerName employerName)
    {
        super(initialLinks);
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.employerName = employerName;
    }

    // SideGig

    public IncomeDto(IncomeId id, IncomeType type, Date date, Values value, SideGigName sideGigName)
    {
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.sideGigName = sideGigName;
    }

    public IncomeDto(Link initialLink, IncomeId id, IncomeType type, Date date, Values value, SideGigName sideGigName)
    {
        super(initialLink);
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.sideGigName = sideGigName;
    }

    public IncomeDto(Iterable<Link> initialLinks, IncomeId id, IncomeType type, Date date, Values value, SideGigName sideGigName)
    {
        super(initialLinks);
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.sideGigName = sideGigName;
    }
}
