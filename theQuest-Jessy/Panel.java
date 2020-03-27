//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Panel
import java.util.ArrayList;
import equipment.*;
public class Panel{

    private ArrayList<IceSpells> iceSpellList;
    private ArrayList<LightningSpells> lightningSpellList;
    private ArrayList<FireSpells> fireSpellList;
    private ArrayList<Potion> potionList;
    private ArrayList<Armory> armoryList;
    private ArrayList<Weaponry> weaponryList;


    public Panel(){
        this.iceSpellList = new ArrayList<IceSpells>();
        this.lightningSpellList = new ArrayList<LightningSpells>();
        this.fireSpellList = new ArrayList<FireSpells> ();
        this.potionList = new ArrayList<Potion>();
        this.armoryList = new ArrayList<Armory>();
        this.weaponryList = new ArrayList<Weaponry>();
    }

    public void addItem(Object obj)
    {
        if(obj instanceof IceSpells)
        {
            this.iceSpellList.add((IceSpells)obj);

        }
        else if(obj instanceof LightningSpells)
        {
            this.lightningSpellList.add((LightningSpells)obj);

        }
        else if(obj instanceof FireSpells)
        {
            this.fireSpellList.add((FireSpells)obj);

        }
        else if(obj instanceof Potion)
        {
            this.potionList.add((Potion)obj);

        }
        else if(obj instanceof Armory)
        {
            this.armoryList.add((Armory)obj);

        }
        else if(obj instanceof Weaponry)
        {
            this.weaponryList.add((Weaponry)obj);
        }
    }

    public boolean checkArmory(Armory a)
    {
        return this.armoryList.contains(a);
    }
    public boolean checkWeaponry(Weaponry w)
    {
        return this.weaponryList.contains(w);
    }

    public void removeItem(Object obj){
        if(obj instanceof IceSpells)
        {
            this.iceSpellList.remove((IceSpells)obj);

        }
        else if(obj instanceof LightningSpells)
        {
            this.lightningSpellList.remove((LightningSpells)obj);

        }
        else if(obj instanceof FireSpells)
        {
            this.fireSpellList.remove((FireSpells)obj);

        }
        else if(obj instanceof Potion)
        {
            this.potionList.remove((Potion)obj);

        }
        else if(obj instanceof Armory)
        {
            this.armoryList.remove((Armory)obj);

        }
        else if(obj instanceof Weaponry)
        {
            this.weaponryList.remove((Weaponry)obj);
        }

    }

    public void fillPanel(){
        this.iceSpellList.add(IceSpells.Arctic_storm);
        this.iceSpellList.add(IceSpells.Frost_Blizzard);
        this.iceSpellList.add(IceSpells.Ice_Blade);
        this.iceSpellList.add(IceSpells.Snow_Canon);

        this.lightningSpellList.add(LightningSpells.Electric_Arrows);
        this.lightningSpellList.add(LightningSpells.LightningDagger);
        this.lightningSpellList.add(LightningSpells.Spark_Needles);
        this.lightningSpellList.add(LightningSpells.Thunder_Blast);

        this.fireSpellList.add(FireSpells.Breath_of_Fire);
        this.fireSpellList.add(FireSpells.Flame_Tornado);
        this.fireSpellList.add(FireSpells.Heat_Wave);
        this.fireSpellList.add(FireSpells.Lava_Commet);

        this.potionList.add(Potion.Ambrosia);
        this.potionList.add(Potion.Healing_Potion);
        this.potionList.add(Potion.Luck_Elixir);
        this.potionList.add(Potion.Magic_Potion);
        this.potionList.add(Potion.Mermaid_Tears);
        this.potionList.add(Potion.Strength_Potion);

        this.armoryList.add(Armory.Breastplate);
        this.armoryList.add(Armory.Full_Body_Armor);
        this.armoryList.add(Armory.Platinum_Shield);
        this.armoryList.add(Armory.Speed_Boots);
        this.armoryList.add(Armory.Wizard_Shield);

        this.weaponryList.add(Weaponry.Axe);
        this.weaponryList.add(Weaponry.Bow);
        this.weaponryList.add(Weaponry.Dagger);
        this.weaponryList.add(Weaponry.Scythe);
        this.weaponryList.add(Weaponry.Shield);
        this.weaponryList.add(Weaponry.Sword);
        this.weaponryList.add(Weaponry.TSwords);

    }

