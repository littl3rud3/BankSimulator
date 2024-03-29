package com.example.exchangeapp.controller;

import com.example.exchangeapp.DTO.BankDTO;
import com.example.exchangeapp.DTO.PersonalInfoDTO;
import com.example.exchangeapp.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Tag(name = "Bank controller")
@RequestMapping("/api/bank")
public class BankController {

    private final BankService bankService;

    @Operation(summary = "All bank clients")
    @GetMapping("/{bank}")
    public Flux<PersonalInfoDTO> allClients(@PathVariable("bank") String bankName) {

        return bankService.allClients(bankName);
    }

    @Operation(summary = "All banks")
    @GetMapping
    public Flux<BankDTO> allBanks() {

        return bankService.getAll();
    }

    @Operation(summary = "Add new bank")
    @PostMapping
    public Mono<BankDTO> create(@RequestBody BankDTO bankDTO) {

        return bankService.create(bankDTO);
    }

    @Operation(summary = "Rebranding")
    @PutMapping("/{objectId}")
    public Mono<BankDTO> update(@PathVariable String objectId, @RequestParam String name) {

        return bankService.update(objectId, name);
    }

    @Operation(summary = "Delete bank")
    @DeleteMapping("/{objectId}")
    private Mono<Void> delete(@PathVariable String objectId) {

        return bankService.delete(objectId);
    }
}
