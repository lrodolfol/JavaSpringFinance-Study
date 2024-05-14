package com.tinosnegocios.financas.seedTest.buildEntities;

import com.tinosnegocios.financas.entities.BalanceFlow;
import com.tinosnegocios.financas.entities.BalanceInput;
import com.tinosnegocios.financas.entities.BalanceOutPut;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BalanceFlowBuild {
    public static List<BalanceFlow> build(List<BalanceInput> listImput, List<BalanceOutPut> listOutPut){
        List<BalanceFlow> list = new ArrayList<>();
        list.addAll(buildWithBalanceInput(listImput));
        list.addAll(buildWithBalanceOutPut(listOutPut));

        return list;
    }

    private static List<BalanceFlow> buildWithBalanceOutPut(List<BalanceOutPut> listOutPut) {
        List<BalanceFlow> list = new ArrayList<>();

        for(int x = 0; x < listOutPut.size(); x++) {
            list.add(new BalanceFlow(
                    listOutPut.get(x).getDescription(),
                    listOutPut.get(x).getObservation(),
                    listOutPut.get(x).getAmount(),
                    Instant.now().plus(x + 1, ChronoUnit.HOURS),
                    null,
                    listOutPut.get(x)
            ));
        }

        return list;
    }
    private static List<BalanceFlow> buildWithBalanceInput(List<BalanceInput> listInput) {
        List<BalanceFlow> list = new ArrayList<>();

        for(int x = 0; x < listInput.size(); x++) {
            list.add(new BalanceFlow(
                    listInput.get(x).getDescription(),
                    listInput.get(x).getObservation(),
                    listInput.get(x).getAmount(),
                    Instant.now().plus(x + 1, ChronoUnit.HOURS),
                    listInput.get(x),
                    null
            ));
        }

        return list;
    }
}
