//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Fight
import java.util.Random;
import java.util.Scanner;
import equipment.*;



public class Fight{
    private Monster[] monsters;
    private Hero[] heros;
    private Action cur_Action;
    private CharacterType cur_turn;
    private Variable var;
    private Function function;
    
    Fight(int number,Hero[] heros,Function function){
        //constructer
        this.heros = heros;
        this.cur_turn = CharacterType.HERO;
        this.function = function;
        this.monsters = new Monster[number];

        this.initFight(number);
        this.initMonster(number);
        this.run();


    }

    public void initFight(int number){
        //the function to init the fight
        this.var = new Variable();
        cur_turn = CharacterType.HERO;
    }

    public void initMonster(int number){
        //the function to init the monsters
        Random r = new Random();
        for(int i = 0;i<number;i++)
        {
            this.monsters[i] = new Monster(this.var.getMonsterList().get(r.nextInt(this.var.monsterList.size())),getHighestLevel());
        }
    
    }

    public double getHighestLevel()
    {
        //the function to get the highest level of heros
        double level = this.heros[0].getlevel();
        for(int i =0;i<this.heros.length;i++)
        {
            if(this.heros[i].getlevel()>level)
            {
                level = this.heros[i].getlevel();
            }

        }

        return level;
    }

    public void run(){
        //the function to run the fight
        displayOpening();
        this.displayInfo();
        while(!ifEnd())
        {
            if(cur_turn == CharacterType.HERO)
            {
                //Hero's turn
                

                for(int i = 0;i<heros.length;i++)
                {
                    Hero cur_hero;
                    Monster cur_monster;

                    cur_hero = this.heros[i];

                    if(!cur_hero.getDead())
                    {
                        System.out.println(ConsoleColors.RED+"It's HERO: "+cur_hero.getName()+" 's turn! '"+ConsoleColors.RESET);
                        Action action = this.getAction(cur_hero);


                        if(ifFullMonster())
                        {
                            cur_monster = monsters[i];
                        }
                        else
                        {
                            cur_monster = this.getNextMonster();
                        }
                        
                        
                        if(action.equals(Action.CHANGE_EQUIPMENT))
                        {
                            this.changeEquipment(cur_hero);
                            System.out.println(ConsoleColors.RED+cur_hero.getName()+" changed equipment "+ConsoleColors.RESET);

                        }
                        else if(action.equals(Action.REGULAR_ATTACK))
                        {
                            this.regularAttack(cur_hero, cur_monster);
                            

                        }
                        else if(action.equals(Action.USE_POTION))
                        {
                            Scanner keyboard = new Scanner(System.in);
                            System.out.println("Please enter the potion");
                            String obj = keyboard.next();
                            function.checkInput(obj);
                            Potion p = (Potion)var.EQUIP_TO_ENUM.get(obj);
                            this.usePotion(cur_hero, p);

                        }
                        else if(action.equals(Action.CAST_SPELL))
                        {
                            Scanner keyboard = new Scanner(System.in);
                            System.out.println("Please enter the spell:");
                            String obj = keyboard.next();
                            function.checkInput(obj);
                            Object spell = var.EQUIP_TO_ENUM.get(obj);
                            this.castSpell(cur_hero, cur_monster, spell);
                            

                        }
                        cur_monster.isDead();
                        cur_hero.isDead();
                    }
                }

            }
            if(cur_turn == CharacterType.MONSTER)
            {
                //Monster's turn
                for(int i =0;i<this.monsters.length;i++)
                {
                    Monster cur_monster;
                    Hero cur_hero;

                    cur_monster = this.monsters[i];

                    if(!cur_monster.getDead())
                    {
                        if(ifFullHero())
                        {
                            cur_hero = heros[i];
                        }
                        else
                        {
                            cur_hero = this.getNextHero();
                        }

                        System.out.println(ConsoleColors.RED+"It's MONSTER: "+cur_monster.getName()+" 's turn! '"+ConsoleColors.RESET);
                        this.monsterAttack(cur_monster, cur_hero);
                        cur_monster.isDead();
                        cur_hero.isDead();
                    }

                    
                }
            }
            this.displayInfo();
            this.changeTurn();
            this.regain();        
            
        }
        this.displayEndInfo();
        this.reward();


    }

    public boolean ifFullHero()
    {
        boolean temp = true;
        for(int i = 0;i<this.heros.length;i++)
        {
            if(this.heros[i].getDead())
            {
                temp = false;
            }
        
        }
        return temp;

    }



