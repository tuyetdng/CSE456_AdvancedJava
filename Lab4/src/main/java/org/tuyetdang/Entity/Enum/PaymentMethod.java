package org.tuyetdang.Entity.Enum;

public enum PaymentMethod {
    CREDIT_CARD("Credit Card"),
    E_WALLET("eWallet"),
    BANK_TRANSFER("Bank Transfer");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
