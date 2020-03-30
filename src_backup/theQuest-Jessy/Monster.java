//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Monster
import character.Dragons;
import character.Exoskeletons;
import character.Spirits;
import java.util.Random;

public class Monster extends Character{
    private double damage;
    private double defense;
    private double dodge;
    
    public Monster(String name,double level)
    {
        super(name);
        Object monster = Variable.MONSTER_TO_ENUM.get(name);
        
        if(monster instanceof Dragons)
        {
            Dragons warrior = (Dragons) monster;
            initAtt(level, warrior.getDamage(),warrior.getDefense(),warrior.getDodgeChance());
            
        }
        else if(monster instanceof Spirits)
        {
            Spirits s = (Spirits) monster;
            initAtt(level, s.getDamage(),s.getDefense(),s.getDodgeChance());
        }
        else if(monster instanceof Exoskeletons)
        {
            Exoskeletons p = (Exoskeletons) monster;
            initAtt(level, p.getDamage(),p.getDefense(),p.getDodgeChance());
        }
    }

    public void initAtt(double level,double d,double defense,double dodgechangce)
    {
        this.setLevel(level);
        this.setHp(level*10);
        this.damage = d;
        this.defense = defense;
        this.dodge = dodgechangce;
    }

    public void setLevel(double level)
    {
        super.setLevel(level);

    }

    public void setDamage(double d){
        this.damage = d;

    }

    public double getDamage(){
        return this.damage;
    }

    public void setDefense(double d){
        this.defense = d;
    }

    public double getDefense(){
        return this.defense;
    }

    public void setDodge(double d){
        this.dodge = d;
    }

    public double getDodge(){
        return this.dodge;
    }

    public boolean isDodged(){
        double chance = this.dodge*0.005;
        Random r = new Random();
        double temp = r.nextDouble();
        if(temp<=chance)
        {
            System.out.println(ConsoleColors.BLUE+"MONSTER DODGED!"+ConsoleColors.RESET);
            return true;
        }
        return false;
    
    }

    public void isDead()
    {
        if(this.getHp()<=0)
        {
            super.setDead(true);
        }
    }

    public void displayInfoFight()
    {
        System.out.println("-----------------------------");
        System.out.println("NAME: "+super.getName());
        System.out.println("HP: "+super.getHp());
        System.out.println("ISDEAD: "+super.getDead());
        System.out.println("-----------------------------"); 
    }

    
}