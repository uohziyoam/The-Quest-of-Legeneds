//Created by Jinshu Yang
//March 23, 2020

This is the README file of the Quest game. It contains some notes and rules of the game

1. Introduction of File:

	Action.java---The file contains the structure of Action class.
	AttributeType.java---The file contains the structure of AttributeType class.
	Board.java---The file contains the structure of Board class which represents the grid of the game.
	Cell.java---The file contains the structure of Cell class which defines the elements in the grid.
	Character.java---The file contains the structure of Character class which consists of Hero and Monster.
	CharacterType.java---The file contains the structure of CharacterType class.
	ConsoleColors.java---The file contains the structure of ConsoleColors class enabling the color print.
	Coordinate.java---The file contains the structure of Coordinate class
	Fight.java---The file contains the structure and logic of Fight class presenting the process of fight.
	Function.java---The file contains the structure of Function. Function contains all the functions related to functionality of the game.
	Hero.java---The file contains the structure of Hero which is a subclass of character.
	itemList.java---The file contains the structure of itemList.
	Main.java---The Main file to run the game.
	Market.java---The file contains the structure and logic of Market to present the process of market.
	Monster.java---The file contains the structure of Monster which is a subclass of character.
	Obstacle.java---The file contains the structure of Obstacle which is a subclass of Cell.
	Panel.java---The file contains the structure of Panel which is to display items with info.
	Quest.java---The file contains the structure and logic of the Quest game.
	Road.java---The file contains the structure of Road which is a subclass of Cell.
	Variable.java---The file contains the structure of Variable which contains all the lists for the purpose of reference.
	equipment FOLDER:
		Armory.java---The file contains the structure of Armory class.
		Weaponry.java---The file contains the structure of Weaponry class.
		FireSpells.java---The file contains the structure of FireSpells class.
		LightningSpells.java---The file contains the structure of LightningSpells class.
		IceSpells.java---The file contains the structure of IceSpells class.
		Potion.java---The file contains the structure of Potion class.
	character FOLDER:
		Dragons.java---The file contains the structure of Dragons class.
		Exoskeletons.java---The file contains the structure of Exskeletons class.
		Paladins.java---The file contains the structure of Paladins class.
		Sorcerers.java---The file contains the structure of Sorcerers class.
		Spirits.java---The file contains the structure of Spirits class.
		Warriors.java---The file contains the structure of Warriors class.


2. How to run the game:
	Firstly, cd to the directory or open the folder in editor,
	If you are using Visual Studio, you can simple click "Run" in the Main.java file.
	If you are using terminal, you need to "javac XXX.file" to compile all the files;
	Then you need to type "java Main" to run the game.

3. Rules of the Game
	The following are some customized rules:

		1. Heros will always start at the left upper corner of the grid. Coordinate(0,0)
		2. You can always enter "QUIT" to leave the game.
		3. Once you enter the market, for each hero, unless they enter "Q" to leave the market, they will always be in market. (You can enter Q to leave market. Be careful not "quit". "quit" is to leave the game.)
		4. How to move: W = up, A = left, S = down, D = right;
		5. Enter "info" for displaying the information of heros; Enter "map" to display the grid.
		6. Heros that got fainted will be awaked after fight. They will come back with half of the HP.
		7. You can "go through" the market.



	First, you will need to enter the number of heros that you want( min 1, max 3);
	With the list of heros displayed, you need to enter the Hero's name to create the Hero
	(Make sure you enter the name as exactly as displayer, it is case sensitive)


	The Grid:
		The following is a sample grid.
		+--+--+--+--+--+--+--+--+
		| 0|  |  |  |  | X| X|  | 
		+--+--+--+--+--+--+--+--+
		|  |  |  |  |  |  |  |  | 
		+--+--+--+--+--+--+--+--+
		|  |  |  |  |  |  |  |  | 
		+--+--+--+--+--+--+--+--+
		|  |  | X|  | X|  |  | M| 
		+--+--+--+--+--+--+--+--+
		| X| M|  |  | M| M| M|  | 
		+--+--+--+--+--+--+--+--+
		| X| X| X|  |  |  | X| X| 
		+--+--+--+--+--+--+--+--+
		|  |  | X| M|  |  |  | X| 
		+--+--+--+--+--+--+--+--+
		|  |  |  |  | X|  |  |  | 
		+--+--+--+--+--+--+--+--+
		Heros will always start at the left upper corner of the grid. Coordinate(0,0)
		0 presents the current location of heros.
		X presents obstacles.
		M presents Market.
		empty box means Road which is possible for encountering a fight.

	The Fight:
		When heros encounter a fight, the same number of monsters will appear.
		Monsters will be choosed randomly.
		Heros will always start first.
		Heros have 4 choices of action: REGULAR_ATTACK, CAST_SPELL，USE_POTION, CHANGE_EQUIPMENT.
		Please make sure that you enter exactly as the option above to correctly trigger the action!
	







		



