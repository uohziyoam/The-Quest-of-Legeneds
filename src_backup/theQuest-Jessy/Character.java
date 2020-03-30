//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Character
public class Character{
    private String name; 
    private double hp;
    private double level;
    private boolean isDead; //if the character is dead
    

    public Character(String name){
        //constructer
        this.name = name;
        this.level = 1;
        this.hp = level*10;
        this.isDead = false;

    }

    public Character(){
        this.name = "";
        this.level = 1;
        this.hp = level*1;
        this.isDead = false;

    }



    public double getHp(){
        return this.hp;
    }

    public String getName()
    {
        return this.name;
    }

    public double getlevel(){
        return this.level;
    }

    public boolean getDead(){
        return this.isDead;
    }

    public void setHp(double newhp){
        this.hp = newhp;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLevel(double newlevel){
        this.level = newlevel;
    }

    public void setDead(boolean ifdead){
        this.isDead = ifdead;
    }
}