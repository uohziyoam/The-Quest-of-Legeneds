//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Board
import java.util.Random;
import java.util.Collections;
import java.util.*;

public class Board{
	// the file contains the class which represent the game board

	private int row;
	private int col;
	private Cell[][] grid; //the 2D array contains the cells of the board

	private double nonAccProb = 0.2;
	private double marketProb = 0.3;
	private double commonProb = 0.5;

	private int nonAcc_num;
	private int market_num;
	private int common_num;

	private Coordinate cur_pos;

	private Function function;

	public Board(int row, int col,Function function){
		// the board constructor
		this.row = row;
		this.col = col;
		this.grid = new Cell[row][col];
		this.function = function;
		this.cur_pos = new Coordinate(0, 0);

		this.nonAcc_num = (int)Math.round(this.row*this.col*this.nonAccProb);
		this.market_num = (int)Math.round(this.row * this.col * this.marketProb);
		this.common_num = (int)Math.round(this.row * this.col * this.commonProb);
		initGrid();
		display_board(); // display the board

	}

	public void initGrid() {
		//the function to init the grid
		this.grid = new Cell[this.row][this.col];
		ArrayList<String> signlist = new ArrayList<String>();

		int c = 1;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(c<=this.nonAcc_num)//init different types of cell
				{
					signlist.add("X");
				}
				else if((c>this.nonAcc_num)&&(c<=this.market_num))
				{
					signlist.add("M");
				}
				else
				{
					signlist.add(" ");

				}
				c++;
			}
		}

		Collections.shuffle(signlist);
		signlist.set(0, " ");

		int counter = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				
				grid[i][j] = initCell(new Coordinate(i, j),signlist.get(counter)); 
				counter++;// create the cell object
			}
		}

	}



	public Cell initCell(Coordinate index,String str) {
		Cell cur;


		if (str.equals("X")) {
			//obstacle
			cur = new Obstacle(index,false);
			
		} else if (str.equals("M"))
		{ //market
			cur = new Market(index,true,this.function);
		}
		else
		{ //commonCell
			cur = new Road(index,true);
		}
		return cur;
	}

	public Cell getCell(int i,int j){
		//get the cell
		return grid[i][j];

	}

	public void setCurPos(int i,int j)
	{
		this.cur_pos = new Coordinate(i, j);
	}

	public void display_board(){
		//the function to display the board

		for(int i = 0;i<this.row;i++)
		{
			display_boarder();
			for(int j = 0;j<this.col;j++)
			{
				System.out.print("| ");
				if(i == cur_pos.getX() && j == cur_pos.getY())
				{
					if(grid[i][j].toString().equals(" "))
					{
						System.out.print(ConsoleColors.RED+"0"+ConsoleColors.RESET);

					}
					else
					{
						System.out.print(ConsoleColors.RED+grid[i][j]+ConsoleColors.RESET);
					}

				}
				else
				{
					System.out.print(grid[i][j]);
				}
			
				


			}
			System.out.println("| ");

		}
		display_boarder();
		
	}



	public void display_boarder(){
		//the function to display the boarder
		for(int i = 0;i<this.col;i++)
		{
			System.out.print("+--");
		}
		System.out.println("+");
	}




}