//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of ACTION
public enum Action {
    REGULAR_ATTACK("REGULAR_ATTACK"), 
    CAST_SPELL("CAST_SPELL"), 
    USE_POTION("USE_POTION"), 
    CHANGE_EQUIPMENT("CHANGE_EQUIPMENT"),
    INVALID("INVALID");
    private String name;

    Action(String name){
        //constructur
        this.name = name;
    }

    public boolean equals(Action other)
    {
        if(this.name.equals(other.name))
        {
            return true;
        }

        return false;

    }
}