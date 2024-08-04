package com.example.personalfinance.controller.rest_controllers.resources;

import com.example.personalfinance.controller.rest_controllers.InvestmentController;
import com.example.personalfinance.mapper.InvestmentDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class InvestmentResourceHandler
{
    public static InvestmentDto manageAdd(InvestmentDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(InvestmentController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }

    public static Iterable<InvestmentDto> manageFindAll(Iterable<InvestmentDto> dtos)
    {
        for (InvestmentDto investmentDto : dtos) {

            Link link = WebMvcLinkBuilder.linkTo(InvestmentController.class)
                    .slash(investmentDto.id)
                    .withSelfRel();

            investmentDto.add(link);
        }

        return dtos;
    }

    public static InvestmentDto manageFindById(InvestmentDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(InvestmentController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }
}
