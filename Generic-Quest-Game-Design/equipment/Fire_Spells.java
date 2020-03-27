package equipment;

public enum Fire_Spells {
    FlameTornado(700, 4, 850, 300),

    LavaCommet(800, 7, 1000, 550),

    BreathofFire(350, 1, 450, 100),

    HeatWave(450, 2, 600, 150);

    private final double cost;
    private final int minimumLevel;
    private final double damage;
    private final double manaCost;

    Fire_Spells(double cost, int minimumLevel, double damage, double manaCost) {
        this.cost = cost;
        this.minimumLevel = minimumLevel;
        this.damage = damage;
        this.manaCost = manaCost;
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
     * @return the manaCost
     */
    public double getManaCost() {
        return manaCost;
    }

    /**
     * @return the minimumLevel
     */
    public int getMinimumLevel() {
        return minimumLevel;
    }

}
