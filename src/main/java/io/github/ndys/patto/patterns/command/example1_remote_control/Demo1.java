package io.github.ndys.patto.patterns.command.example1_remote_control;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {
    public static void run() {
        TerminalPrinter.printHeader("Chain of Responsibility > Support Ticket Handling Demo");

        Light livingRoomLight = new Light();

        LightOnCommand lightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand lightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();

        System.out.println("Turning the light ON...");
        remote.setCommand(lightOn);
        remote.pressButton();

        System.out.println("Turning the light OFF...");
        remote.setCommand(lightOff);
        remote.pressButton();
        
        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
}

