//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of itemlist
import equipment.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class itemList{
    private ArrayList<Object> items;
    private String listName;
    public itemList(){
        items = new ArrayList<Object>();
    }

    public itemList(ArrayList imp){
        for(int i = 0;i<imp.size();i++)
        {
            items.add(imp.get(i));
        }

    }

    public Object getItem(int i){
        return items.get(i-1);
    }

    public void addItem(Object item){
        this.items.add(item);
    }

    public void removeItem(int i){
        this.items.remove(i-1);
    }

    @Override
    public String toString(){
        String str = this.listName + "/n";
        
        for(int i = 0;i<this.items.size();i++)
        {
           String temp = (i+1)+"/n"+this.items.get(i);
           str = str + temp;
        }

        return str;


    }

}