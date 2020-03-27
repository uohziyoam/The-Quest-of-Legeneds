//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Hero
import character.Paladins;
import character.Sorcerers;
import character.Warriors;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import equipment.*;

public class Hero extends Character{
    //Hero class is a subclass of Character class

    private double strength;
    private double agility;
    private double dexerity;
    private double money;
    private double mana;
    private double exp;

    private int row;
    private int col;

    private Function function;

    private Coordinate pos;

    private Armory armor;
    private Weaponry weapon;
    private HashMap<Object,Integer> itemcount;
    private Panel bag;
    private Object hero;

    public Hero(String name,Coordinate startingpos,Function function){
        super(name);
        this.pos = startingpos;
        Variable var = new Variable();
        this.function = function;

        this.hero = var.HERO_TO_ENUM.get(name);
        this.armor = null;
        this.weapon = null;
        this.itemcount = new HashMap<Object,Integer>();
        this.bag = new Panel();
        this.row = 7;
        this.col = 7;

        if(hero instanceof Warriors)
        {
            Warriors warrior = (Warriors) hero;
            initAtt(warrior.getStrength(), warrior.getAgility(),warrior.getDexterity(),warrior.getMana(), warrior.getMoney(), warrior.getExp());
            
        }
        else if(hero instanceof Sorcerers)
        {
            Sorcerers s = (Sorcerers) hero;
            initAtt(s.getStrength(), s.getAgility(),s.getDexterity(),s.getMana(), s.getMoney(), s.getExp());
        }
        else if(hero instanceof Paladins)
        {
            Paladins p = (Paladins) hero;
            initAtt(p.getStrength(), p.getAgility(),p.getDexterity(),p.getMana(), p.getMoney(), p.getExp());
        }
    }

    public void initAtt(double strength,double agility,double dexerity,double mana,double money,double exp){
        this.strength = strength;
        this.agility = agility;
        this.dexerity = dexerity;
        this.mana = mana;
        this.money = money;
        this.exp = exp;
    }

    public void buy(String item,Market market)
    {
        Object obj = Variable.EQUIP_TO_ENUM.get(item);
        Object ret = market.buy(obj,this);
        if(ret != null)
        {
            
            if(obj instanceof IceSpells)
            {
                IceSpells i = (IceSpells) obj;
                System.out.println(ConsoleColors.GREEN+"Successfully bought "+i.name()+ " with $"+i.getMoneyCost()+ConsoleColors.RESET);
                

            }
            else if(obj instanceof LightningSpells)
            {
                LightningSpells i = (LightningSpells) obj;
                System.out.println(ConsoleColors.GREEN+"Successfully bought "+i.name()+ " with $"+i.getMoneyCost()+ConsoleColors.RESET);

            }
            else if(obj instanceof FireSpells)
            {
                FireSpells f = (FireSpells)obj;
                System.out.println(ConsoleColors.GREEN+"Successfully bought "+f.name()+ " with $"+f.getMoneyCost()+ConsoleColors.RESET);

            }
            else if(obj instanceof Potion)
            {
                Potion p = (Potion) obj;
                System.out.println(ConsoleColors.GREEN+"Successfully bought "+p.name()+ " with $"+p.getMoneyCost()+ConsoleColors.RESET);

            }
            else if(obj instanceof Armory)
            {
                Armory a = (Armory) obj;
                System.out.println(ConsoleColors.GREEN+"Successfully bought "+a.name()+ " with $"+a.getMoneyCost()+ConsoleColors.RESET);

            }
            else if(obj instanceof Weaponry)
            {
                Weaponry w = (Weaponry) obj;
                System.out.println(ConsoleColors.GREEN+"Successfully bought "+w.name()+ " with $"+w.getMoneyCost()+ConsoleColors.RESET);
            }

            this.bag.addItem(ret);
            if(this.itemcount.containsKey(ret))
            {
                this.itemcount.put(ret, this.itemcount.get(ret)+1);
            }
            else
            {
                this.itemcount.put(ret, 1);
            }
            
        }

    }