    public boolean ifFullMonster(){
        boolean temp = true;
        for(int i = 0;i<this.monsters.length;i++)
        {
            if(this.monsters[i].getDead())
            {
                temp = false;
            }
        
        }
        return temp;

    }

    public Monster getNextMonster()
    {
        for(int i =0;i<this.monsters.length;i++)
        {
            Monster cur_monster = this.monsters[i];
            if(!cur_monster.getDead())
            {
                return cur_monster;
            }
        }

        return new Monster("endvalue",new Double(1.0));
    }

    public Hero getNextHero(){
        for(int i = 0;i<this.heros.length;i++)
        {
            Hero cur_hero = this.heros[i];
            if(!cur_hero.getDead())
            {
                return cur_hero;
            }
        }
        return heros[0];
    }


    public boolean ifEnd(){
        return this.ifAllDeadHero() || this.ifAllDeadMonster();

    }

    public boolean ifAllDeadHero()
    {
        boolean temp = true;
        for(int i = 0;i<heros.length;i++)
        {
            if(!heros[i].getDead())
            {
                temp = false;
            }
            
        }

        return temp;
    }



    public boolean ifAllDeadMonster()
    {
        boolean temp = true;
        for(int i = 0;i<this.monsters.length;i++)
        {
            if(!this.monsters[i].getDead())
            {
                temp = false;
            }
            
        }

        return temp;

    }

