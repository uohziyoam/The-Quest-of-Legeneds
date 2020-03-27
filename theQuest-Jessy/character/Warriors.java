package character;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Hero: Warriors
public enum Warriors{
    Gaerdal_Ironhand("Gaerdal_Ironhand",100,700,50,50,1354,7),
    Sehanine_Monnbow("Sehanine_Monnbow",600,700,50,50,2500,8),
    Muamman_Duathall("Muamman_Duathall",300,900,50,50,2546,6),
    Flandal_Steelskin("Flandal_Steelskin",200,750,50,50,2500,7);

    private String name;
    private double mana;
    private double strength;
    private double agility;
    private double dexterity;
    private double starting_money;
    private double starting_exp;

    Warriors(String name, double mana,double strength,double agility,double dexterity,double starting_money,double starting_exp)
    {
        this.name = name;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.starting_money = starting_money;
        this.starting_exp = starting_exp;
    }

    public String getName(){
        return this.name;
    }

    public double getMana(){
        return this.mana;
    }

    public double getStrength(){
        return this.strength;
    }

    public double getAgility(){
        return this.agility;
    }

    public double getDexterity(){
        return this.dexterity;
    }

    public double getMoney(){
        return this.starting_money;
    }

    public double getExp(){
        return this.starting_exp;
    }

}