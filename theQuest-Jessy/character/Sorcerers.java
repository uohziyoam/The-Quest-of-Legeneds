package character;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Hero: Sorcerers
public enum Sorcerers{
    Garl_Glittergold("Garl_Glittergold",700,550,50,50,2500,7),
    Rillifane_Rallathil("Rillifane_Rallathil",1300,750,50,50,2500,9),
    Segojan_Earthcaller("Segojan_Earthcaller",900,800,50,50,2500,5),
    Skoraeus_Stonebones("Skoraeus_Stonebones",800,850,50,50,2500,6);

    private String name;
    private double mana;
    private double strength;
    private double agility;
    private double dexterity;
    private double starting_money;
    private double starting_exp;

    Sorcerers(String name,double mana,double strength,double agility,double dexterity,double starting_money,double starting_exp)
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