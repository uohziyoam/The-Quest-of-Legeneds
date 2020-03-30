package avatar.avatarName;

/**
 * Exoskeletons
 */
public enum Exoskeletons {

    Cyrrollalee(7, 700, 800, 75),

    Brandobaris(3, 350, 450, 30),

    BigBadWolf(1, 150, 250, 15),

    WickedWitch(2, 250, 350, 25),

    Aasterinian(4, 400, 500, 45),

    Chronepsish(6, 650, 750, 60),

    Kiaransalee(8, 850, 950, 85),

    StShargaas(5, 550, 650, 55),

    Merrshaullk(10, 1000, 900, 55),

    StYeenoghu(9, 950, 850, 90);

    private double level;
    private double damage;
    private double defense;
    private double dodgeChance;

    Exoskeletons(

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