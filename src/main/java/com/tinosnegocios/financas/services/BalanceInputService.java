package com.tinosnegocios.financas.services;

import com.tinosnegocios.financas.exceptions.ResourceNotFoundException;
import com.tinosnegocios.financas.models.dto.BalanceInputDto;
import com.tinosnegocios.financas.repositories.BalanceFlowRepository;
import com.tinosnegocios.financas.repositories.BalanceInPutRepository;
import com.tinosnegocios.financas.entities.BalanceInput;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceInputService {
    @Autowired(required = true)
    private BalanceInPutRepository balanceInputRepository;
    @Autowired
    private BalanceFlowRepository balanceFlowRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<BalanceInput> findAll(){
        return balanceInputRepository.findAll();
    }
    public BalanceInput findOneById(Long id) {
        Optional<BalanceInput> balanceInput = balanceInputRepository.findById(id);
        if(balanceInput.isPresent()){
            return balanceInput.get();
        }

        throw new ResourceNotFoundException(id);
    }
    public BalanceInput saveOne(BalanceInputDto balanceInputDto) {
        BalanceInput balanceInput = modelMapper.map(balanceInputDto, BalanceInput.class);

        return balanceInputRepository.save(balanceInput);
    }
    public void deleteById(Long id){
        balanceFlowRepository.deleteByInputId(id);
        balanceInputRepository.deleteById(id);
    }
    public BalanceInput updateOne(BalanceInput balanceInput, Long id){
        try{
            BalanceInput inputObj = balanceInputRepository.getReferenceById(id);
            //inputObj.setAmount(balanceInput.getAmount());
            inputObj.setDescription(balanceInput.getDescription());
            inputObj.setObservation(balanceInput.getObservation());
            inputObj.setMoment(balanceInput.getMoment());
            inputObj.setRealProfit(balanceInput.isRealProfit());

            return balanceInputRepository.save(inputObj);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
}
