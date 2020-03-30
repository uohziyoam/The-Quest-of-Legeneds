//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of AttributeType
public class AttributeType{

    private String name;
    private int number;

    public AttributeType(String name,int number){
        this.name = name;
        this.number = number;
    }

    public void setAtt(int Att){
        this.number = Att;
    }

    public void changeAtt(int delta){
        this.number = this.number + delta;
    }

    public int getAtt(){
        return this.number;
    }

    @Override
    public String toString(){
        return this.name+" : "+this.number;
    }

}