package equipment;
//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Potion
public enum Potion{
    //1:HP
    //2:Stren
    //3: mana,
    //4 dex
    //5 agi
    Healing_Potion(250,1,1,100),
    Strength_Potion(200,1,2,75),
    Magic_Potion(350,2,3,100),
    Luck_Elixir(500,4,4,65),
    Mermaid_Tears(850,5,1,100),
    Ambrosia(100,8,1,150);

    private double money_cost;
    private int required_level;
    private int att_Type;
    private double att_Incre;

    Potion(double money_cost,int required_level,int att_Type,double att_Incre)
    {
        this.money_cost = money_cost;
        this.required_level = required_level;
        this.att_Type = att_Type;
        this.att_Incre = att_Incre;
    }

    public double getMoneyCost()
    {
        return this.money_cost;
    }

    public int getRequiredLevel(){
        return this.required_level;
    }

    public int getAttType(){
        return this.att_Type;
    }

    public double getAttIncre(){
        return this.att_Incre;
    }

    public void printFields(){
        System.out.println(this);
    }

    
    public String printDetails(){
        String str = "Name : "+this + "\n";
        str = str + "Cost : " +this.money_cost+"\n";
        str = str + "Required Level : " +this.required_level+"\n";
        str = str + "Attribute Increment : " +this.att_Incre+"\n";
        return str;
    }
    public void printItem(){
        System.out.print(this.name()+"    LEVEL: "+this.required_level+ "    Cost: "+this.money_cost);
    }
}