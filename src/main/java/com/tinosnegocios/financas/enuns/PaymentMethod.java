package com.tinosnegocios.financas.enuns;

public enum PaymentMethod {
    MONEY(1),
    CREDIT_CART(2),
    DEBIT_CART(3),
    PIX(4),
    TED(5),
    DOC(6);

    private int code;

    PaymentMethod(int code) {
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static PaymentMethod valuOf(int code){
        for(PaymentMethod value : PaymentMethod.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        return PaymentMethod.MONEY;
    }
}
