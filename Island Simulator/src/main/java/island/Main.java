package island;

import island.config.SimulationParameters;

public class Main {
    public static void main(String[] args) {
        SimulationParameters parameters = new SimulationParameters();
        
        SimulationRunner runner = new SimulationRunner(parameters);
        runner.startSimulation();
    }
}