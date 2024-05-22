package com.tinosnegocios.financas.services;

import com.tinosnegocios.financas.entities.BalanceOutPut;
import com.tinosnegocios.financas.exceptions.ResourceNotFoundException;
import com.tinosnegocios.financas.repositories.BalanceFlowRepository;
import com.tinosnegocios.financas.repositories.BalanceOutPutRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceOutPutService {
    @Autowired
    private BalanceOutPutRepository balanceOutPutRepository;
    @Autowired
    private BalanceFlowRepository balanceFlowRepository;

    public List<BalanceOutPut> findAll(){
        return balanceOutPutRepository.findAll();
    }
    public BalanceOutPut findOneById(Long id) {
        Optional<BalanceOutPut> balanceInput = balanceOutPutRepository.findById(id);
        if(balanceInput.isPresent()){
            return balanceInput.get();
        }

        throw new ResourceNotFoundException(id);
    }
    public BalanceOutPut saveOne(BalanceOutPut balanceInput) {
        return balanceOutPutRepository.save(balanceInput);
    }
    public void deleteById(Long id){
        balanceFlowRepository.deleteByInputId(id);
        balanceOutPutRepository.deleteById(id);
    }
    public BalanceOutPut updateOne(BalanceOutPut balanceInput, Long id){
        try{
            BalanceOutPut inputObj = balanceOutPutRepository.getReferenceById(id);
            //inputObj.setAmount(balanceInput.getAmount());
            inputObj.setDescription(balanceInput.getDescription());
            inputObj.setObservation(balanceInput.getObservation());
            inputObj.setMoment(balanceInput.getMoment());

            return balanceOutPutRepository.save(inputObj);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
}
