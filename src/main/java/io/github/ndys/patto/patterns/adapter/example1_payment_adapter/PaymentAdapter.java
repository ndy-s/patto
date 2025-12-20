package io.github.ndys.patto.patterns.adapter.example1_payment_adapter;

public class PaymentAdapter implements PaymentProcessor {

    private final LegacyPaymentGateway legacyGateway;

    public PaymentAdapter(LegacyPaymentGateway legacyGateway) {
        this.legacyGateway = legacyGateway;
    }

    @Override
    public void pay(int amount) {
        // adapt int to double and delegate
        legacyGateway.makePayment((double) amount);
    }

    
}

