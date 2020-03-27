package avatar.avatarInterface;

import avatar.Avatar;
import board.square.Market;

/**
 * HeroAction
 */
public interface HeroAction {

    public void printStatistics();

    public void castSpell(String targetSpell, Avatar targetAvatar);

    public void changeArmor(String targetArmor);

    public void changeWeapon(String targetWeapon);

    public double manaCostCalculation(Object damageEquipment);

    public double damageCalculation(Object damageEquipment);

    public void buy(String item, Market market);

    public void sell(String item, Market market);

    public void reduceMoney(double cost);

    public void addExperience(double experience);

    public double getMoney();

    public void recover();

    public void useMana(double manaCost);

}