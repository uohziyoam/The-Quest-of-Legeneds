package avatar.avatarName;

/**
 * Spirits
 */
public enum Spirits {
    Andrealphus(2, 600, 500, 40),

    AimHaborym(1, 450, 350, 35),

    Andromalius(3, 550, 450, 25),

    Chiangshih(4, 700, 600, 40),

    FallenAngel(5, 800, 700, 50),

    Ereshkigall(6, 950, 450, 35),

    Melchiresas(7, 350, 150, 75),

    Jormunngand(8, 600, 900, 20),

    Rakkshasass(9, 550, 600, 35),

    Taltecuhtli(10, 300, 200, 50);

    private double level;
    private double damage;
    private double defense;
    private double dodgeChance;

    Spirits(

            double level,

            double damage,

            double defense,

            double dodgeChance

    ) {
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    /**
     * @return the damage
     */
    public double getDamage() {
        return damage;
    }

    /**
     * @return the defense
     */
    public double getDefense() {
        return defense;
    }

    /**
     * @return the dodgeChance
     */
    public double getDodgeChance() {
        return dodgeChance;
    }

    /**
     * @return the level
     */
    public double getLevel() {
        return level;
    }
}
