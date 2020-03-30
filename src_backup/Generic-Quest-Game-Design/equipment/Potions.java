package equipment;

public enum Potions {
    HealingPotion(250, 1, 100),

    StrengthPotion(200, 1, 75),

    MagicPotion(350, 2, 100),

    LuckElixir(500, 4, 65),

    MermaidTears(850, 5, 100),

    Ambrosia(1000, 8, 150);

    // Name/cost/minimumLevel/attributeIncrease

    private final double cost;
    private final int minimumLevel;
    private final double attributeIncrease;

    Potions(double cost, int minimumLevel, double attributeIncrease) {
        this.cost = cost;
        this.minimumLevel = minimumLevel;
        this.attributeIncrease = attributeIncrease;
    }

    /**
     * @return the attributeIncrease
     */
    public double getAttributeIncrease() {
        return attributeIncrease;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return the minimumLevel
     */
    public int getMinimumLevel() {
        return minimumLevel;
    }

}
