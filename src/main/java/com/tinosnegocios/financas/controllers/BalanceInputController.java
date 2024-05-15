package com.tinosnegocios.financas.controllers;

import com.tinosnegocios.financas.services.BalanceInputService;
import com.tinosnegocios.financas.entities.BalanceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("balance-input")
public class BalanceInputController {
    @Autowired
    private BalanceInputService service;
    @GetMapping
    public ResponseEntity<List<BalanceInput>> getAll() {
        List<BalanceInput> balances = service.findAll();
        return ResponseEntity.ok().body(balances);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<BalanceInput> getOneById(@PathVariable Long id) {
        BalanceInput balanceInput = service.findOneById(id);
        return ResponseEntity.ok().body(balanceInput);
    }
}
