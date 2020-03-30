//Created by Jinshu Yang
//March 23, 2020

//The file contains the structure of Market
import java.util.List;
import java.util.Scanner;
import equipment.*;

public class Market extends Cell {
    private Panel panel;
    private Hero[] heros;
    private boolean ifend;
    private Function function;

    private boolean ifMonsters;



    public Market(Coordinate index, boolean ifAcc,Function function) {
        super(index, true);
        this.setIfMonsters(false);
        this.ifend = true;
        this.function = function;
        
        
    }

    public void run(){
        initMarket();
        while(this.ifend)
        {
            for(int i = 0;i<this.heros.length;i++)
            {
                Hero cur_hero = this.heros[i];
                System.out.println("HERO: "+cur_hero.getName());
                this.askForAction(cur_hero);
            }
        }

    }

    public void setHeros(Hero[] heros){
        this.heros = heros;
    }

    public void askForAction(Hero hero){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your action (SELL,BUY,Q):");
        String answer = keyboard.next();
        function.checkInput(answer);
        if(answer.equals("SELL"))
        {
            System.out.println("YOUR BAG:");
            hero.getBag().printByDirection();
            System.out.println("PLEASE ENTER THE ITEM THAT YOU WANT TO SELL:");
            String item = keyboard.next();
            hero.sell(item, this);
        }
        else if(answer.equals("BUY"))
        {
            this.panel.printByDirection();
            System.out.println("PLEASE ENTER THE ITEM THAT YOU WANT TO BUY:");
            String item = keyboard.next();
            hero.buy(item,this);
        }
        else if(answer.equals("Q"))
        {
            this.ifend = false;
            System.out.println("YOU QUIT THE MARKET");
        }

        

    }

 

    



    public boolean getIfMonster() {
        return this.ifMonsters;
    }

    public void setIfMonsters(boolean ifMonsters) {
        this.ifMonsters = ifMonsters;
    }

    public Market() {
        this.setIfMonsters(false);
    }

    public void initMarket(){
        panel = new Panel();
        panel.fillPanel();
        System.out.println("Welcome to Market");
        panel.printByDirectionMarket();
    }

    public double sell(Object item)
    { //人卖东西给市场
        if (item instanceof Weaponry) {
             return ((Weaponry) item).getMoneyCost()/2;
        }

        if (item instanceof Armory) {
            return ((Armory) item).getMoneyCost()/2;
        }

        if (item instanceof Potion) {
            return ((Potion) item).getMoneyCost()/2;
        }

        if (item instanceof IceSpells) {
            return ((IceSpells) item).getMoneyCost()/2;
        }

        if (item instanceof FireSpells) {
            return ((FireSpells) item).getMoneyCost()/2;
        }

        if (item instanceof LightningSpells) {
            return ((LightningSpells) item).getMoneyCost()/2;
        }

        return -1;

    }

    public Object buy(Object item,Hero hero){
        if (item instanceof Weaponry) {
            Weaponry temp = (Weaponry)item;
            double req_level = temp.getRequiredLevel();
            double price = temp.getMoneyCost();

            double hero_level = hero.getlevel();
            double hero_money = hero.getMoney();
            if((req_level<=hero_level)&&(price<=hero_money))
            {
                hero_money = hero_money-price;
                hero.setMoney(hero_money);
                return temp;
            }
            else
            {
                System.out.println("CAN'T BUY THIS ITEM");
            }
            return null;
       }

       if (item instanceof Armory) {
            Armory temp = (Armory)item;
            double req_level = temp.getRequiredLevel();
            double price = temp.getMoneyCost();

            double hero_level = hero.getlevel();
            double hero_money = hero.getMoney();
            if((req_level<=hero_level)&&(price<=hero_money))
            {
                hero_money = hero_money-price;
                hero.setMoney(hero_money);
                return temp;
            }
            else
            {
                System.out.println("CAN'T BUY THIS ITEM");
            }
            return null;
       }

       if (item instanceof Potion) {
            Potion temp = (Potion)item;
            double req_level = temp.getRequiredLevel();
            double price = temp.getMoneyCost();

            double hero_level = hero.getlevel();
            double hero_money = hero.getMoney();
            if((req_level<=hero_level)&&(price<=hero_money))
            {
                hero_money = hero_money-price;
                hero.setMoney(hero_money);
                return temp;
            }
            else
            {
                System.out.println("CAN'T BUY THIS ITEM");
            }
            return null;
       }

       if (item instanceof IceSpells) {
            IceSpells temp = (IceSpells)item;
            double req_level = temp.getRequiredLevel();
            double price = temp.getMoneyCost();

            double hero_level = hero.getlevel();
            double hero_money = hero.getMoney();
            if((req_level<=hero_level)&&(price<=hero_money))
            {
                hero_money = hero_money-price;
                hero.setMoney(hero_money);
                return temp;
            }
            else
            {
                System.out.println("CAN'T BUY THIS ITEM");
            }
            return null;
       }

       if (item instanceof FireSpells) {
            FireSpells temp = (FireSpells)item;
            double req_level = temp.getRequiredLevel();
            double price = temp.getMoneyCost();

            double hero_level = hero.getlevel();
            double hero_money = hero.getMoney();
            if((req_level<=hero_level)&&(price<=hero_money))
            {
                hero_money = hero_money-price;
                hero.setMoney(hero_money);
                return temp;
            }
            else
            {
                System.out.println("CAN'T BUY THIS ITEM");
            }
            return null;
       }

       if (item instanceof LightningSpells) {
            LightningSpells temp = (LightningSpells)item;
            double req_level = temp.getRequiredLevel();
            double price = temp.getMoneyCost();

            double hero_level = hero.getlevel();
            double hero_money = hero.getMoney();
            if((req_level<=hero_level)&&(price<=hero_money))
            {
                hero_money = hero_money-price;
                hero.setMoney(hero_money);
                return temp;
            }
            else
            {
                System.out.println("CAN'T BUY THIS ITEM");
            }
            return null;
       }

       return null;


    }

    @Override
    public String toString(){
        return "M";
    }


}