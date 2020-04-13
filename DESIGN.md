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

- Board.java - A class that encapsulates cells in a rectangle grid 
- Cell.java - A class that encapsulates some object known as occupier
- Mark.java - A class represents a mark String
- Hero.java - A creature and a template for hero object defining essential attributes that a hero subclass object must have
- HSkills.java - A skill set of a hero
- Paladin.java - Paladin hero implementing Hero superclass
- Sorcerer.java - Sorcerer hero implementing Hero superclass
- Warrior.java - Warrior hero implementing Hero superclass
- StdinWrapper.java - Wrapper class for stdin to take in an input or special keys
- Armor.java - A class representing Armor to be worn by a hero to defense an attack from the monster
- FireSpell.java - A class for FireSpell (one of spells hero can equip)
- IceSpell.java - A class for IceSpell (one of spells hero can equip)
- LightningSpell.java - A class for LightningSpell (one of spells hero can equip)
- Market.java - Market class that takes care of shopping on either a hero or a player
- Potion.java - A class for Potion that a hero can take drink and increase some attributes (intended for one-time use)
- Weapon.java - A class for Weapon that a hero can equip
- Dragon.java - Dragon monster which has more damage
- Exoskeleton.java - Exoskeleton monster which has more defense
- Monster.java - A creature and a superclass of all monsters defining essential attributes
- Spirit.java - Spirit monster which has more dodge chance
- Player.java - A player containing heroes
- Color.java - Definition of color for colorful playing experience
- Creature.java - Template for essential attributes of Creature
- Damagable.java - Specification for an object that can cause damage
- Damage.java - An object representing damage that an object can cause
- HeroSorter.java - Sorter according to hero's mark
- Item.java - A template for item object defining essential attributes
- ItemComparator.java - Compare items used for sorting
- Positionable.java - Specification for an object that has position
- Spell.java - A template for subclass spell defining essential attributes
- Team.java - A collection of objects that belong together
- Tuple.java - A packed data structure of two values
- Quest.java - Main logic of the game
