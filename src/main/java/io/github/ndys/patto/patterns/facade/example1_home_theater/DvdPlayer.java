package io.github.ndys.patto.patterns.facade.example1_home_theater;

public class DvdPlayer {

    void on() {
        System.out.println("DVD Player on");
    }

    void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    void off() {
        System.out.println("DVD Player off");
    }
    
}

