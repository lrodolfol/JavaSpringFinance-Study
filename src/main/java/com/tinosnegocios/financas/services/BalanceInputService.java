package com.tinosnegocios.financas.services;

import com.tinosnegocios.financas.exceptions.ResourceNotFoundException;
import com.tinosnegocios.financas.repositories.BalanceInPutRepository;
import com.tinosnegocios.financas.entities.BalanceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.InstantSource;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceInputService {
    @Autowired(required = true)
    private BalanceInPutRepository balanceInoutRepository;

    public List<BalanceInput> findAll(){
        return balanceInoutRepository.findAll();
    }
    public BalanceInput findOneById(Long id) {
        Optional<BalanceInput> balanceInput = balanceInoutRepository.findById(id);
        if(balanceInput.isPresent()){
            return balanceInput.get();
        }

        throw new ResourceNotFoundException(id);
    }
    public BalanceInput saveOne(BalanceInput balanceInput) {
        return balanceInoutRepository.save(balanceInput);
    }
    public void deleteById(Long id){
        balanceInoutRepository.deleteById(id);
    }
}
