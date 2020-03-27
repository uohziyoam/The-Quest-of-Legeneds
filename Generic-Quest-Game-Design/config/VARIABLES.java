package config;

import java.util.HashMap;
import java.util.HashSet;

import avatar.avatarName.*;
import equipment.*;

/**
 * ItemToEnum
 */
public class VARIABLES {

    public static String UP = "W";
    public static String LEFT = "A";
    public static String DOWN = "S";
    public static String RIGHT = "D";
    public static String QUIT = "Q";
    public static String INFORMATION = "I";

    public static HashSet<String> ITEMS_CATEGORY;

    public static HashMap<String, Object> ITEMS_TO_ENUM;
    public static HashMap<String, Object> HEROS_TO_ENUM;
    public static HashMap<String, Object> MONSTERS_TO_ENUM;

    static {
        ITEMS_CATEGORY = new HashSet<>();

        ITEMS_CATEGORY.add("WEAPONS");
        ITEMS_CATEGORY.add("SPELLS");
        ITEMS_CATEGORY.add("ARMORS");
        ITEMS_CATEGORY.add("POTIONS");
        ITEMS_CATEGORY.add("Q");
    }

    static {
        ITEMS_TO_ENUM = new HashMap<>();
        ITEMS_TO_ENUM.put("Q", null);

        ITEMS_TO_ENUM.put("Platinumshield", Armors.PlatinumShield);
        ITEMS_TO_ENUM.put("Breastplate", Armors.Breastplate);
        ITEMS_TO_ENUM.put("Fullbodyarmor", Armors.FullBodyArmor);
        ITEMS_TO_ENUM.put("Wizardshield", Armors.WizardShield);
        ITEMS_TO_ENUM.put("Speedboots", Armors.SpeedBoots);

        ITEMS_TO_ENUM.put("Healingpotion", Potions.HealingPotion);
        ITEMS_TO_ENUM.put("Strengthpotion", Potions.StrengthPotion);
        ITEMS_TO_ENUM.put("Magicpotion", Potions.MagicPotion);
        ITEMS_TO_ENUM.put("Luckelixir", Potions.LuckElixir);
        ITEMS_TO_ENUM.put("Mermaidtears", Potions.MermaidTears);
        ITEMS_TO_ENUM.put("Ambrosia", Potions.Ambrosia);

        ITEMS_TO_ENUM.put("Sword", Weapons.Sword);
        ITEMS_TO_ENUM.put("Bow", Weapons.Bow);
        ITEMS_TO_ENUM.put("Scythe", Weapons.Scythe);
        ITEMS_TO_ENUM.put("Axe", Weapons.Axe);
        ITEMS_TO_ENUM.put("Shield", Weapons.Shield);
        ITEMS_TO_ENUM.put("Tswords", Weapons.TSwords);
        ITEMS_TO_ENUM.put("Dagger", Weapons.Dagger);

        ITEMS_TO_ENUM.put("Flametornado", Fire_Spells.FlameTornado);
        ITEMS_TO_ENUM.put("Lavacommet", Fire_Spells.LavaCommet);
        ITEMS_TO_ENUM.put("Breathoffire", Fire_Spells.BreathofFire);
        ITEMS_TO_ENUM.put("Heatwave", Fire_Spells.HeatWave);

        ITEMS_TO_ENUM.put("Snowcanon", Ice_Spells.SnowCanon);
        ITEMS_TO_ENUM.put("Frostblizzard", Ice_Spells.FrostBlizzard);
        ITEMS_TO_ENUM.put("Arcticstorm", Ice_Spells.Arcticstorm);
        ITEMS_TO_ENUM.put("Iceblade", Ice_Spells.IceBlade);

        ITEMS_TO_ENUM.put("Thunderblast", Lighting_Spells.ThunderBlast);
        ITEMS_TO_ENUM.put("Electricarrows", Lighting_Spells.ElectricArrows);
        ITEMS_TO_ENUM.put("Sparkneedles", Lighting_Spells.SparkNeedles);
        ITEMS_TO_ENUM.put("Lightningdagger", Lighting_Spells.LightningDagger);
    }

