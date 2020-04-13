# The-Quest-of-Legends

## Implementation Checklists (Will be removed before submission)

- [x] Board has 3 lanes and all cell types
- [x] Randomly assign heroes and monsters to last and first rows
- [x] Board is responsible for moving across Cells and informing hero/monster being moved
- [x] Heroes and Monsters maintains current position (main logic must call board ```move()```
- [x] Hero takes into account benefit of special cells when in that cell.
- [x] Heroes move separately, once each per round
- [x] Hero can teleport to other lanes
- [x] Hero can move back to nexus
- [x] Hero can only enter market when in nexus
- [x] Check when encounter monster(s) and engage in fight
- [x] Automatically spawn monsters every 8(?) rounds
- [x] Check win/lose when hero or monster reach nexus of the opponent
- [ ] Teleport seems to be too OP. Might need some limitation.
- [x] ASCII art + colored text
- [ ] Address verbosity in gameplay

## Description

Quest is a board game operates on 8x8 board with 2-column wide 3 lane and. There are 4 special cell types: Bush, Cave, Koulou, and Nexus. The game ends when either any hero or any monster reach the nexus of the opponent. Heroes and monsters are randoomly assigned at start and at spawn (for monsters). Monsters only moves forward while heroes can move in 4 directions, teleport to other lanes, and go back to nexus. Heroes enter market only when in nexus. Spawned monsters' level is always in parity with heroes' highest level or 10 if heroes' highest level goes beyond that.

## Prerequisites

```
Java 14 or higher
```

## Tested Environment

- [x] Java 14 build 14+36-1461 on MacOS 10.15.4
- [x] Java 14 build 14+36-1461 on CSA1 machine

## Special Key

Q/q: Abruptly quit the game
E/e: Cancel the current prompt
I/i: Show helpful information
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
B/b: Move back to nexus
T/t: Teleport to other lanes

## Data Loading

The game expects all data to be in csv format with one header line at the beginning located under `db` folder that should exist somewhere under `src` folder. [toCsv.py](src/main/java/edu/bu/phuminw/quest/db/toCsv.py) converts given txt files into csv files.  The following structure shows where each csv file should be placed:

```
db/
    creature/
        hero/
            *.csv of heroes
        monster
            *.csv of monsters
    item
        *.csv of items (Armor, Spells, Weapon, etc.)
```

All entries is checked for duplication with the loaded entries (no duplicate hero, monster, and items). They are loaded once before the game starts.

## Compilation and Launching

[startgame](startgame) binary must be used to launch the game. It does necessary check for prerequisites, compile the source code (`javac`), and launch the game (`java`). Simply run the following command in the terminal:

```(bash)
./startgame
```

## Remarks

- Details of each class/file can be found in the comments and design document

- The game can be ended either when all heroes died or by entering "q"

- The additional attack of `IceSpell` is assumed to reduce all monster skills by the specified amount i.e. 0.1 will reduce 10%

- Spell's damage range was not used in this implementation, thus is hardcoded as 1.

- When some monsters encounter with some heroes, player will be asked for an adjacent hero to fight.

- Potion.txt was modified by adding "attribute" column indicating which stat will be affected.

- .tar file is included since the source code files need to be structured properly, but Gradescope flattened all files and might cause problems testing the code.

## Authors

**Yizhou Mao**
**Phumin Walaipatchara**
**Jinshu Yang**
