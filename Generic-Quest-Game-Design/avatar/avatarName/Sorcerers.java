package avatar.avatarName;

/**
 * Sorcerers
 */
public enum Sorcerers {
    Garl_Glittergold(700, 550, 600, 500, 2500, 7),

    Rillifane_Rallathil(1300, 750, 450, 500, 2500, 9),

    Segojan_Earthcaller(900, 800, 500, 650, 2500, 5),

    Skoraeus_Stonebones(800, 850, 600, 450, 2500, 6);

    private final double mana;
    private final double strength;
    private final double agility;
    private final double dexterity;
    private final double startingMoney;
    private final double startingExperience;

    Sorcerers(

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