    public void singleDirection(String str){
        if(str.equals("ICE_SPELL"))
        {
            System.out.println("ICE_SPELL:");
            if(this.iceSpellList.size()==0)
            {
                System.out.println("Empty");
            }
            for(int i = 0;i<this.iceSpellList.size();i++)
            {
                System.out.println(this.iceSpellList.get(i).printDetails());
            }
            System.out.println();

        }
        else if(str.equals("LIGHTNING_SPELL"))
        {
            if(this.lightningSpellList.size()==0)
            {
                System.out.println("Empty");
            }
            for(int i = 0;i<this.lightningSpellList.size();i++)
            {
                System.out.println(this.lightningSpellList.get(i).printDetails());
            }
            System.out.println();

        }
        else if(str.equals("FIRE_SPELL"))
        {
            System.out.println("FIRE_SPELL:");
            if(this.fireSpellList.size()==0)
            {
                System.out.println("Empty");
            }
            
            for(int i = 0;i<this.fireSpellList.size();i++)
            {
                System.out.println(this.fireSpellList.get(i).printDetails());
            }
            System.out.println();

        }
        else if(str.equals("POTION"))
        {
            System.out.println("POTION:");
            if(this.potionList.size()==0)
            {
                System.out.println("Empty");
            }
            
            for(int i = 0;i<this.potionList.size();i++)
            {
                System.out.println(this.potionList.get(i).printDetails());
            }
            System.out.println();
        }
        else if(str.equals("ARMORY"))
        {
            System.out.println("ARMORY:");
            if(this.armoryList.size()==0)
            {
                System.out.println("Empty");
            }
            
            for(int i = 0;i<this.armoryList.size();i++)
            {
                System.out.println(this.armoryList.get(i).printDetails());
            }
            System.out.println();

        }
        else if(str.equals("WEAPONRY"))
        {
        
            System.out.println("WEAPONRY:");
            if(this.weaponryList.size()==0)
            {
                System.out.println("Empty");
            }
            for(int i = 0;i<this.weaponryList.size();i++)
            {
                System.out.println(this.weaponryList.get(i).printDetails());
            }
            System.out.println();

        }

    }

    public void printByDirection(){
        System.out.println(ConsoleColors.GREEN+"ICE_SPELL:"+ConsoleColors.RESET);
        for(int i = 0;i<this.iceSpellList.size();i++)
        {
            System.out.println(this.iceSpellList.get(i));
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"LIGHTNING_SPELL:"+ConsoleColors.RESET);
        for(int i = 0;i<this.lightningSpellList.size();i++)
        {
            System.out.println(this.lightningSpellList.get(i));
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"FIRE_SPELL:"+ConsoleColors.RESET);
        for(int i = 0;i<this.fireSpellList.size();i++)
        {
            System.out.println(this.fireSpellList.get(i));
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"POTION:"+ConsoleColors.RESET);
        for(int i = 0;i<this.potionList.size();i++)
        {
            System.out.println(this.potionList.get(i));
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"ARMORY:"+ConsoleColors.RESET);
        for(int i = 0;i<this.armoryList.size();i++)
        {
            System.out.println(this.armoryList.get(i));
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"WEAPONRY:"+ConsoleColors.RESET);
        for(int i = 0;i<this.weaponryList.size();i++)
        {
            System.out.println(this.weaponryList.get(i));
        }
        System.out.println();

    }

    public void printByDirectionMarket(){
        System.out.println(ConsoleColors.GREEN+"ICE_SPELL:"+ConsoleColors.RESET);
        for(int i = 0;i<this.iceSpellList.size();i++)
        {
            this.iceSpellList.get(i).printItem();
            System.out.println();
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"LIGHTNING_SPELL:"+ConsoleColors.RESET);
        for(int i = 0;i<this.lightningSpellList.size();i++)
        {
            this.lightningSpellList.get(i).printItem();
            System.out.println();
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"FIRE_SPELL:"+ConsoleColors.RESET);
        for(int i = 0;i<this.fireSpellList.size();i++)
        {
            this.fireSpellList.get(i).printItem();
            System.out.println();
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"POTION:"+ConsoleColors.RESET);
        for(int i = 0;i<this.potionList.size();i++)
        {
            this.potionList.get(i).printItem();
            System.out.println();
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"ARMORY:"+ConsoleColors.RESET);
        for(int i = 0;i<this.armoryList.size();i++)
        {
            this.armoryList.get(i).printItem();
            System.out.println();
        }
        System.out.println();

        System.out.println(ConsoleColors.GREEN+"WEAPONRY:"+ConsoleColors.RESET);
        for(int i = 0;i<this.weaponryList.size();i++)
        {
            this.weaponryList.get(i).printItem();
            System.out.println();
        }
        System.out.println();

    }

}