package character;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Monster: Exoskeletons
public enum Exoskeletons{
    Cyrrollalee("Cyrrollalee",7,10,70,75),
    Brandobaris("Brandobaris",3,10,70,30),
    BigBad_Wolf("BigBad_Wolf",1,10,70,15),
    WickedWitch("WickedWitch",2,10,70,25),
    Aasterinian("Aasterinian",4,15,70,45),
    Chronepsish("Chronepsish",6,10,70,60),
    Kiaransalee("Kiaransalee",8,10,70,85),
    St_Shargaas("St_Shargaas",5,10,70,55),
    Merrshaullk("Merrshaullk",10,15,70,55),
    St_Yeenoghu("St_Yeenoghu",9,10,70,90);

    private String name;
    private double level;
    private double damage;
    private double defense;
    private double dodge_chance;

    Exoskeletons(String name, double level,double damage,double defense,double dodge_chance)
    {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodge_chance = dodge_chance;
    }

    public String getName(){
        return this.name;
    }

    public double getLevel(){
        return this.level;
    }

    public double getDamage(){
        return this.damage;
    }

    public double getDefense(){
        return this.defense;
    }

    public double getDodgeChance(){
        return this.dodge_chance;
    }


}