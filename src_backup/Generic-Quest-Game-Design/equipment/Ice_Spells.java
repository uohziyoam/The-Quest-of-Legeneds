package equipment;

public enum Ice_Spells {
    SnowCanon(500, 2, 650, 250),

    FrostBlizzard(750, 5, 850, 350),

    Arcticstorm(700, 6, 800, 300),

    IceBlade(250, 1, 450, 100);

    private final double cost;
    private final int minimumLevel;
    private final double damage;
    private final double manaCost;

    Ice_Spells(double cost, int minimumLevel, double damage, double manaCost) {
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
