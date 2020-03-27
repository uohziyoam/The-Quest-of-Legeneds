//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Coordinate
public class Coordinate{
	private int x;
	private int y;

	public Coordinate(int x,int y){
		//constructer
		this.x = x;
		this.y = y;
	}
	public Coordinate(Coordinate i){
		this.x = i.getX();
		this.y = i.getY();
	}



	public int getX(){
		return this.x;
	}



	public int getY(){
		return this.y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}
}