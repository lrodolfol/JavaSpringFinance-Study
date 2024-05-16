package com.tinosnegocios.financas.services;

import com.tinosnegocios.financas.exceptions.ResourceNotFoundException;
import com.tinosnegocios.financas.repositories.BalanceFlowRepository;
import com.tinosnegocios.financas.repositories.BalanceInPutRepository;
import com.tinosnegocios.financas.entities.BalanceInput;
import jakarta.persistence.EntityNotFoundException;
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
    @Autowired
    private BalanceFlowRepository balanceFlowRepository;

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
        balanceFlowRepository.deleteByInputId(id);
        balanceInoutRepository.deleteById(id);
    }
    public BalanceInput updateOne(BalanceInput balanceInput, Long id){
        try{
            BalanceInput inputObj = balanceInoutRepository.getReferenceById(id);
            //inputObj.setAmount(balanceInput.getAmount());
            inputObj.setDescription(balanceInput.getDescription());
            inputObj.setObservation(balanceInput.getObservation());
            inputObj.setMoment(balanceInput.getMoment());
            inputObj.setRealProfit(balanceInput.isRealProfit());

            return balanceInoutRepository.save(inputObj);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
}
