package character;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Monster: Spirits
public enum Spirits{
    Andrealphus("Andrealphus",2,10,70,40),
    Aim_Haborym("Aim_Haborym",1,15,70,35),
    Andromalius("Andromalius",3,15,70,25),
    Chiang_shih("Chiang_shih",4,10,70,40),
    FallenAngel("FallenAngel",5,10,70,50),
    Ereshkigall("Ereshkigall",6,15,45,35),
    Melchiresas("Melchiresas",7,10,70,75),
    Jormunngand("Jormunngand",8,10,70,20),
    Rakkshasass("Rakkshasass",9,10,70,35),
    Taltecuhtli("Taltecuhtli",10,15,70,50);

    private String name;
    private double level;
    private double damage;
    private double defense;
    private double dodge_chance;
    

    Spirits(String name,double level,double damage,double defense,double dodge_chance)
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