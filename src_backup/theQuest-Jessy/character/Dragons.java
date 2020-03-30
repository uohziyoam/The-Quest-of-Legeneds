    //Created by Jinshu Yang
    //March 23, 2020

    //The file contains the structure of Monster: Dragons
package character;

public enum Dragons{
    Desghidorrah("Desghidorrah",3,10,70,35),
    Chrysophylax("Chrysophylax",2,10,70,20),
    BunsenBurner("BunsenBurner",4,10,70,45),
    Natsunomeryu("Natsunomeryu",1,10,70,10),
    TheScaleless("TheScaleless",7,10,70,75),
    Kas_Ethelinh("Kas_Ethelinh",5,10,70,60),
    Alexstraszan("Alexstraszan",10,10,70,55),
    Phaarthurnax("Phaarthurnax",6,10,70,60),
    D_Maleficent("D_Maleficent",9,10,70,85),
    TheWeatherbe("TheWeatherbe",8,10,70,80);

    private String name;
    private double level;
    private double damage;
    private double defense;
    private double dodge_chance;

    Dragons(String name,double level,double damage,double defense,double dodge_chance)
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