package com.tinosnegocios.financas.seedTest.buildEntities;

import com.tinosnegocios.financas.entities.BalanceInput;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BalanceInputBuild {
    public static List<BalanceInput> build(){
        List<BalanceInput> list = new ArrayList<>();
        list.add(new BalanceInput(null, "Salário emprego", "sem HE", 85.33, Instant.now().plus(-2, ChronoUnit.DAYS), true ));
        list.add(new BalanceInput(null,"13ª salário", "pago adiantado", 85.33, Instant.now().plus(-5, ChronoUnit.DAYS), true ));
        list.add(new BalanceInput(null,"Recebimento emprestimo", "", 85.33, Instant.now().plus(-8, ChronoUnit.DAYS), true ));

        return  list;
    }
}
