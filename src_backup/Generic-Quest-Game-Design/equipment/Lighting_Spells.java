package equipment;

public enum Lighting_Spells {
    ThunderBlast(750, 4, 950, 400),

    ElectricArrows(550, 5, 650, 200),

    SparkNeedles(500, 2, 600, 200),

    LightningDagger(400, 1, 500, 150);

    private final double cost;
    private final int minimumLevel;
    private final double damage;
    private final double manaCost;

    Lighting_Spells(double cost, int minimumLevel, double damage, double manaCost) {
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