    public void displayOpening(){
        System.out.println(ConsoleColors.BLUE+"Oops! You encounter a fight!"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE+"MONSTER:"+ConsoleColors.RESET);
        for(int i = 0;i<this.monsters.length;i++)
        {
            System.out.print(ConsoleColors.BLUE+" "+this.monsters[i].getName()+ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.BLUE+" have appeared!"+ConsoleColors.RESET);
        //details for monster
    }

    public void changeTurn(){
        if(cur_turn == CharacterType.HERO)
        {
            cur_turn = CharacterType.MONSTER;
        }
        else if(cur_turn == CharacterType.MONSTER)
        {
            cur_turn = CharacterType.HERO;
        }
    }

    public void monsterAttack(Monster monster,Hero hero)
    {
        double attack = monster.getDamage();
        if(!hero.isDodged())
        {
            if(hero.getArmor()!= null)
            {
                attack = attack-hero.getArmor().getDamage();
                System.out.println(ConsoleColors.RED+"MONSTER "+monster.getName()+" cause "+attack+" causality to"+" Hero "+hero.getName()+ConsoleColors.RESET);
                hero.setHp(hero.getHp()-attack);
            }
            else
            {
                System.out.println(ConsoleColors.RED+"MONSTER "+monster.getName()+" cause "+attack+" causality to"+" Hero "+hero.getName()+ConsoleColors.RESET);
                hero.setHp(hero.getHp()-attack);
            }
            
        }
        else
        {
            System.out.println(ConsoleColors.RED+"HERO "+hero.getName()+" DODGED"+ConsoleColors.RESET);
        }
   
        
    }

    

    public void regularAttack(Hero hero, Monster monster){
        //(strength + weapon damage)*0.05
        
        double attack;
        if(hero.getWeapon() != null)
        {
            System.out.print("With weapon");
            attack = (hero.getStrength()+hero.getWeapon().getDamage())*0.05;
        }
        else
        {
            System.out.println("without weapon");
            attack = (hero.getStrength())*0.05;
        }
        attack = attack - monster.getDefense()*0.1; 
        if(!monster.isDodged())
        {
            if(attack<0)
            {
                attack = 0;
            }
            System.out.println(ConsoleColors.RED+hero.getName()+" uses regular attack and cause "+attack+" causalty"+ConsoleColors.RESET);
            monster.setHp(monster.getHp()-attack);
        }

    }

    public void castSpell(Hero hero,Monster monster,Object spell){
        //判断是否足够Mana
        //damage range = spells_base_damage +(dexterity/10000)*spells_base_damage
        //
        if(spell instanceof FireSpells)
        {
            //reduce defense
            FireSpells f = (FireSpells) spell;
            if(f.getManaCost()>=hero.getMana())
            {
                return;
            }
            double damage = f.getDamage()+(hero.getDexterity()/10000)*f.getDamage();
            damage = damage - monster.getDefense();
            if(!monster.isDodged())
            {
                if(damage<0)
                {
                    damage = 0;
                }
                monster.setHp(monster.getHp()-damage);
                
                monster.setDefense(monster.getDefense()*0.9);
            }

            hero.removeItem(f);
            
        }
        else if(spell instanceof LightningSpells)
        {
            //reduce dodge chance
            LightningSpells l = (LightningSpells)spell;
            if(l.getManaCost()>=hero.getMana())
            {
                return;
            }
            double damage = l.getDamage()+(hero.getDexterity()/10000)*l.getDamage();
            damage = damage - monster.getDefense();
            if(!monster.isDodged())
            {
                if(damage<0)
                {
                    damage = 0;
                }
                monster.setHp(monster.getHp()-damage);
                monster.setDodge(monster.getDodge()*0.9);
            }
            hero.removeItem(l);

        }
        else if(spell instanceof IceSpells)
        {
            //reduce damage range
            IceSpells i = (IceSpells) spell;
            if(i.getManaCost()>=hero.getMana())
            {
                return;
            }
            double damage = i.getDamage()+(hero.getDexterity()/10000)*i.getDamage();
            damage = damage - monster.getDefense();
            if(!monster.isDodged())
            {
                if(damage<0)
                {
                    damage = 0;
                }
                monster.setHp(monster.getHp()-damage);
                monster.setDamage(monster.getDamage()*0.9);
            }
            hero.removeItem(i); 
        }

    }

    public void usePotion(Hero hero, Potion potion){
        //1:HP
    //2:Stren
    //3: mana,
    //4 dex
    //5 agi
        int type = potion.getAttType();
        if(type == 1)
        {
            hero.setHp(hero.getHp()+potion.getAttIncre());
        }
        else if(type == 2)
        {
            hero.setStrength(hero.getStrength()+potion.getAttIncre());
        }
        else if(type == 3)
        {
            hero.setMana(hero.getMana()+potion.getAttIncre());
        }
        else if(type == 4)
        {
            hero.setDexterity(hero.getDexterity()+potion.getAttIncre());
        }
        else if(type == 5)
        {
            hero.setAgility(hero.getAgility()+potion.getAttIncre());
        }
        hero.removeItem(potion); 
        
    }

    public void iskilled(){

    }

    public void changeEquipment(Hero hero){
        hero.changeEquipment();

    }

    public void regain(){
        //You can assume that during every round of a fight the heroes regain 5% of their hp and 5% of their mana.
        for(int i = 0;i<this.heros.length;i++)
        {
            Hero cur_hero = this.heros[i];
            if(!cur_hero.getDead())
            {
                cur_hero.setHp(cur_hero.getHp()*1.05);
                cur_hero.setMana(cur_hero.getMana()*1.05);
            }
        }
    }

    public void reward()
    {
        for(int i = 0;i<this.heros.length;i++)
        {
            Hero cur_hero = this.heros[i];
            if(!cur_hero.getDead())
            {
                cur_hero.setMoney(cur_hero.getMoney()+150);
                cur_hero.setExp(cur_hero.getExp()+2);
                cur_hero.ifLevelUp();
                
            }
            else
            {
                cur_hero.setHp(cur_hero.getHp()/2);
                cur_hero.setDead(false);
            }
        }
    }


    public Action getAction(Hero cur_hero){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("HERO:"+cur_hero.getName());
        System.out.println("Please enter the action: (REGULAR_ATTACK,CAST_SPELL,USE_POTION, CHANGE_EQUIPMENT)");
        String answer = keyboard.nextLine();
        function.checkInput(answer);
        Action action;
        if(answer.equals("REGULAR_ATTACK") || answer.equals("CAST_SPELL") || answer.equals("USE_POTION")||answer.equals("CHANGE_EQUIPMENT"))
        {
            action = var.ACTION_TO_ENUM.get(answer);
            return action;
        }
        return Action.INVALID;
    }

    public void displayEndInfo()
    {
        if(this.ifAllDeadHero())
        {
            System.out.println(ConsoleColors.RED+"You Lose!"+ConsoleColors.RESET);
            //restart

        }
        else if(this.ifAllDeadMonster())
        {
            System.out.println(ConsoleColors.RED+"You win! Congratulations!"+ConsoleColors.RESET);

        }
    }

    public void displayInfo()
    {
        System.out.println("=====================");
        System.out.println("Heros:");
        for(int i = 0;i<this.heros.length;i++)
        {
            Hero cur_hero = this.heros[i];
            cur_hero.displayInfoFight();
        }
        System.out.println("V.S.");
        System.out.println("Monsters:");
        for(int i = 0;i<this.monsters.length;i++)
        {
            Monster cur_monster = this.monsters[i];
            cur_monster.displayInfoFight();
        }
        System.out.println("=====================");

    }

    

}