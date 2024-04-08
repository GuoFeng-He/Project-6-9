import java.util.ArrayList;
import java.util.Scanner;

public class Player{
    public static boolean isAlive = true;
    private int score;
    private int moves;
    private int maxHealth;
    private int hp;
    private int healthPotAmount;
    private int attack;
    private String name;
    private int gold;
    private ArrayList<Items>inventory;
    private Armor armor;
    private Weapon weapon;
    private Scanner scan;
    // status effects
    private boolean isBurning;
    private boolean isBleeding;
    private ArrayList<Integer[]> statusEffects;

    public Player(String name) {
        scan = new Scanner(System.in);
        maxHealth = 2500;
        hp = maxHealth;
        healthPotAmount = 2;
        attack = 12000;
        score = 0;
        moves = 0;
        this.name = name;
        inventory = new ArrayList<Items>();
        gold = 0;
        isBleeding = false;
        isBurning = false;
        statusEffects = new ArrayList<>();
        armor = new Armor("",0,0);
        weapon = new Weapon("",0,0);
    }

    public int getHealth(){
        return hp;
    }
    public int getAttack(){
        return attack;
    }
    public boolean getIsAlive(){
        return isAlive;
    }
    public ArrayList<Integer[]> getStatusEffects(){
        return statusEffects;
    }
    public int getScore() {
        return score;
    }

    public int getMoves() {
        return moves;
    }
    public void setAttack(int a){
        attack = a;
    }
    public void setHealth(int h){
        hp = h;
    }
    public void move() {
        moves++;
    }

    public void addPoints(int amt) {
        score += amt;
    }
    public int getGold(){
        return gold;
    }
    public void addGold(int n){
        gold += n;
    }

    public String getStatusEffectDurations(){
        String returnString = "|";
        for (Integer[] s : statusEffects){
            returnString += s[0] + "|";
        }
        return returnString;
    }

