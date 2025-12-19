package io.github.ndys.patto.patterns.singleton.example1_config_manager;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Singleton > Config Manager");

        ConfigManager config1 = ConfigManager.getInstance();
        ConfigManager config2 = ConfigManager.getInstance();

        config1.set("env", "production");

        System.out.println("Config from instance 1:");
        System.out.println("env = " + config1.get("env"));

        System.out.println("\nConfig from instance 2:");
        System.out.println("env = " + config2.get("env"));

        System.out.println("\nSame instance?");
        System.out.println(config1 == config2);

        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
    
}

