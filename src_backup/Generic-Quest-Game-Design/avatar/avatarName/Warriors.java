
package avatar.avatarName;

/**
 * Warriors
 */
public enum Warriors {
    Gaerdal_Ironhand(100, 700, 500, 600, 1354, 7),

    Sehanine_Monnbow(600, 700, 800, 500, 2500, 8),

    Muamman_Duathall(300, 900, 500, 750, 2546, 6),

    Flandal_Steelskin(200, 750, 650, 700, 2500, 7);

    private final double mana;
    private final double strength;
    private final double agility;
    private final double dexterity;
    private final double startingMoney;
    private final double startingExperience;

    Warriors(

            double mana,

            double strength,

            double agility,

            double dexterity,

            double startingMoney,

            double startingExperience

    ) {
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.startingMoney = startingMoney;
        this.startingExperience = startingExperience;
    }

    /**
     * @return the agility
     */
    public double getAgility() {
        return agility;
    }

    /**
     * @return the dexterity
     */
    public double getDexterity() {
        return dexterity;
    }

    /**
     * @return the mana
     */
    public double getMana() {
        return mana;
    }

    /**
     * @return the startingExperience
     */
    public double getStartingExperience() {
        return startingExperience;
    }

    /**
     * @return the startingMoney
     */
    public double getStartingMoney() {
        return startingMoney;
    }

    /**
     * @return the strength
     */
    public double getStrength() {
        return strength;
    }

}
