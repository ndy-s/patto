package io.github.ndys.patto.patterns.bridge.example1_device_remote;

import io.github.ndys.patto.patterns.bridge.example1_device_remote.device.Radio;
import io.github.ndys.patto.patterns.bridge.example1_device_remote.device.Tv;
import io.github.ndys.patto.patterns.bridge.example1_device_remote.remote.BasicRemote;
import io.github.ndys.patto.patterns.bridge.example1_device_remote.remote.Remote;
import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Bridge > Device & Remote");

        System.out.println("Using Remote with TV");
        Remote tvRemote = new BasicRemote(new Tv());
        tvRemote.powerToggle();
        tvRemote.volumeUp();

        System.out.println();

        System.out.println("Using Remote with Radio");
        Remote radioRemote = new BasicRemote(new Radio());
        radioRemote.powerToggle();
        radioRemote.volumeUp();

        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
    
}

