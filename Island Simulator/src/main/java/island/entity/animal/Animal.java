package island.entity.animal;

import island.Island;
import island.Location;
import island.config.AnimalParameters;
import island.util.Direction;
import island.util.EntityType;
import island.util.RandomGenerator;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    private final UUID id;
    private final EntityType type;
    private double weight;
    private double foodSatiation;
    private boolean isAlive;
    private Location location;
    private int row;
    private int col;

    public Animal(EntityType type, Location location) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.weight = AnimalParameters.getMaxWeight(type);
        this.foodSatiation = weight / 2;
        this.isAlive = true;
        this.location = location;
        if (location != null) {
            this.row = location.getRow();
            this.col = location.getCol();
        }
    }

    public abstract void eat();
    
    public void move(Island island) {
        if (!isAlive || AnimalParameters.getSpeed(type) == 0) {
            return;
        }

        int steps = RandomGenerator.getRandomNumber(0, AnimalParameters.getSpeed(type));
        
        for (int i = 0; i < steps; i++) {
            Direction direction = chooseDirection();
            int newRow = row + direction.getRowDelta();
            int newCol = col + direction.getColDelta();
            
            if (island.isValidLocation(newRow, newCol)) {
                if (location != null) {
                    location.removeAnimal(this);
                }
                
                this.row = newRow;
                this.col = newCol;
                this.location = island.getLocation(newRow, newCol);
                
                if (location != null) {
                    location.addAnimal(this);
                }
            }
        }
    }
    
    public Direction chooseDirection() {
        return RandomGenerator.getRandomDirection();
    }
    
    public void reproduce(Island island) {
        if (!isAlive || ThreadLocalRandom.current().nextInt(100) > 30) {
            return;
        }
        
        List<Animal> sameTypeAnimals = location.getAnimalsByType(type);
        
        if (sameTypeAnimals.size() >= 2) {
            int offspringCount = island.getParameters().getOffspring(type);
            
            for (int i = 0; i < offspringCount; i++) {
                if (location.getAnimalCountByType(type) < AnimalParameters.getMaxCount(type)) {
                    Animal offspring = createOffspring(location);
                    location.addAnimal(offspring);
                }
            }
        }
    }
    
    protected abstract Animal createOffspring(Location location);
    
    public void decreaseSatiation() {
        foodSatiation -= weight * 0.1;
        if (foodSatiation <= 0) {
            die();
        }
    }
    
    public void increaseSatiation(double foodAmount) {
        foodSatiation += foodAmount;
        double maxSatiation = AnimalParameters.getMaxWeight(type);
        if (foodSatiation > maxSatiation) {
            foodSatiation = maxSatiation;
        }
    }
    
    public void die() {
        isAlive = false;
        if (location != null) {
            location.removeAnimal(this);
            location = null;
        }
    }
    
    public UUID getId() {
        return id;
    }
    
    public EntityType getType() {
        return type;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
        if (location != null) {
            this.row = location.getRow();
            this.col = location.getCol();
        }
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public double getFoodRequired() {
        return AnimalParameters.getFoodRequired(type);
    }
    
    @Override
    public String toString() {
        return type.getIcon();
    }
}