package com.example.personalfinance.domain.income;

import com.example.personalfinance.domain.valueobjects.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Optional;

@Component
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

    public Income createIncome(IncomeId id, IncomeType type, Date date, Values value, EmployerName name)
    {
        try {
            String fullPath = PATH + type.getType();

            return (Income) Class.forName(fullPath)
                    .getConstructor(IncomeId.class, IncomeType.class, Date.class, Values.class, EmployerName.class)
                    .newInstance(id, type, date, value, name);
        } catch (ClassNotFoundException | InstantiationException |
                 NoSuchMethodException | NullPointerException |
                 InvocationTargetException | IllegalArgumentException |
                 IllegalAccessException e) {
            return null;
        }
    }

    public Income createIncome(IncomeId id, IncomeType type, Date date, Values value, SideGigName name)
    {
        try {
            String fullPath = PATH + type.getType();

            return (Income) Class.forName(fullPath)
                    .getConstructor(IncomeId.class, IncomeType.class, Date.class, Values.class, SideGigName.class)
                    .newInstance(id, type, date, value, name);
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

    public Optional<EmployerName> createEmployerName(String name)
    {
        try {
           return Optional.of(new EmployerName(name));
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