    static {
        HEROS_TO_ENUM = new HashMap<>();
        HEROS_TO_ENUM.put("Q", null);

        HEROS_TO_ENUM.put("Gaerdal_Ironhand", Warriors.Gaerdal_Ironhand);
        HEROS_TO_ENUM.put("Sehanine_Monnbow", Warriors.Sehanine_Monnbow);
        HEROS_TO_ENUM.put("Muamman_Duathall", Warriors.Muamman_Duathall);
        HEROS_TO_ENUM.put("Flandal_Steelskin", Warriors.Flandal_Steelskin);

        HEROS_TO_ENUM.put("Garl_Glittergold", Sorcerers.Garl_Glittergold);
        HEROS_TO_ENUM.put("Rillifane_Rallathil", Sorcerers.Rillifane_Rallathil);
        HEROS_TO_ENUM.put("Segojan_Earthcaller", Sorcerers.Segojan_Earthcaller);
        HEROS_TO_ENUM.put("Skoraeus_Stonebones", Sorcerers.Skoraeus_Stonebones);

        HEROS_TO_ENUM.put("Solonor_Thelandira", Paladins.Solonor_Thelandira);
        HEROS_TO_ENUM.put("Sehanine_Moonbow", Paladins.Sehanine_Moonbow);
        HEROS_TO_ENUM.put("Skoraeus_Stonebones", Paladins.Skoraeus_Stonebones);
        HEROS_TO_ENUM.put("Garl_Glittergold", Paladins.Garl_Glittergold);
    }

    static {
        MONSTERS_TO_ENUM = new HashMap<>();

        MONSTERS_TO_ENUM.put("Desghidorrah", Dragons.Desghidorrah);
        MONSTERS_TO_ENUM.put("Chrysophylax", Dragons.Chrysophylax);
        MONSTERS_TO_ENUM.put("BunsenBurner", Dragons.BunsenBurner);
        MONSTERS_TO_ENUM.put("Natsunomeryu", Dragons.Natsunomeryu);
        MONSTERS_TO_ENUM.put("TheScaleless", Dragons.TheScaleless);
        MONSTERS_TO_ENUM.put("KasEthelinh", Dragons.KasEthelinh);
        MONSTERS_TO_ENUM.put("Alexstraszan", Dragons.Alexstraszan);
        MONSTERS_TO_ENUM.put("Phaarthurnax", Dragons.Phaarthurnax);
        MONSTERS_TO_ENUM.put("DMaleficent", Dragons.DMaleficent);
        MONSTERS_TO_ENUM.put("TheWeatherbe", Dragons.TheWeatherbe);

        MONSTERS_TO_ENUM.put("Cyrrollalee", Exoskeletons.Cyrrollalee);
        MONSTERS_TO_ENUM.put("Brandobaris", Exoskeletons.Brandobaris);
        MONSTERS_TO_ENUM.put("BigBadWolf", Exoskeletons.BigBadWolf);
        MONSTERS_TO_ENUM.put("WickedWitch", Exoskeletons.WickedWitch);
        MONSTERS_TO_ENUM.put("Aasterinian", Exoskeletons.Aasterinian);
        MONSTERS_TO_ENUM.put("Chronepsish", Exoskeletons.Chronepsish);
        MONSTERS_TO_ENUM.put("Kiaransalee", Exoskeletons.Kiaransalee);
        MONSTERS_TO_ENUM.put("StShargaas", Exoskeletons.StShargaas);
        MONSTERS_TO_ENUM.put("Merrshaullk", Exoskeletons.Merrshaullk);
        MONSTERS_TO_ENUM.put("StYeenoghu", Exoskeletons.StYeenoghu);

        MONSTERS_TO_ENUM.put("Andrealphus", Spirits.Andrealphus);
        MONSTERS_TO_ENUM.put("AimHaborym", Spirits.AimHaborym);
        MONSTERS_TO_ENUM.put("Andromalius", Spirits.Andromalius);
        MONSTERS_TO_ENUM.put("Chiangshih", Spirits.Chiangshih);
        MONSTERS_TO_ENUM.put("FallenAngel", Spirits.FallenAngel);
        MONSTERS_TO_ENUM.put("Ereshkigall", Spirits.Ereshkigall);
        MONSTERS_TO_ENUM.put("Melchiresas", Spirits.Melchiresas);
        MONSTERS_TO_ENUM.put("Jormunngand", Spirits.Jormunngand);
        MONSTERS_TO_ENUM.put("Rakkshasass", Spirits.Rakkshasass);
        MONSTERS_TO_ENUM.put("Taltecuhtli", Spirits.Taltecuhtli);

    }
}