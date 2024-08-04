package com.example.personalfinance.controller.rest_controllers;

import com.example.personalfinance.controller.rest_controllers.resources.InvestmentResourceHandler;
import com.example.personalfinance.domain.investment.Investment;
import com.example.personalfinance.mapper.InvestmentDto;
import com.example.personalfinance.mapper.InvestmentMapper;
import com.example.personalfinance.service.InvestmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/investment")
@AllArgsConstructor
public class InvestmentController
{
    private final InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<Object> addInvestment(@RequestBody InvestmentDto dto)
    {
        Investment investment = InvestmentMapper.dtoToInvestment(dto);
        Investment investmentToAdd = investmentService.add(investment.getId(), investment.getCategory(), investment.getDescription(), investment.getPurchaseDate(), investment.getSaleDate(), investment.getQty(), investment.getValue());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(InvestmentResourceHandler.manageAdd(InvestmentMapper.investmentToDto(investmentToAdd))
        );
    }

    @GetMapping(params = "category")
    public ResponseEntity<Iterable<InvestmentDto>> findInvestmentsByCategory(@RequestParam String category)
    {
        Iterable<Investment> investments = investmentService.findByCategory(category);
        Iterable<InvestmentDto> investmentDtos = InvestmentResourceHandler.manageFindAll(InvestmentMapper.investmentListToDto(investments));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(investmentDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id)
    {
        boolean deleted = investmentService.delete(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping
    public ResponseEntity<Iterable<InvestmentDto>> findAll()
    {
        Iterable<Investment> investments = investmentService.findAll();
        Iterable<InvestmentDto> investmentDtos = InvestmentResourceHandler.manageFindAll(InvestmentMapper.investmentListToDto(investments));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(investmentDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findInvestmentById(@PathVariable long id)
    {
        Investment investment = investmentService.findById(id);
        if (investment == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Investment with id " + id + " not found!");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(InvestmentResourceHandler
                .manageFindById(InvestmentMapper.investmentToDto(investment)));
    }
}
