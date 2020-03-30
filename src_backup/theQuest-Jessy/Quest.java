//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Quest
import java.util.Random;
import java.util.Scanner;

//favored 要改一下
//monster 的选取




public class Quest {
	private Board board;
	private Hero[] heros;
	private Variable var;
	private boolean ifEnd;
	private Function function;
	private double fightPoss = 0.5;

	public Quest(){
		this.ifEnd = false;
		initGame();
		this.board = new Board(8,8,this.function);
		run();

	}

	public void initGame(){
		Scanner keyboard = new Scanner(System.in);
		this.var = new Variable();
		System.out.println("Welcome to the Quest!");
		System.out.println("Please enter the number of players:(1~3)");
		int number = (int)this.checkValidDouble(1,3);
		this.function = new Function(this.board,this.heros);
		this.heros = new Hero[number];
		this.initPlayers(number);
		this.function = new Function(this.board,this.heros);

	}

	public void run()
	{
		while(!this.ifEnd)
		{
			Coordinate temp = this.askMove();
			// this.board.setCurPos(cur_hero.getPos().getX(),cur_hero.getPos().getY());
			// this.function.setMap(this.board);
			//Coordinate cur_pos = this.heros[0].getPos();
			Cell cur_cell = this.board.getCell(temp.getX(),temp.getY());
			if(cur_cell instanceof Market)
			{
				this.board.display_board();
				this.board.setCurPos(temp.getX(),temp.getY());
				this.function.setMap(this.board);
				this.board.display_board();
				for(int i = 0;i<this.heros.length;i++)
				{
					this.heros[i].setPos(temp);
				}

				Market m = (Market) cur_cell;
				m.setHeros(this.heros);
				System.out.println("It is a market. You can shop here!");
				m.run();
				this.board.display_board();
			}
			else if(cur_cell instanceof Obstacle)
			{
				Obstacle o = (Obstacle) cur_cell;
				System.out.println("It is an obstacle.You can't move.");
			}
			else if(cur_cell instanceof Road)
			{
				for(int i = 0;i<this.heros.length;i++)
				{
					this.heros[i].setPos(temp);
				}
				this.board.setCurPos(temp.getX(),temp.getY());
				this.function.setMap(this.board);
				this.board.display_board();
				Road r = (Road) cur_cell;
				if(ifFight())
				{

					Fight f = new Fight(heros.length, this.heros,this.function);
					this.board.display_board();
				}
			}
		}
	}

	public void initPlayers(int number){
		Scanner keyboard = new Scanner(System.in);

		System.out.println("PLEASE CHOOSE FROM THE FOLLOWING LIST OF HEROS：");
		this.var.displayHero();
		for(int i = 0;i<this.heros.length;i++)
		{
			System.out.println("PLEASE ENTER NAME:");
			String name = keyboard.next();
			function.checkInput(name);
			this.heros[i] = new Hero(name, new Coordinate(0,0),this.function);
			System.out.println("SUCCESSFULLY CREATED");
		}





	}

	public boolean ifFight(){
		Random r = new Random();
		double number = r.nextDouble();
		if(number<=fightPoss)
		{
			return true;
		}
		return false;

	}

	public double checkValidDouble(double min,double max)
	{
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.next();
		try {
			double Dinput = Double.valueOf(input);
			if(Dinput>=min && Dinput <=max)
			{
				return Dinput;
			}
			
		} catch (Exception e) {
			System.out.println("INVALID INPUT");
			checkValidDouble(min, max);
		}

		checkValidDouble(min, max);

		return -1;

		
	}

	public String checkValidString(String[] answers)
	{
		Scanner keyboard = new Scanner(System.in);
		String answer = "";
		boolean turn = false;
		while(turn == false)
		{
			answer = keyboard.nextLine();
			for(int i = 0;i<answers.length;i++)
			{
				if(answer.equals(answers[i]))
				{
					turn = true;
				}
			}

		}
		return answer;
	}

	// public void enterMarket(Hero[] heros, Market m){
	// 	Scanner keyboard = new Scanner(System.in);
	// 	for(int i = 0;i<heros.length;i++)
	// 	{
	// 		m.initMarket();
	// 		System.out.println("Player: "+heros[i].getName()+" Please choose to buy or sell items. When you want to quit the market, please type 'quit_market'");
	// 		String answer = checkValidString(new String[]{"buy","sell","quit_market"});
	// 		checkInput(answer);
	// 		if(answer.equals("sell"))
	// 		{
				
	// 		}
			

	// 	}
	// }

	public Coordinate askMove(){
		Coordinate temp;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("PLEASE ENTER THE DIRECTION THAT YOU WANT TO MOVE");
		String direction = keyboard.next();
		function.checkInput(direction);
		temp = this.heros[0].move(direction);
		
		while((this.heros[0].move(direction).getX() == -1)&&(this.heros[0].move(direction).getY() == -1))
		{
			System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
			direction = keyboard.next();
		}
		temp = this.heros[0].move(direction);

			// this.board.setCurPos(cur_hero.getPos().getX(),cur_hero.getPos().getY());
			// this.function.setMap(this.board);
		
		return temp;
	}

	// public void checkInput(String str){
	// 	if(str.equals("quit") || str.equals("QUIT"))
	// 	{
	// 		System.exit(0);
	// 	}
	// 	if(str.equals("MAP") || str.equals("map"))
	// 	{
	// 		this.board.display_board();
	// 	}
	// 	if(str.equals("info") || str.equals("INFO"))
	// 	{
	// 		System.out.println("HERO'S CONDITION:");
	// 		for(int i = 0;i<this.heros.length;i++)
	// 		{
	// 			this.heros[0].Inventories();
	// 		}
	// 	}

	// }

	public void displayMap(){
		this.board.display_board();
	}





}