    public Coordinate move(String direction){
        if(direction.equals("W"))
        {
            int i = this.pos.getX()-1;
            int j = this.pos.getY();
            if(i>=0 && i<=row)
            {
                return new Coordinate(i,j);
            }
        }

        if(direction.equals("S"))
        {
            int i = this.pos.getX()+1;
            int j = this.pos.getY();
            if(i>=0 && i<=row)
            {
                return new Coordinate(i,j);
            }
        }

        if(direction.equals("A"))
        {
            int i = this.pos.getX();
            int j = this.pos.getY()-1;
            if(j>=0 && j<=col)
            {
                return new Coordinate(i,j);
            }
        }

        if(direction.equals("D"))
        {
            int i = this.pos.getX();
            int j = this.pos.getY()+1;
            if(j>=0 && j<=col)
            {
                return new Coordinate(i,j);
            }
        }

        return new Coordinate(-1,-1);
    }

    public void setMana(double mana)
    {
        this.mana = mana;
    }

    public void setStrength(double stren)
    {
        this.strength = stren;
    }

    public void setAgility(double ag)
    {
        this.agility = ag;
    }

    public void setDexterity(double d)
    {
        this.dexerity = d;
    }

    public Coordinate getPos(){
        return this.pos;
    }





    public Weaponry getWeapon(){
        return this.weapon;
    }

    public Armory getArmor(){
        return this.armor;
    }

    public void removeItem(Object obj){
        this.bag.removeItem(obj);
        this.itemcount.put(obj,this.itemcount.get(obj)-1);
    }

    public void setPos(Coordinate temp)
    {
        this.pos = new Coordinate(temp);
    }

    public void sell(String item,Market market)
    {
        Object obj = Variable.EQUIP_TO_ENUM.get(item);
        if(itemcount.containsKey(obj))
        {
            if(itemcount.get(obj)>0)
            {
                this.money = this.money + market.sell(obj);
                this.itemcount.put(obj, this.itemcount.get(obj)-1);
                this.bag.removeItem(obj);

            }
            else
            {
                System.out.println("NOT ENOUGH!FAIL TO SELL!");
            }

        }
        else
        {
            System.out.println("FAIL TO SELL!"); 
        }
        
    }

    public double getMoney(){
        return this.money;
    }

    public void setMoney(double n){
        this.money = n;
    }

    public Panel getBag()
    {
        return this.bag;
    }

    public double getStrength(){
        return this.strength;
    }

    public double getAgility(){
        return this.agility;
    }

    public double getDexterity(){
        return this.dexerity;
    }

    public double getMana(){
        return this.mana;
    }


    public double getExp(){
        return this.exp;
    }

    public void displayBag(){
        
    }

    

    public void changeEquipment(){
        this.bag.printByDirection();
        System.out.println("Please enter the name of the equipment that you wish to equip");
        Scanner keyboard = new Scanner(System.in);
        String equip = keyboard.next();
        function.checkInput(equip);
        Object equip_obj = Variable.EQUIP_TO_ENUM.get(equip);
        if(equip_obj instanceof Armory)
        {
            Armory a = (Armory) equip_obj;
            if(this.bag.checkArmory(a))
            {
                this.armor = a;
                System.out.println("Successfully changed to "+a.name());
            }
            else
            {
                System.out.println("Failed");
            }

        }
        else if(equip_obj instanceof Weaponry)
        {
            Weaponry w = (Weaponry) equip_obj;
            if(this.bag.checkWeaponry(w))
            {
                System.out.println("Successfully changed to "+w.name());
                this.weapon = w;
            }
            else
            {
                System.out.println("Failed");
            }
            
        }
    }



    public boolean isDodged(){
        double chance = this.agility*0.01;
        Random r = new Random();
        double temp = r.nextDouble();
        if(temp<=chance)
        {
            return true;
        }
        return false;
    
    }

