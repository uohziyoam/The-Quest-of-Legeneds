package equipment;

public enum Armors {
    PlatinumShield(150, 1, 200),

    Breastplate(350, 3, 600),

    FullBodyArmor(1000, 8, 1100),

    WizardShield(1200, 10, 1500),

    SpeedBoots(550, 4, 600);

    private final double cost;
    private final int minimumLevel;
    private final double damageReduction;

    Armors(double cost, int minimumLevel, double damageReduction) {
        this.cost = cost;
        this.minimumLevel = minimumLevel;
        this.damageReduction = damageReduction;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return the damageReduction
     */
    public double getDamageReduction() {
        return damageReduction;
    }

    /**
     * @return the minimumLevel
     */
    public int getMinimumLevel() {
        return minimumLevel;
    }

}
