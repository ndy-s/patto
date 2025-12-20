package io.github.ndys.patto.patterns.bridge.example1_device_remote.remote;

import io.github.ndys.patto.patterns.bridge.example1_device_remote.device.Device;

public class BasicRemote extends Remote {
    
    private int volume = 50;

    public BasicRemote(Device device) {
        super(device);
    }

    @Override
    public void powerToggle() {
        device.powerOn();
    }

    @Override
    public void volumeUp() {
        volume += 10;
        device.setVolume(volume);
    }
    
}

