package character;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Hero: Paladins
public enum Paladins{
    Solonor_Thelandira("Solonor_Thelandira",300,750,50,50,2500,7),
    Sehanine_Moonbow("Sehanine_Moonbow",300,750,50,50,500,7),
    Skoraeus_Stonebones("Skoraeus_Stonebones",250,650,50,50,2500,4),
    Garl_Glittergold("Garl_Glittergold",100,600,50,50,500,5);

    private String name;
    private double mana;
    private double strength;
    private double agility;
    private double dexterity;
    private double starting_money;
    private double starting_exp;

    Paladins(String name,double mana,double strength,double agility,double dexterity,double starting_money,double starting_exp)
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