package com.tinosnegocios.financas.services;

import com.tinosnegocios.financas.entities.BalanceInput;
import com.tinosnegocios.financas.exceptions.ResourceNotFoundException;
import com.tinosnegocios.financas.repositories.BalanceFlowRepository;
import com.tinosnegocios.financas.repositories.BalanceInPutRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BalanceOutPutService {
    @Autowired(required = true)
    private BalanceInPutRepository balanceOutPutRepository;
    @Autowired
    private BalanceFlowRepository balanceFlowRepository;

    public List<BalanceInput> findAll(){
        return balanceOutPutRepository.findAll();
    }
    public BalanceInput findOneById(Long id) {
        Optional<BalanceInput> balanceInput = balanceOutPutRepository.findById(id);
        if(balanceInput.isPresent()){
            return balanceInput.get();
        }

        throw new ResourceNotFoundException(id);
    }
    public BalanceInput saveOne(BalanceInput balanceInput) {
        return balanceOutPutRepository.save(balanceInput);
    }
    public void deleteById(Long id){
        balanceFlowRepository.deleteByInputId(id);
        balanceOutPutRepository.deleteById(id);
    }
    public BalanceInput updateOne(BalanceInput balanceInput, Long id){
        try{
            BalanceInput inputObj = balanceOutPutRepository.getReferenceById(id);
            //inputObj.setAmount(balanceInput.getAmount());
            inputObj.setDescription(balanceInput.getDescription());
            inputObj.setObservation(balanceInput.getObservation());
            inputObj.setMoment(balanceInput.getMoment());
            inputObj.setRealProfit(balanceInput.isRealProfit());

            return balanceOutPutRepository.save(inputObj);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
}
