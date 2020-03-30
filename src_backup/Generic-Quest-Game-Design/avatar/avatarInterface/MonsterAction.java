package avatar.avatarInterface;

/**
 * MonsterAction
 */
public interface MonsterAction {

    /**
     * @return the damage
     */
    public double getDamage();

    /**
     * @return the defense
     */
    public double getDefense();

    /**
     * @return the dodge
     */
    public double getDodge();

    /**
     * @return the worth
     */
    public double getWorth();

    public void printStatistics();

}