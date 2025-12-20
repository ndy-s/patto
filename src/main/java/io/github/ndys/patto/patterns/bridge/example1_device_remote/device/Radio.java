package io.github.ndys.patto.patterns.bridge.example1_device_remote.device;

public class Radio implements Device {

    @Override
    public void powerOn() {
        System.out.println("Radio is ON");
    }

    @Override
    public void powerOff() {
        System.out.println("Radio is OFF");
    }

    @Override
    public void setVolume(int percent) {
        System.out.println("Radio volume set to " + percent + "%");
    }

    
}

