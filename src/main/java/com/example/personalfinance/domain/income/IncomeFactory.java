package com.example.personalfinance.domain.income;

import com.example.personalfinance.domain.valueobjects.Date;
import com.example.personalfinance.domain.valueobjects.IncomeId;
import com.example.personalfinance.domain.valueobjects.IncomeType;
import com.example.personalfinance.domain.valueobjects.Values;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class IncomeFactory
{

    public static final String PATH = "com.example.personalfinance.domain.income";

    public Income createIncome(IncomeId id, IncomeType type, Date date, Values value)
    {
        try {
            String fullPath = PATH + type.getType();

            return (Income) Class.forName(fullPath)
                    .getConstructor(IncomeId.class, IncomeType.class, Date.class, Values.class)
                    .newInstance(id, type, date, value);
        } catch (ClassNotFoundException | InstantiationException |
                 NoSuchMethodException | NullPointerException |
                InvocationTargetException | IllegalArgumentException |
                IllegalAccessException e) {
            return null;
        }
    }

    public Optional<IncomeId> createIncomeId(long id)
    {
        try {
            return Optional.of(new IncomeId(id));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<Date> createDate(LocalDate date)
    {
        try {
            return Optional.of(new Date(date));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<Values> createValue(double value)
    {
        try {
            return Optional.of(new Values(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
