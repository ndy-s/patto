package io.github.ndys.patto.patterns.bridge.example1_device_remote.device;

public interface Device {
    void powerOn();
    void powerOff();
    void setVolume(int percent);
}

