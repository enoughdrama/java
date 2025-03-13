# Island Simulation

This project simulates an island ecosystem with various animal species and plants.

## Requirements

- Java 17 or higher
- Maven

## Running the simulation

To build and run the project:

```bash
mvn clean package
java -jar target/island-simulation-1.0-SNAPSHOT.jar
```

## Project structure

The simulation implements:
- 5 predator types: Wolf, Boa, Fox, Bear, Eagle
- 10 herbivore types: Horse, Deer, Rabbit, Mouse, Goat, Sheep, Boar, Buffalo, Duck, Caterpillar
- Plants

Animals can:
- Eat plants or other animals
- Move around the island
- Reproduce
- Die from starvation or being eaten

The simulation runs with multithreading to improve performance.
