package com.example.personalfinance.controller.rest_controllers.resources;

import com.example.personalfinance.controller.rest_controllers.InvoiceRegistrationController;
import com.example.personalfinance.mapper.ExpenseDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class InvoiceRegistrationResourceHandler
{
    public static ExpenseDto manageAdd(ExpenseDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(InvoiceRegistrationController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }

    public static Iterable<ExpenseDto> manageFindAll(Iterable<ExpenseDto> dtos)
    {
        for (ExpenseDto expenseDto : dtos) {

            Link link = WebMvcLinkBuilder.linkTo(InvoiceRegistrationController.class)
                    .slash(expenseDto.id)
                    .withSelfRel();

            expenseDto.add(link);
        }

        return dtos;
    }

    public static ExpenseDto manageFindId(ExpenseDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(InvoiceRegistrationController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }
}
