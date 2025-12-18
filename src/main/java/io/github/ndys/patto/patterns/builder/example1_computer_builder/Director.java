package io.github.ndys.patto.patterns.builder.example1_computer_builder;

public class Director {

    public void construct(ComputerBuilder builder) {
        builder.buildCpu();
        builder.buildRam();
        builder.buildStorage();
        builder.buildGpu();
    }
    
}

