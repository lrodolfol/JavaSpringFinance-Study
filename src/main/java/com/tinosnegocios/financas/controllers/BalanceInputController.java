package com.tinosnegocios.financas.controllers;

import com.tinosnegocios.financas.services.BalanceInputService;
import com.tinosnegocios.financas.entities.BalanceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("balance-input")
public class BalanceInputController {
    @Autowired
    private BalanceInputService service;
    @GetMapping
    public ResponseEntity<List<BalanceInput>> get(){
        List<BalanceInput> balances = service.findAll();
        return ResponseEntity.ok().body(balances);
    }
}
