package equipment;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Armory
public enum Armory{
    Platinum_Shield(150,1,200),
    Breastplate(350,3,600),
    Full_Body_Armor(1000,8,1100),
    Wizard_Shield(1200,10,1500),
    Speed_Boots(550,4,600);

    private double money_cost;
    private int required_level;
    private double damage_reduction;

    Armory(double money_cost,int required_level,double damage)
    {
        this.money_cost = money_cost;
        this.required_level = required_level;
        this.damage_reduction = damage;
    }

    public double getMoneyCost()
    {
        return this.money_cost;
    }

    public int getRequiredLevel(){
        return this.required_level;
    }

    public double getDamage(){
        return this.damage_reduction;
    }

    public void printFields(){
        System.out.println("  Name  "+" "+"  Cost  "+ " "+"  Level  "+ " "+"  Damage Reduction  "+ " ");
    }


    public String printDetails(){
        String str = "Name : "+this + "\n";
        str = str + "Cost : " +this.money_cost+"\n";
        str = str + "Required Level : " +this.required_level+"\n";
        str = str + "Damage Reduction : " +this.damage_reduction+"\n";
        return str;
    }

    public void printItem(){
        System.out.print(this.name()+"    LEVEL: "+this.required_level+ "    Cost: "+this.money_cost);
    }

}