package io.github.ndys.patto.patterns.builder.example1_computer_builder;

public class OfficeComputerBuilder implements ComputerBuilder {

    private final Computer computer = new Computer();

    @Override
    public void buildCpu() {
        computer.setCpu("Intel i5");
    }

    @Override
    public void buildRam() {
        computer.setRam("16GB");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("512 SSD");
    }

    @Override
    public void buildGpu() {
        computer.setGpu("Integrated Graphics");
    }

    @Override
    public Computer getResult() {
        return computer;
    }

    
}