    public void ifLevelUp()
    {
        
        Scanner keyboard = new Scanner(System.in);
        double required_exp = this.getlevel()*10;
        if(this.exp>=required_exp)
        {
            this.setExp(0);
            this.setLevel(this.getlevel()+1);
            this.setMana(this.getMana()+this.getMana()*0.1);
            this.setAgility(this.getAgility()*1.05);
            this.setDexterity(this.getDexterity()*1.05);
            this.setStrength(this.getStrength()*1.05);
            System.out.println("Congrats!You level up!");

            if(this.hero instanceof Warriors)
            {
                this.setAgility(this.getAgility()*1.10);
                this.setDexterity(this.getDexterity()*1.05);
                this.setStrength(this.getStrength()*1.10);
            }
            else if(this.hero instanceof Paladins)
            {
                this.setAgility(this.getAgility()*1.05);
                this.setDexterity(this.getDexterity()*1.10);
                this.setStrength(this.getStrength()*1.10);

            }
            else if(this.hero instanceof Sorcerers)
            {
                this.setAgility(this.getAgility()*1.10);
                this.setDexterity(this.getDexterity()*1.10);
                this.setStrength(this.getStrength()*1.05);

            }

        }

    }

    public void setExp(double exp){
        this.exp = exp;
    }

    public void setWeapon(Weaponry w)
    {
        this.weapon = w;
    }

    public void setArmor(Armory a)
    {
        this.armor = a;
    }

    public void isDead()
    {
        if(this.getHp()<=0)
        {
            super.setDead(true);
        }
    }


    public void displayInfo(){
        String type;
        if(this.hero instanceof Warriors)
        {
            type = "Warrior";

        }
        else if(this.hero instanceof Paladins)
        {
            type = "Paladin";
        }
        else
        {
            type = "Sorcerer";
        }
        System.out.println("-----------------------------");
        System.out.println("NAME: "+super.getName());
        System.out.println("TYPE: "+type);
        System.out.println("LEVEL: "+super.getlevel());
        System.out.println("HP: "+super.getHp());
        System.out.println("STRENGTH: "+this.getStrength());
        System.out.println("AGILITY: "+this.getAgility());
        System.out.println("DEXTERITY: "+this.getDexterity());
        System.out.println("MANA: "+this.getMana());
        System.out.println("MONEY: "+this.getMoney());
        System.out.println("EXP: "+this.getExp());
        System.out.println("ISDEAD: "+super.getDead());
        System.out.println("-----------------------------");

    }

    public void displayInfoFight()
    {
        System.out.println("-----------------------------");
        System.out.println("NAME: "+super.getName());
        System.out.println("LEVEL: "+super.getlevel());
        System.out.println("HP: "+super.getHp());
        System.out.println("MANA: "+this.getMana());
        System.out.println("EXP: "+this.getExp());
        System.out.println("STRENGTH: "+this.getStrength());
        System.out.println("AGILITY: "+this.getAgility());
        System.out.println("DEXTERITY: "+this.getDexterity());
        System.out.println("ISDEAD: "+super.getDead());
        System.out.println("-----------------------------"); 
    }

    public void Inventories()
    {
        System.out.println("-----------------------------");
        System.out.println("NAME: "+super.getName());
        System.out.println("LEVEL: "+super.getlevel());
        System.out.println("HP: "+super.getHp());
        System.out.println("MANA: "+this.getMana());
        System.out.println("EXP: "+this.getExp());
        System.out.println("STRENGTH: "+this.getStrength());
        System.out.println("AGILITY: "+this.getAgility());
        System.out.println("DEXTERITY: "+this.getDexterity());
        System.out.println("MONEY: "+this.getMoney());
        System.out.println("ISDEAD: "+super.getDead());
        System.out.println("-----------------------------"); 
    }
    
}