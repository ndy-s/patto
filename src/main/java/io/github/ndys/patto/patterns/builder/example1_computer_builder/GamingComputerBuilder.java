package io.github.ndys.patto.patterns.builder.example1_computer_builder;

public class GamingComputerBuilder implements ComputerBuilder {

    private final Computer computer = new Computer();

    @Override
    public void buildCpu() {
        computer.setCpu("Intel i9");
    }

    @Override
    public void buildRam() {
        computer.setRam("32GB");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("1TB NVMe SSD");
    }

    @Override
    public void buildGpu() {
        computer.setGpu("RTX 4090");
    }

    @Override
    public Computer getResult() {
        return computer;
    }

    
}

