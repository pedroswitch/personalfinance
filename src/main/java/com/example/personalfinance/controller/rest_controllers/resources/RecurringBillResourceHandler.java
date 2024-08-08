package com.example.personalfinance.controller.rest_controllers.resources;

import com.example.personalfinance.controller.rest_controllers.RecurringBillController;
import com.example.personalfinance.mapper.ExpenseDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class RecurringBillResourceHandler
{
    public static ExpenseDto manageAdd(ExpenseDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(RecurringBillController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }

    public static Iterable<ExpenseDto> manageFindAll(Iterable<ExpenseDto> dtos)
    {
        for (ExpenseDto expenseDto : dtos) {

            Link link = WebMvcLinkBuilder.linkTo(RecurringBillController.class)
                    .slash(expenseDto.id)
                    .withSelfRel();

            expenseDto.add(link);
        }

        return dtos;
    }

    public static ExpenseDto manageFindId(ExpenseDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(RecurringBillController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }
}
