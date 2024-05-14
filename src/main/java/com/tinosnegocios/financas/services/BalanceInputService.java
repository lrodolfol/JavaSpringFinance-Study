package com.tinosnegocios.financas.services;

import com.tinosnegocios.financas.repositories.BalanceInPutRepository;
import com.tinosnegocios.financas.entities.BalanceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceInputService {
    @Autowired(required = true)
    private BalanceInPutRepository balanceInoutRepository;

    public List<BalanceInput> findAll(){
        return balanceInoutRepository.findAll();
    }
}
