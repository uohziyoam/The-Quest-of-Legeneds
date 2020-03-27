# Object Oriented Design (2): Quest Game

## OOP Structure

The high level design of the whole project is based on the following components:

- **Main.java** - A main enterance for the game.
- **FightRound.java** - A class implements the logic of fight between monsters and warriors.
- **Game.java** - A game class that includes all the main logic of the game.

- **Equipment**

  - **Armors.java** - An enum construct all the details about the equipment.
  - **Weapons.java** - An enum construct all the details about the equipment.
  - **Potions.java** - An enum construct all the details about the equipment.
  - **Fire_Spells.java** - An enum construct all the details about the equipment.
  - **Ice_Spells.java** - An enum construct all the details about the equipment.
  - **Lighting_Spells.java** - An enum construct all the details about the equipment.

- **Config**

  - **Color.java** - A static class incluedes all color variables.
  - **DamageType.java** - An enum includes the damage type (e.g. magic and physical).
  - **Variables.java** - A static class initializes and stores all required data.

* **Board**

  - **Board.java** - A class implements the generation logic of board.
  - **Cell.java** - A single cell of a board.
  - **Coordinnate.java** - A class that is used to record the coordinate of a cell.
  - **Market.java** - A class implements the structure of the Market.
  - **Obstacle.java** - A class implements the structure of the Obstacle.
  - **Road.java** - A class implements the structure of the Road.
  - **Square.java** - A superclass implements the structure of the Square.

* **Avatar**

  - **HeroAction.java** - An interface includes all the required actions performed by heros.
  - **MonsterAction.java** - An interface includes all the required actions performed by monsters.
  - **Dragons.java** - An enum construct all the details about the avatar.
  - **Exoskeletons.java** - An enum construct all the details about the avatar.
  - **Paladins.java** - An enum construct all the details about the avatar.
  - **Sorcerers.java** - An enum construct all the details about the avatar.
  - **Spirits.java** - An enum construct all the details about the avatar.
  - **Warriors.java** - An enum construct all the details about the avatar.
  - **Avatar.java** - A superclass that provides the details about an avatar in the game.
  - **Hero.java** - A class that constructs a Hero Object.
  - **Monster.java** - A class that constructs a Monster Object.

```
.
|____board
| |____square
| | |____Road.java
| | |____Obstacle.java
| | |____Market.java
| | |____Coordinate.java
| | |____Square.java
| |____Board.java
| |____Cell.java
|____config
| |____VARIABLES.java
| |____DamageType.java
| |____Color.java
|____game
| |____FightRound.java
| |____ActionType.java
| |____Game.java
|____readme.md
|____Main.java
|____avatar
| |____avatarName
| | |____Exoskeletons.java
| | |____Dragons.java
| | |____Sorcerers.java
| | |____Paladins.java
| | |____Spirits.java
| | |____Warriors.java
| |____Hero.java
| |____Avatar.java
| |____Monster.java
| |____avatarInterface
| | |____HeroAction.java
| | |____MonsterAction.java
| |____AvatarType.java
|____equipment
| |____Potions.java
| |____Armors.java
| |____Ice_Spells.java
| |____Lighting_Spells.java
| |____Weapons.java
| |____Fire_Spells.java
```

## Installing

To compile the program:

```
javac Main.java
```

To run the program:

```
java Main
```

## Getting Started

```
HOW MANY HEROS DO YOU NEED? (1 ~ 3):
1
```

#### - Sample of 1 Hero Player

```
PICK YOUR No. 0 HEROS! (e.g. Flandal_Steelskin):
Flandal_Steelskin
```

```
Garl_Glittergold:
LEVEL: 1.0
HP: 100.0
MANA: 100.0
STRENGTH: 600.0
AGILITY: 500.0
DEXTERITY: 400.0
MONEY: 2500.0
EXPERIENCE: 5.0
ARMOR: null
WEAPON: null
BAGS: {}
DEAD: NO
```

```
ENTER YOUR NEXT MOVEMENT:
1. MOVE UP (W/w)
2. MOVE DOWN (S/s)
3. MOVE LEFT (A/a)
4. MOVE RIGHT (D/d)
5. INFORMATION (I/i)
6. MAP (M/m)
7. QUIT (Q/q)
```

```
Hero: Garl_Glittergold (ENTER FULL STRING e.g. USEPOTION)
0. USEPOTION
1. REGULARATTACK
2. CHANGEARMOR
3. CHANGEWEAPON
4. CASTSPELL
5. DISPLAYSTATS
6. QUIT
```

```
WHICH HERO NEED TO ENTER MARKET? (ENTER INDEX! OR Q/I):
0. Garl_Glittergold
1. QUIT (Q/q)
2. INFORMATION (I/i)
```

Each cell has a corresponding Letter, **M** stands for Market, **%** stands for Obstacle, **" "** stands for Road, **H** stands for Hero.

```
+-----+-----+-----+-----+-----+-----+-----+-----+
|  M  |  M  |  %  |  M  |     |  M  |     |  M  |
+-----+-----+-----+-----+-----+-----+-----+-----+
|     |     |     |  %  |     |  M  |  %  |     |
+-----+-----+-----+-----+-----+-----+-----+-----+
|  %  |  M  |  M  |     |     |     |     |     |
+-----+-----+-----+-----+-----+-----+-----+-----+
|  %  |     |  M  |  M  |  M  |     |  H  |     |
+-----+-----+-----+-----+-----+-----+-----+-----+
|     |  %  |  M  |     |     |  M  |     |     |
+-----+-----+-----+-----+-----+-----+-----+-----+
|  M  |  M  |     |     |  %  |  %  |  M  |  %  |
+-----+-----+-----+-----+-----+-----+-----+-----+
|     |     |     |     |     |  %  |  M  |     |
+-----+-----+-----+-----+-----+-----+-----+-----+
|     |     |  %  |  %  |     |  M  |     |  %  |
+-----+-----+-----+-----+-----+-----+-----+-----+
```

### Prerequisites

```
Java SE 8
```

## Authors

**Yizhou Mao** - _Initial work_ - [BECK'S BLOG](https://www.maoyizhou.com)
