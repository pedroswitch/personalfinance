package com.example.personalfinance.controller.rest_controllers.resources;

import com.example.personalfinance.controller.rest_controllers.SalaryController;
import com.example.personalfinance.mapper.IncomeDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class SalaryResourceHandler
{
    public static IncomeDto manageAdd(IncomeDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(SalaryController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }

    public static Iterable<IncomeDto> manageFindAll(Iterable<IncomeDto> dtos)
    {
        for (IncomeDto incomeDto : dtos) {

            Link link = WebMvcLinkBuilder.linkTo(SalaryController.class)
                    .slash(incomeDto.id)
                    .withSelfRel();

            incomeDto.add(link);
        }
        return dtos;
    }

    public static IncomeDto manageFindById(IncomeDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(SalaryController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }
}
