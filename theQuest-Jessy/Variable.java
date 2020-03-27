//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Variable
import java.util.ArrayList;
import java.util.HashMap;
import character.Dragons;
import character.Exoskeletons;
import character.Paladins;
import character.Sorcerers;
import character.Spirits;
import character.Warriors;
import equipment.*;

public class Variable {

    public static String UP = "W";
    public static String LEFT = "A";
    public static String DOWN = "S";
    public static String RIGHT = "D";

    public static String QUIT = "Q";

    
    public static HashMap<String, Object> EQUIP_TO_ENUM;
    public static HashMap<String, Object> MONSTER_TO_ENUM;
    public static HashMap<String, Object> HERO_TO_ENUM;
    public static HashMap<String, Action> ACTION_TO_ENUM;

    public static ArrayList<String> heroList;
    public static ArrayList<String> monsterList;    

    public Variable(){
        initEQUIP();
        initHERO();
        initMONSTER();
        initACTION();
        initHeroList();
        initMonsterList();
    }

    public void initEQUIP(){
        EQUIP_TO_ENUM = new HashMap<String, Object>();

        EQUIP_TO_ENUM.put("Arctic_storm",IceSpells.Arctic_storm);
        EQUIP_TO_ENUM.put("Frost_Blizzard",IceSpells.Frost_Blizzard);
        EQUIP_TO_ENUM.put("Ice_Blade",IceSpells.Ice_Blade);
        EQUIP_TO_ENUM.put("Snow_Canon",IceSpells.Snow_Canon);

        EQUIP_TO_ENUM.put("Electric_Arrows",LightningSpells.Electric_Arrows);
        EQUIP_TO_ENUM.put("LightningDagger",LightningSpells.LightningDagger);
        EQUIP_TO_ENUM.put("Spark_Needles",LightningSpells.Spark_Needles);
        EQUIP_TO_ENUM.put("Thunder_Blast",LightningSpells.Thunder_Blast);

        EQUIP_TO_ENUM.put("Breath_of_Fire",FireSpells.Breath_of_Fire);
        EQUIP_TO_ENUM.put("Flame_Tornado",FireSpells.Flame_Tornado);
        EQUIP_TO_ENUM.put("Heat_Wave",FireSpells.Heat_Wave);
        EQUIP_TO_ENUM.put("Lava_Commet",FireSpells.Lava_Commet);

        EQUIP_TO_ENUM.put("Ambrosia",Potion.Ambrosia);
        EQUIP_TO_ENUM.put("Healing_Potion",Potion.Healing_Potion);
        EQUIP_TO_ENUM.put("Luck_Elixir",Potion.Luck_Elixir);
        EQUIP_TO_ENUM.put("Magic_Potion",Potion.Magic_Potion);
        EQUIP_TO_ENUM.put("Mermaid_Tears",Potion.Mermaid_Tears);
        EQUIP_TO_ENUM.put("Strength_Potion",Potion.Strength_Potion);

        EQUIP_TO_ENUM.put("Breastplate",Armory.Breastplate);
        EQUIP_TO_ENUM.put("Full_Body_Armor",Armory.Full_Body_Armor);
        EQUIP_TO_ENUM.put("Platinum_Shield",Armory.Platinum_Shield);
        EQUIP_TO_ENUM.put("Speed_Boots",Armory.Speed_Boots);
        EQUIP_TO_ENUM.put("Wizard_Shield",Armory.Wizard_Shield);

        EQUIP_TO_ENUM.put("Axe",Weaponry.Axe);
        EQUIP_TO_ENUM.put("Bow",Weaponry.Bow);
        EQUIP_TO_ENUM.put("Dagger",Weaponry.Dagger);
        EQUIP_TO_ENUM.put("Scythe",Weaponry.Scythe);
        EQUIP_TO_ENUM.put("Shield",Weaponry.Shield);
        EQUIP_TO_ENUM.put("Sword",Weaponry.Sword);
        EQUIP_TO_ENUM.put("TSwords",Weaponry.TSwords);

    }

