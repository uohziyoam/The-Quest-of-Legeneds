//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Obstacle
public class Obstacle extends Cell{
    private boolean ifMonsters;
    public Obstacle(Coordinate index,boolean ifAcc)
    {
        super(index,false);
        this.ifMonsters = false;
    }

    @Override
    public String toString(){
        return "X";
    }

}