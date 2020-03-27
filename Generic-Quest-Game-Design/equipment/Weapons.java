package equipment;

public enum Weapons {
    Sword(500, 1, 800, 1),

    Bow(300, 2, 500, 2),

    Scythe(1000, 6, 1100, 2),

    Axe(550, 5, 850, 1),

    Shield(400, 1, 100, 1),

    TSwords(1400, 8, 1600, 2),

    Dagger(200, 1, 250, 1);

    private final double cost;
    private final int minimumLevel;
    private final int numberOfHandsRequired;
    private final double damage;

    Weapons(double cost, int minimumLevel, double damage, int numberOfHandsRequired) {
        this.cost = cost;
        this.minimumLevel = minimumLevel;
        this.numberOfHandsRequired = numberOfHandsRequired;
        this.damage = damage;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return the damage
     */
    public double getDamage() {
        return damage;
    }

    /**
     * @return the minimumLevel
     */
    public int getMinimumLevel() {
        return minimumLevel;
    }

    /**
     * @return the numberOfHandsRequired
     */
    public int getNumberOfHandsRequired() {
        return numberOfHandsRequired;
    }
}
