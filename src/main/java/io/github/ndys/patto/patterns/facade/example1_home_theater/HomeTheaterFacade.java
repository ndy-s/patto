package io.github.ndys.patto.patterns.facade.example1_home_theater;

public class HomeTheaterFacade {

    private final Amplifier amplifier;
    private final DvdPlayer dvdPlayer;
    private final Projector projector;
    private final Lights lights;

    public HomeTheaterFacade(
        Amplifier amplifier, 
        DvdPlayer dvdPlayer, 
        Projector projector, 
        Lights lights
    ) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("\nStarting movie...");
        lights.dim();
        projector.on();
        amplifier.on();
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("\nStopping movie...");
        dvdPlayer.off();
        amplifier.off();
        projector.off();
        lights.on();
    }

    
}

