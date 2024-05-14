package com.tinosnegocios.financas.seedTest.buildEntities;

import com.tinosnegocios.financas.entities.BalanceInput;
import com.tinosnegocios.financas.entities.BalanceOutPut;
import com.tinosnegocios.financas.enuns.PaymentMethod;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BalanceOutputBuild {
    public static List<BalanceOutPut> build(){
        List<BalanceOutPut> list = new ArrayList<>();
        list.add(new BalanceOutPut(null, "Conta de água", "Pago sem atraso", 85.50, Instant.now().plus(-2, ChronoUnit.DAYS),
                "SAAE", 0.0, 0.0, false, PaymentMethod.PIX));
        list.add(new BalanceOutPut(null, "Conta de energia", "Pago sem atraso", 105.40, Instant.now().plus(-5, ChronoUnit.DAYS),
                "CEMIG", 0.0, 0.0, false, PaymentMethod.MONEY));
        list.add(new BalanceOutPut(null, "Mercado", "Compras do mês", 355.89, Instant.now().plus(-8, ChronoUnit.DAYS),
                "Carrossel", 25.0, 0.0, false, PaymentMethod.PIX));

        return  list;
    }
}