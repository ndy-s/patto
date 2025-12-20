package io.github.ndys.patto.patterns.bridge.example1_device_remote.remote;

import io.github.ndys.patto.patterns.bridge.example1_device_remote.device.Device;

public abstract class Remote {

    protected Device device;

    protected Remote(Device device) {
        this.device = device;
    }

    public abstract void powerToggle();
    public abstract void volumeUp();

}

