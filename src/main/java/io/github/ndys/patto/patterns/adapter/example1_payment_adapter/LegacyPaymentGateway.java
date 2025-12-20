package io.github.ndys.patto.patterns.adapter.example1_payment_adapter;

public class LegacyPaymentGateway {

    public void makePayment(double money) {
        System.out.println("Processing legacy payment: Rp " + money);
    }

    
}
