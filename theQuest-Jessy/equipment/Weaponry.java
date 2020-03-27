package equipment;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Weaponry
public enum Weaponry{
    Sword(500,1,800,1),
    Bow(300,2,500,1),
    Scythe(1000,6,1100,2),
    Axe(1200,10,1500,1),
    Shield(1200,10,1500,1),
    TSwords(1400,8,1600,2),
    Dagger(200,1,250,1);

    private double money_cost;
    private int required_level;
    private double damage;
    private int required_hands;

    Weaponry(double money_cost,int required_level,double damage,int handnum)
    {
        this.money_cost = money_cost;
        this.required_level = required_level;
        this.damage = damage;
        this.required_hands = handnum;
    }

    public double getMoneyCost()
    {
        return this.money_cost;
    }

    public int getRequiredLevel(){
        return this.required_level;
    }

    public double getDamage(){
        return this.damage;
    }

    public int getRequiredHands(){
        return this.required_hands;
    }


    public String printDetails(){
        String str = "Name : "+this + "\n";
        str = str + "Cost : " +this.money_cost+"\n";
        str = str + "Required Level : " +this.required_level+"\n";
        str = str + "Required Hands : " +this.required_hands+"\n";
        return str;
    }
    public void printItem(){
        System.out.print(this.name()+"    LEVEL: "+this.required_level+ "    Cost: "+this.money_cost);
    }

}