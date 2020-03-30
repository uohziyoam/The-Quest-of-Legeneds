//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Cell
public class Cell{
	//the file contains the class which represents the cell of the board object

	private boolean ifAcc; // the boolean to present if the cell is accessible
	private Coordinate index; // the cell's coordinate

	public Cell(){
		//constructer
		this.ifAcc = false;
		this.index = null;
	}

	public Cell(Coordinate i){
        this.index = i;
	}

    public Cell(Coordinate i,boolean ifAcc){
        this.index = i;
        this.ifAcc = ifAcc;
    }

	public void setAcc(boolean ifAcc){
		//the function to set the cell's ifAcc
		this.ifAcc = ifAcc;
	}

	public boolean getAcc(){
		//the function to get the cell's ifAcc
		return this.ifAcc;
	}

	public Coordinate getIndex(){
		//the function to get the position of the cell
		return this.index;
	}

	public void setIndex(Coordinate i){
		//the function to get the index of the cell
		this.index = i;
	}
}