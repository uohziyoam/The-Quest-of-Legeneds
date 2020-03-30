package equipment;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of FireSpells
public enum FireSpells{
    Flame_Tornado(700,4,850,300),
    Breath_of_Fire(350,1,450,100),
    Heat_Wave(450,2,600,150),
    Lava_Commet(800,7,1000,550);

    private double money_cost;
    private int required_level;
    private double damage;
    private double mana_cost;

    FireSpells(double money_cost,int required_level,double damage,double mana_cost)
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