package io.github.ndys.patto.patterns.bridge.example1_device_remote.device;

public class Tv implements Device {

    @Override
    public void powerOn() {
        System.out.println("TV is ON");
    }

    @Override
    public void powerOff() {
        System.out.println("TV is OFF");
    }

    @Override
    public void setVolume(int percent) {
        System.out.println("TV volume set to " + percent + "%");
    }

    
}

