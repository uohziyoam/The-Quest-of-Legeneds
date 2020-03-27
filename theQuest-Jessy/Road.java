//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Road
public class Road extends Cell{
    private boolean ifMonsters;
    public Road(Coordinate index,boolean ifAcc)
    {
        super(index,ifAcc);
    }

    @Override
    public String toString(){
        return " ";
    }

}