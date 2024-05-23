package com.tinosnegocios.financas.controllers;

import com.tinosnegocios.financas.entities.BalanceOutPut;
import com.tinosnegocios.financas.services.BalanceOutPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("balance-output")
public class BalanceOutputController {
    @Autowired
    private BalanceOutPutService service;
    @GetMapping
    public ResponseEntity<List<BalanceOutPut>> getAll() {
        List<BalanceOutPut> balances = service.findAll();
        return ResponseEntity.ok().body(balances);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<BalanceOutPut> getOneById(@PathVariable Long id) {
        BalanceOutPut balanceOutPut = service.findOneById(id);
        return ResponseEntity.ok().body(balanceOutPut);
    }
    @PostMapping
    public ResponseEntity<BalanceOutPut> create(@RequestBody BalanceOutPut balanceOutPut){
        BalanceOutPut inputObj = service.saveOne(balanceOutPut);
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
    public ResponseEntity<BalanceOutPut> updateOne(@RequestBody BalanceOutPut balanceOutput, @PathVariable Long id) {
        BalanceOutPut newBalanceOutput = service.updateOne(balanceOutput, id);

        if(newBalanceOutput != null){
            return ResponseEntity.ok().body(newBalanceOutput);
        }

        return ResponseEntity.notFound().build();
    }
}
