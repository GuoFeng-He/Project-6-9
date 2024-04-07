import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;

public class Player{
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

    public Player(String name) {
        scan = new Scanner(System.in);
        maxHealth = 100;
        hp = maxHealth;
        healthPotAmount = 2;
        attack = 5;
        score = 0;
        moves = 0;
        this.name = name;
        inventory = new ArrayList<Items>();
        gold = 0;
    }
    public int getHealth(){
        return hp;
    }
    public int getAttack(){
        return attack;
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
    public String getStats(){
        String s = "Name:" + name;
        s += "\nHealth (\uD83E\uDDE1): " + hp + "/" + maxHealth;
        s += "\nAttack (⚔): " + attack;
        s += "\nGold (\uD83E\uDE99): " + gold;
        s += "\nArmor (⛨): " + armor.getName();
        s += "\nWeapon (\uD83D\uDD2A): " + weapon.getName();
        return s;
    }
    public void equipInventory(){
        showInventory();
        if(inventory != null){
            System.out.print("Enter the number to equip (0 to end): ");
            int option = scan.nextInt();
            if (option < 0 || option > inventory.size()){
                System.out.println("Invalid option, tru again");
                equipInventory();
            }else if(option != 0){
                equip(option-1);
                equipInventory();
            }
        }
    }
    public void showInventory(){
        if (inventory == null){
            System.out.println("You have no items currently");
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
            System.out.println("\nChoose your move: ");
            System.out.println("1. Sword Swing (0 SP): Deals damage to a single target");
            System.out.println("2. Cleave (2 SP): Deals damage to a target and adjacent ones");
            System.out.println("3. Cascading Lance (4 SP): Deals heavy damage to all targets");
            System.out.println("4. Consume health pot (you have " + healthPotAmount + ")");
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
            hp += maxHealth * 0.75; // option 4: drinking a health potion
            if (hp > maxHealth){
                hp = maxHealth;
            }
            healthPotAmount--;
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
        System.out.println("Pick a target (number): ");
        int choice = scan.nextInt();
        scan.nextLine();
        if (choice < 1 || choice > mobs.size()){
            System.out.println("Invalid option, try again: ");
            choice = scan.nextInt();
        }
        System.out.println();
        return choice - 1;
    }

    // methods used for dealing damage to the player
    public void damage(int damage){
        hp -= damage;
    }

}
