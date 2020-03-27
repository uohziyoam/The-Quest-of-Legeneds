package avatar;

import board.square.Coordinate;
import board.square.Square;
import config.DamageType;
import config.VARIABLES;

/**
 * Avatar
 */
abstract public class Avatar extends Square {
    private String name;

    private double currentHP;

    private double maxHP;

    private double level;

    private boolean isDead;

    private Coordinate curLocation;

    public Avatar(String name, Coordinate curLocation) {
        this.name = name;
        level = 1;
        currentHP = level * 100;
        maxHP = level * 100;
        isDead = false;
        this.curLocation = curLocation;
    }

    public Avatar(String name) {
        this.name = name;
        level = 1;
        currentHP = level * 100;
        isDead = false;
    }

    /**
     * @return the currentHp
     */
    public double getCurrentHp() {
        return currentHP;
    }

    /**
     * @return the maxHP
     */
    public double getMaxHP() {
        return maxHP;
    }

    /**
     * @return the level
     */
    public double getLevel() {
        return level;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the isDead
     */
    public boolean isDead() {
        return this.currentHP <= 0;
    }

    /**
     * @return the curLocation
     */
    public Coordinate getCurLocation() {
        return curLocation;
    }

    /**
     * @param curLocation the curLocation to set
     */
    public void setCurLocation(Coordinate curLocation) {
        this.curLocation = curLocation;
    }

    /**
     * @param hp the hp to set
     */
    public void setHp(double hp) {
        this.currentHP = hp;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(double level) {
        this.level = level;
    }

    abstract void regularAttack(Avatar targetAvatar);

    public void revive() {
        this.setHp(maxHP / 2);
    };

    public void levelUp() {
        this.level += 1;
    }

    /**
     * @param maxHP the maxHP to set
     */
    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public double makeDamage(double damage) {
        return damage;
    }

    public void receiveDamage(DamageType damageType, double damage) {

        this.setHp(this.getCurrentHp() - damage < 0 ? 0 : this.getCurrentHp() - damage);
        isDead = getCurrentHp() <= 0;
    }

    public boolean successfullyDodge() {
        return false;
    }

    
}