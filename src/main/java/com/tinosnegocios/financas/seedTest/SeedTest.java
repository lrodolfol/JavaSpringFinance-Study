package com.tinosnegocios.financas.seedTest;

import com.tinosnegocios.financas.entities.BalanceFlow;
import com.tinosnegocios.financas.entities.BalanceInput;
import com.tinosnegocios.financas.entities.BalanceOutPut;
import com.tinosnegocios.financas.enuns.PaymentMethod;
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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Configuration
@Profile("dev")
public class SeedTest implements CommandLineRunner {

    @Autowired
    private BalanceInPutRepository balanceInputRepository;
    @Autowired
    private BalanceOutPutRepository balanceOutPutRepository;
    @Autowired
    private BalanceFlowRepository balanceFlowRepository;

    @Override
    public void run(String... args) throws Exception {
        List<BalanceInput> listImput = BalanceInputBuild.build();
        //BalanceInput input = new BalanceInput(null, "Salário emprego", "sem HE", 85.33, Instant.now().plus(-2, ChronoUnit.DAYS), true );
        balanceInputRepository.saveAll(listImput);

        List<BalanceOutPut> listOutPut = BalanceOutputBuild.build();
        //BalanceOutPut output = new BalanceOutPut(null, "Conta de água", "Pago sem atraso", 85.50, Instant.now().plus(-2, ChronoUnit.DAYS),
                //"SAAE", 0.0, 0.0, false, PaymentMethod.PIX);
        balanceOutPutRepository.saveAll(listOutPut);

        List<BalanceFlow> listFlow = BalanceFlowBuild.build(listImput, listOutPut);
        //BalanceFlow flow = new BalanceFlow(
//                input.getDescription(),
//                input.getObservation(),
//                input.getAmount(),
//                Instant.now().plus(1 + 1, ChronoUnit.HOURS),
//                input,
//                output);
        balanceFlowRepository.saveAll(listFlow);
    }
}
