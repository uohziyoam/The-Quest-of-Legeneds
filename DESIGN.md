# Design Document

## File Structure

```
src/main/java/edu/bu/phuminw/quest/
    board/
        Board.java
        Cell.java
        Mark.java
    db/
        creature/
            hero/
                *.csv of heroes
            monster/
                *.csv of monsters
        item/
            *.csv of items (Armor, Spells, Weapon, etc.)
        toCsv.py
    hero/
        Hero.java
        HSkills.java
        Paladin.java
        Sorcerer.java
        Warrior.java
    io/
        StdinWrapper.java
    market/
        Armor.java
        FireSpell.java
        IceSpell.java
        LightningSpell.java
        Market.java
        Potion.java
        Weapon.java
    monster/
        Dragon.java
        Exoskeleton.java
        Monster.java
        Spirit.java
    player/
        Player.java
    util/
        Color.java
        Creature.java
        Damagable.java
        Damage.java
        HeroSorter.java
        Item.java
        ItemComparator.java
        Positionable.java
        Spell.java
        Team.java
        Tuple.java
    Quest.java
DESIGN.md
README.md
startgame
```

## File/Class Description

### Flags

**J** = Jinshu Yang

**P** = Phumin Walaipatchara 

**Y** = Yizhou Mao

**+** = Modification/Creation was done. Before **+** are authors that their implementations were used. After **+** are authors that do modification

**num** = Level of modification/creation: 1 is few lines or few methods, 2 is about half changed, 3 is almost or completely changed

- (P+PY1) Board.java - A class that encapsulates cells in a rectangle grid; (For this class, instead of continuing using the String as a marker in the pervious assignment, we choose to create a Mark class to make it more flexible to deal with more modifications.)
- (P+JP1) Cell.java - A class that encapsulates some object known as occupier
- (P+P1) Mark.java - A class represents a mark String
- (P+PY1) Hero.java - A creature and a template for hero object defining essential attributes that a hero subclass object must have
- (P+P1) HSkills.java - A skill set of a hero
- (P) Paladin.java - Paladin hero implementing Hero superclass
- (P) Sorcerer.java - Sorcerer hero implementing Hero superclass
- (P) Warrior.java - Warrior hero implementing Hero superclass
- (P+P1) StdinWrapper.java - Wrapper class for stdin to take in an input or special keys
- (P) Armor.java - A class representing Armor to be worn by a hero to defense an attack from the monster
- (P) FireSpell.java - A class for FireSpell (one of spells hero can equip)
- (P) IceSpell.java - A class for IceSpell (one of spells hero can equip)
- (P) LightningSpell.java - A class for LightningSpell (one of spells hero can equip)
- (P+P1) Market.java - Market class that takes care of shopping on either a hero or a player
- (P) Potion.java - A class for Potion that a hero can take drink and increase some attributes (intended for one-time use)
- (P) Weapon.java - A class for Weapon that a hero can equip
- (P) Dragon.java - Dragon monster which has more damage
- (P) Exoskeleton.java - Exoskeleton monster which has more defense
- (P+P1) Monster.java - A creature and a superclass of all monsters defining essential attributes
- (P) Spirit.java - Spirit monster which has more dodge chance
- (P+1P) Player.java - A player containing heroes
- (Y) Color.java - Definition of color for colorful playing experience
- (+P3) Creature.java - Template for essential attributes of Creature
- (P) Damagable.java - Specification for an object that can cause damage
- (P) Damage.java - An object representing damage that an object can cause
- (+P3) HeroSorter.java - Sorter according to hero's mark
- (P) Item.java - A template for item object defining essential attributes
- (P) ItemComparator.java - Compare items used for sorting
- (+P3) Positionable.java - Specification for an object that has position
- (P) Spell.java - A template for subclass spell defining essential attributes
- (P+P1) Team.java - A collection of objects that belong together
- (P+P1) Tuple.java - A packed data structure of two values
- (P+JPY2) Quest.java - Main logic of the game



## Authors

### Group 2

**Yizhou Mao**

**Phumin Walaipatchara**

**Jinshu Yang**
