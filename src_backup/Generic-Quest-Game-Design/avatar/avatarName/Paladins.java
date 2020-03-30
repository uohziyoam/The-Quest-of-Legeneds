package avatar.avatarName;

/**
 * Paladins
 */
public enum Paladins {
    Solonor_Thelandira(300, 750, 650, 700, 2500, 7),

    Sehanine_Moonbow(300, 750, 700, 700, 2500, 7),

    Skoraeus_Stonebones(250, 650, 600, 350, 2500, 4),

    Garl_Glittergold(100, 600, 500, 400, 2500, 5);

    private final double mana;
    private final double strength;
    private final double agility;
    private final double dexterity;
    private final double startingMoney;
    private final double startingExperience;

    Paladins(

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