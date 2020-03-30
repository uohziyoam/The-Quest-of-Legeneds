package avatar.avatarName;

/**
 * Dragons
 */
public enum Dragons {
    Desghidorrah(3, 300, 400, 35),

    Chrysophylax(2, 200, 500, 20),

    BunsenBurner(4, 400, 500, 45),

    Natsunomeryu(1, 100, 200, 10),

    TheScaleless(7, 700, 600, 75),

    KasEthelinh(5, 600, 500, 60),

    Alexstraszan(10, 1000, 9000, 55),

    Phaarthurnax(6, 600, 700, 60),

    DMaleficent(9, 900, 950, 85),

    TheWeatherbe(8, 800, 900, 80);

    private double level;
    private double damage;
    private double defense;
    private double dodgeChance;

    Dragons(

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
