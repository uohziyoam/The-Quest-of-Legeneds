package equipment;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of IceSpells
public enum IceSpells{
    Snow_Canon(500,2,650,250),
    Frost_Blizzard(750,5,850,350),
    Arctic_storm(700,6,800,300),
    Ice_Blade(250,1,450,100);

    private double money_cost;
    private int required_level;
    private double damage;
    private double mana_cost;

    IceSpells(double money_cost,int required_level,double damage,double mana_cost)
    {
        this.money_cost = money_cost;
        this.required_level = required_level;
        this.damage = damage;
        this.mana_cost = mana_cost;
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

    public double getManaCost(){
        return this.mana_cost;
    }

    public void printFields(){
        System.out.println("  Name  "+" "+"  Cost  "+ " "+"  Level  "+ " "+"  Damage  "+ " "+"  Mana Cost "+ " ");
    }

    public String printDetails(){
        String str = "Name : "+this + "\n";
        str = str + "Cost : " +this.money_cost+"\n";
        str = str + "Required Level : " +this.required_level+"\n";
        str = str + "Damage : " +this.damage+"\n";
        str = str + "Mana Cost : " +this.mana_cost+"\n";
        return str;
    }
    public void printItem(){
        System.out.print(this.name()+"    LEVEL: "+this.required_level+ "    Cost: "+this.money_cost);
    }
}