package com.tinosnegocios.financas.seedTest;

import com.tinosnegocios.financas.entities.BalanceFlow;
import com.tinosnegocios.financas.entities.BalanceInput;
import com.tinosnegocios.financas.entities.BalanceOutPut;
import com.tinosnegocios.financas.repositories.BalanceFlowRepository;
import com.tinosnegocios.financas.repositories.BalanceInPutRepository;
import com.tinosnegocios.financas.repositories.BalanceOutPutRepository;
import com.tinosnegocios.financas.seedTest.buildEntities.BalanceFlowBuild;
import com.tinosnegocios.financas.seedTest.buildEntities.BalanceInputBuild;
import com.tinosnegocios.financas.seedTest.buildEntities.BalanceOutputBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("uat")
public class SeedTest implements CommandLineRunner {

    @Autowired
    private BalanceInPutRepository balanceInputRepository;
    @Autowired
    private BalanceOutPutRepository balanceOutPutRepository;
    @Autowired
    private BalanceFlowRepository balanceFlowRepository;

    @Override
    public void run(String... args) throws Exception {
        List<BalanceInput> listInput = BalanceInputBuild.build();
        balanceInputRepository.saveAll(listInput);

        List<BalanceOutPut> listOutPut = BalanceOutputBuild.build();
        balanceOutPutRepository.saveAll(listOutPut);

        List<BalanceFlow> listFlow = BalanceFlowBuild.build(listInput, listOutPut);
        balanceFlowRepository.saveAll(listFlow);
    }
}
