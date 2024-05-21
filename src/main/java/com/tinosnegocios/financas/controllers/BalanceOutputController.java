package com.tinosnegocios.financas.controllers;

import com.tinosnegocios.financas.services.BalanceInputService;
import com.tinosnegocios.financas.entities.BalanceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @PostMapping
    public ResponseEntity<BalanceInput> create(@RequestBody BalanceInput balanceInput){
        BalanceInput inputObj = service.saveOne(balanceInput);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(inputObj.getId())
                .toUri();

        return ResponseEntity.created(uri).body(inputObj);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<BalanceInput> updateOne(@RequestBody BalanceInput balanceInput, @PathVariable Long id) {
        BalanceInput newBalanceInput = service.updateOne(balanceInput, id);

        if(newBalanceInput != null){
            return ResponseEntity.ok().body(newBalanceInput);
        }

        return ResponseEntity.notFound().build();
    }
}
