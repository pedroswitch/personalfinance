package com.example.personalfinance.controller.rest_controllers.resources;

import com.example.personalfinance.controller.rest_controllers.BudgetController;
import com.example.personalfinance.mapper.BudgetDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class BudgetResourceHandler
{
    public static BudgetDto manageAdd(BudgetDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(BudgetController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }

    public static BudgetDto manageDelete(BudgetDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(BudgetController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }

    public static Iterable<BudgetDto> manageFindAll(Iterable<BudgetDto> dtos)
    {
        for (BudgetDto budgetDto : dtos) {

            Link link = WebMvcLinkBuilder.linkTo(BudgetController.class)
                    .slash(budgetDto.id)
                    .withSelfRel();

            budgetDto.add(link);
        }

        return dtos;
    }

    public static BudgetDto manageFindById(BudgetDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(BudgetController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }
}
