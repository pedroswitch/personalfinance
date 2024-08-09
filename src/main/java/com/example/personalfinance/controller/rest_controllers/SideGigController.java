package com.example.personalfinance.controller.rest_controllers;

import com.example.personalfinance.controller.rest_controllers.resources.SideGigResourceHandler;
import com.example.personalfinance.domain.income.Income;
import com.example.personalfinance.domain.income.SideGig;
import com.example.personalfinance.mapper.IncomeDto;
import com.example.personalfinance.mapper.IncomeMappper;
import com.example.personalfinance.service.SideGigService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sidegig")
@AllArgsConstructor
public class SideGigController
{
    private final SideGigService sideGigService;

    @PostMapping
    public ResponseEntity<IncomeDto> addSideGig(@RequestBody IncomeDto dto)
    {
        SideGig sideGig = (SideGig) IncomeMappper.dtoToSideGig(dto);
        SideGig sideGigToAdd = (SideGig) sideGigService.add(sideGig.getId(), sideGig.getType(), sideGig.getDate(), sideGig.getValue(), sideGig.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                SideGigResourceHandler.manageAdd(IncomeMappper.sideGigToIncomeDto(sideGigToAdd))
        );
    }

    @GetMapping(params = "name")
    public ResponseEntity<Iterable<IncomeDto>> findSideGigsByName(@RequestParam String name)
    {
        Iterable<Income> sideGigs = sideGigService.findByName(name);
        Iterable<IncomeDto> sideGigDtos = SideGigResourceHandler.manageFindAll(IncomeMappper.sideGigListToDto(sideGigs));
        return ResponseEntity.status(HttpStatus.OK).body(sideGigDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id)
    {
        boolean deleted = sideGigService.delete(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping
    public ResponseEntity<Iterable<IncomeDto>> findAll()
    {
        Iterable<Income> sideGigs = sideGigService.findAll();
        Iterable<IncomeDto> sideGigDtos = SideGigResourceHandler.manageFindAll(IncomeMappper.sideGigListToDto(sideGigs));
        return ResponseEntity.status(HttpStatus.OK).body(sideGigDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findSideGigById(@PathVariable long id)
    {
        Income sideGig = sideGigService.findById(id);
        if (sideGig == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                SideGigResourceHandler.manageFindById(IncomeMappper.sideGigToIncomeDto(sideGig))
        );
    }
}
