package io.github.ndys.patto.patterns.adapter.example1_payment_adapter;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Adapter > Payment Adapter");

        LegacyPaymentGateway legacyGateway = new LegacyPaymentGateway();
        PaymentProcessor adapter = new PaymentAdapter(legacyGateway);

        CheckoutService checkout = new CheckoutService(adapter);
        checkout.checkout();

        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }

    
}
