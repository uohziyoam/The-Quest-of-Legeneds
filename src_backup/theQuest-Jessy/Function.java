//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Function class
public class Function{
    private Board map; //the grid
    private Hero[] heros; //the hero's team

    public Function(Board map, Hero[] heros){
		//constructer
        this.map = map;
        this.heros = heros;
	}
	
	public void setMap(Board newmap)
	{
		this.map = newmap;
	}

    public void checkInput(String str){
		if(str.equals("quit") || str.equals("QUIT"))
		{
            System.out.println("YOU QUIT THE GAME");
			System.exit(0);
		}
		if(str.equals("MAP") || str.equals("map"))
		{
			this.map.display_board();
		}
		if(str.equals("info") || str.equals("INFO"))
		{
			System.out.println("HERO'S CONDITION:");
			for(int i = 0;i<this.heros.length;i++)
			{
				this.heros[0].Inventories();
			}
		}

    }
    
    public void checkQuit(String str){
        if(str.equals("quit") || str.equals("QUIT"))
		{
            System.out.println("YOU QUIT THE GAME");
			System.exit(0);
		}

    }


}