    public void initMONSTER(){
        MONSTER_TO_ENUM = new HashMap<String, Object>();

        MONSTER_TO_ENUM.put("Desghidorrah",Dragons.Desghidorrah);
        MONSTER_TO_ENUM.put("Alexstraszan",Dragons.Alexstraszan);
        MONSTER_TO_ENUM.put("BunsenBurner",Dragons.BunsenBurner);
        MONSTER_TO_ENUM.put("Chrysophylax",Dragons.Chrysophylax);
        MONSTER_TO_ENUM.put("D_Maleficent",Dragons.D_Maleficent);
        MONSTER_TO_ENUM.put("Kas_Ethelinh",Dragons.Kas_Ethelinh);
        MONSTER_TO_ENUM.put("Natsunomeryu",Dragons.Natsunomeryu);
        MONSTER_TO_ENUM.put("Phaarthurnax",Dragons.Phaarthurnax);
        MONSTER_TO_ENUM.put("TheScaleless",Dragons.TheScaleless);
        MONSTER_TO_ENUM.put("TheWeatherbe",Dragons.TheWeatherbe);

        MONSTER_TO_ENUM.put("Aasterinian",Exoskeletons.Aasterinian);
        MONSTER_TO_ENUM.put("BigBad_Wolf",Exoskeletons.BigBad_Wolf);
        MONSTER_TO_ENUM.put("Brandobaris",Exoskeletons.Brandobaris);
        MONSTER_TO_ENUM.put("Chronepsish",Exoskeletons.Chronepsish);
        MONSTER_TO_ENUM.put("Cyrrollalee",Exoskeletons.Cyrrollalee);
        MONSTER_TO_ENUM.put("Kiaransalee",Exoskeletons.Kiaransalee);
        MONSTER_TO_ENUM.put("Merrshaullk",Exoskeletons.Merrshaullk);
        MONSTER_TO_ENUM.put("WickedWitch",Exoskeletons.WickedWitch);
        MONSTER_TO_ENUM.put("St_Shargaas",Exoskeletons.St_Shargaas);
        MONSTER_TO_ENUM.put("St_Yeenoghu",Exoskeletons.St_Yeenoghu);

        MONSTER_TO_ENUM.put("Aim_Haborym",Spirits.Aim_Haborym);
        MONSTER_TO_ENUM.put("Andrealphus",Spirits.Andrealphus);
        MONSTER_TO_ENUM.put("Andromalius",Spirits.Andromalius);
        MONSTER_TO_ENUM.put("Chiang_shih",Spirits.Chiang_shih);
        MONSTER_TO_ENUM.put("Ereshkigall",Spirits.Ereshkigall);
        MONSTER_TO_ENUM.put("FallenAngel",Spirits.FallenAngel);
        MONSTER_TO_ENUM.put("Jormunngand",Spirits.Jormunngand);
        MONSTER_TO_ENUM.put("Melchiresas",Spirits.Melchiresas);
        MONSTER_TO_ENUM.put("Rakkshasass",Spirits.Rakkshasass);
        MONSTER_TO_ENUM.put("Taltecuhtli",Spirits.Taltecuhtli);
    }

    public void initHERO(){
        HERO_TO_ENUM = new HashMap<String, Object>();

        HERO_TO_ENUM.put("Flandal_Steelskin",Warriors.Flandal_Steelskin);
        HERO_TO_ENUM.put("Gaerdal_Ironhand",Warriors.Gaerdal_Ironhand);
        HERO_TO_ENUM.put("Muamman_Duathall",Warriors.Muamman_Duathall);
        HERO_TO_ENUM.put("Sehanine_Monnbow",Warriors.Sehanine_Monnbow);

        HERO_TO_ENUM.put("Garl_Glittergold",Paladins.Garl_Glittergold);
        HERO_TO_ENUM.put("Sehanine_Moonbow",Paladins.Sehanine_Moonbow);
        HERO_TO_ENUM.put("Skoraeus_Stonebones",Paladins.Skoraeus_Stonebones);
        HERO_TO_ENUM.put("Solonor_Thelandira",Paladins.Solonor_Thelandira);

        HERO_TO_ENUM.put("Garl_Glittergold",Sorcerers.Garl_Glittergold);
        HERO_TO_ENUM.put("Rillifane_Rallathil",Sorcerers.Rillifane_Rallathil);
        HERO_TO_ENUM.put("Segojan_Earthcaller",Sorcerers.Segojan_Earthcaller);
        HERO_TO_ENUM.put("Skoraeus_Stonebones",Sorcerers.Skoraeus_Stonebones);
    }

    public void initACTION(){
        ACTION_TO_ENUM = new HashMap<String, Action>();

        ACTION_TO_ENUM.put("REGULAR_ATTACK",Action.REGULAR_ATTACK);
        ACTION_TO_ENUM.put("CAST_SPELL",Action.CAST_SPELL);
        ACTION_TO_ENUM.put("CHANGE_EQUIPMENT",Action.CHANGE_EQUIPMENT);
        ACTION_TO_ENUM.put("USE_POTION",Action.USE_POTION);
    }

    public void initHeroList(){
        heroList = new ArrayList<String>();

        heroList.add("Flandal_Steelskin");
        heroList.add("Gaerdal_Ironhand");
        heroList.add("Muamman_Duathall");
        heroList.add("Sehanine_Monnbow");

        heroList.add("Garl_Glittergold");
        heroList.add("Sehanine_Moonbow");
        heroList.add("Skoraeus_Stonebones");
        heroList.add("Solonor_Thelandira");

        heroList.add("Garl_Glittergold");
        heroList.add("Rillifane_Rallathil");
        heroList.add("Segojan_Earthcaller");
        heroList.add("Skoraeus_Stonebones");
    }

    public void initMonsterList(){
        monsterList = new ArrayList<String>(MONSTER_TO_ENUM.keySet());
        // System.out.println("hihihi");
        // System.out.println(monsterList);
    }
    
    public ArrayList<String> getMonsterList(){
        return this.monsterList;
    }



    public void displayHero(){
        for(int i = 0;i<heroList.size();i++)
        {
            System.out.println(HERO_TO_ENUM.get((heroList.get(i))));
        }
    }

    public void displayList(ArrayList lst){
        for(int i = 0;i<lst.size();i++)
        {
            System.out.println(HERO_TO_ENUM.get((lst.get(i))));
        }

    }

    




}