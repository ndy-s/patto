package io.github.ndys.patto.patterns.adapter.example1_payment_adapter;

public class CheckoutService {

    private final PaymentProcessor paymentProcessor;

    public CheckoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout() {
        paymentProcessor.pay(150_000);
    }
}