    public String getStats(){
        String s = "> > > " + name + " < < <";
        s += "\n[\uD83E\uDDE1] Health: " + hp + "/" + maxHealth;
        s += "\n[⚔️] Attack: " + attack;
        s += "\n[\uD83E\uDE99] Gold: " + gold;
        s += "\n[\uD83D\uDEE1️] Armor: leather armor" /*+ armor.getName()*/;
        s += "\n[\uD83D\uDD2A] Weapon: Devils' Paintbrush" /*+ weapon.getName()*/;
        s += "\nStatus Effect Durations: " + getStatusEffectDurations();
        s += "\nSkill points: " + Combat.skillPoints;
        return s;
    }
    public void equipInventory(){
        showInventory();
        if(!inventory.isEmpty()){
            System.out.print("Enter the number to equip [0 to end]: ");
            int option = scan.nextInt();
            if (option < 0 || option > inventory.size()){
                System.out.println("Invalid option, try again");
                equipInventory();
            }else if(option != 0){
                equip(option-1);
                equipInventory();
            }
        }
    }
    public void showInventory(){
        if (inventory.isEmpty()){
            System.out.println("You currently have no items!");
        }else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i+1) + ". " + inventory.get(i).displayInfo());
            }
        }
    }
    public void addItem(Items i){
        inventory.add(i);
    }
    public void equip(int num){
        if (num > inventory.size()){
            System.out.println("Not an item");
            Scanner s = new Scanner(System.in);
            System.out.print("Enter again: ");
            equip(s.nextInt());
        }else{
            if (inventory.get(num) instanceof Armor) {
                if (armor != null) {
                    maxHealth -= armor.getHp();
                }
                armor = (Armor) inventory.get(num);
                maxHealth += armor.getHp();
                inventory.add(armor);
                armor = (Armor) inventory.remove(num);
            }
            if (inventory.get(num) instanceof Weapon) {
                if (weapon != null) {
                    attack -= weapon.getDmg();
                }
                weapon = (Weapon) inventory.get(num);
                attack += weapon.getDmg();
                inventory.add(weapon);
                weapon = (Weapon) inventory.remove(num);
            }
        }
    }

    // player attacking abilities
    public int swordSwing(){
        double random = (Math.random() + 0.76) * attack;
        return (int) random;
    }

    public int swordCleave(){
        double random = (Math.random() + 1.01) * attack;
        return (int) random;
    }

    public int cascadingLance(){
        double random = (Math.random() + 2.01) * attack;
        return (int) random;
    }

    // player action menu
    public void act(ArrayList<Enemy> mobs){
        int choice = 0;
        boolean pass = false;
        while ((choice < 1 || choice > 4) || !pass) {
            System.out.println("\nOptions: ");
            System.out.println("\t1. [0 SP] Sword Swing: Deals damage to a single target");
            System.out.println("\t2. [2 SP] Cleave: Deals damage to a target and adjacent ones");
            System.out.println("\t3. [4 SP] Cascading Lance: Deals heavy damage to all targets");
            System.out.println("\t4. [" + healthPotAmount + "] Consume health pot");
            System.out.print("Choose your move >> ");
            choice = scan.nextInt();
            System.out.println();

            if (choice < 1 || choice > 4 || (choice == 4 && healthPotAmount == 0)){
                System.out.println("Invalid choice, please try again");
            }
            if ((choice == 2 && Combat.skillPoints < 2) || (choice == 3 && Combat.skillPoints < 4)){
                System.out.println("You do not have enough skill points to use this");
            } else{
                pass = true;
            }
        }
        if (choice == 1){
            attack(mobs, 1);
        } else if (choice == 2){
            attack(mobs, 2);
        } else if (choice == 3){
            attack(mobs, 3);
        } else {
            hp += (int) (maxHealth * 0.8); // option 4: drinking a health potion
            if (hp > maxHealth){
                hp = maxHealth;
            }
            healthPotAmount--;
            statusEffects.clear();
            System.out.println("You drank a health pot and recovered 75% of your health!");
        }
    }

    // manages damage done by the player against dragons according to respective ability used
    // manages skill point gain/uses as well
    public void attack(ArrayList<Enemy> mobs, int move){
        int damage;
        if (move != 3) {
            int target = target(mobs);
            if (move == 1){
                damage = swordSwing();
                System.out.println(Color.CYAN + "You hit the " + mobs.get(target)+ " for " + damage + " damage!");
                mobs.get(target).takeDamage(damage);
                Combat.skillPoints++;
            } else {
                damage = swordCleave();
                if (target == 0 || target == mobs.size() - 1){
                    if (target == 0) {
                        if (mobs.size() == 1 || mobs.get(target + 1) == null){
                            System.out.println(Color.CYAN + "You hit the " + mobs.get(target) + " for " + damage + " damage!");
                        } else {
                            System.out.println(Color.CYAN + "You hit the " + mobs.get(target) + " and " + mobs.get(target + 1) + " for " + damage + " damage!");
                            mobs.get(target).takeDamage(damage);
                            mobs.get(target).takeDamage(damage);
                        }
                    } else{
                        System.out.println(Color.CYAN + "You hit the " + mobs.get(target) + " and " + mobs.get(target - 1)+ " for " + damage + " damage!");
                        mobs.get(target).takeDamage(damage);
                        mobs.get(target - 1).takeDamage(damage);
                    }
                } else {
                    System.out.println(Color.CYAN + "You hit the " + mobs.get(target - 1) + ", " + mobs.get(target)+ " and " + mobs.get(target + 1) + " for " + damage + " damage!");
                    mobs.get(target - 1).takeDamage(damage);
                    mobs.get(target).takeDamage(damage);
                    mobs.get(target + 1).takeDamage(damage);
                }
                Combat.skillPoints -= 2;
            }
        } else {
            damage = cascadingLance();
            System.out.println(Color.CYAN + "You hit all targets for " + damage + " damage!");
            for (Enemy mob: mobs){
                mob.takeDamage(damage);
            }
            Combat.skillPoints -= 4;
        }
        Combat.skillPoints++;
        System.out.print(Color.RESET);
    }

    // allows the player to select a specific dragon to target
    public int target(ArrayList<Enemy> mobs){
        System.out.print("Pick a target (number): ");
        int choice = scan.nextInt();
        scan.nextLine();
        if (choice < 1 || choice > mobs.size()){
            System.out.print("Invalid option, try again >> ");
            choice = scan.nextInt();
        }
        System.out.println();
        return choice - 1;
    }

    // methods used for dealing damage to the player
    // first index of damage is damage; second index is 1 if it applies a status effect, 0 if it doesn't
    public void damage(int[] damage){
        hp -= damage[0];
        if (damage[1] == 1){
            statusEffects.add(new Integer[]{damage[0] / 2, 2}); // adds status effects (first idx is dmg, second is duration)
        }
    }

    // e[0] is duration; e[1] is damage
    public void applyStatusEffects(){
        if (!statusEffects.isEmpty()){
            for (int i = 0; i < statusEffects.size(); i++){
                Integer[] e = statusEffects.get(i);
                damage(new int[] {e[0], 0});
                e[1]--;

                System.out.println("You took " + e[0] + " damage from a status effect");
                if(e[1] == 0){
                    statusEffects.remove(e);
                }
            }
        }
    }

}
