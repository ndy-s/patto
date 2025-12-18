package io.github.ndys.patto.patterns.builder.example1_computer_builder;

public interface ComputerBuilder {

    void buildCpu();
    void buildRam();
    void buildStorage();
    void buildGpu();

    Computer getResult();

}

