package com.example.personalfinance.controller.rest_controllers.resources;

import com.example.personalfinance.controller.rest_controllers.PaymentController;
import com.example.personalfinance.mapper.PaymentDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class PaymentResourceHandler
{
    public static PaymentDto manageAdd(PaymentDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(PaymentController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }

    public static Iterable<PaymentDto> manageFindAll(Iterable<PaymentDto> dtos)
    {
        for (PaymentDto paymentDto : dtos) {

            Link link = WebMvcLinkBuilder.linkTo(PaymentController.class)
                    .slash(paymentDto.id)
                    .withSelfRel();

            paymentDto.add(link);
        }

        return dtos;
    }

    public static PaymentDto manageFindById(PaymentDto dto)
    {
        Link link = WebMvcLinkBuilder.linkTo(PaymentController.class)
                .slash(dto.id)
                .withSelfRel();

        dto.add(link);

        return dto;
    }
}
