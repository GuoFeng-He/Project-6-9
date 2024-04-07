import java.util.ArrayList;
import java.util.Scanner;

public class Player{
    private int score;
    private int moves;
    private int maxHealth;
    private int hp;

    private int attack;
    private String name;
    private int gold;
    private ArrayList<Items>inventory;
    private Armor armor;
    private Weapon weapon;

    public Player(String name) {
        this.name = name;
        maxHealth = 100;
        hp = maxHealth;
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
        s += "\nHealth: " + hp + "/" + maxHealth;
        s += "\nDamage: " + attack;
        s += "\nGold: " + gold;
        s += "\nArmor: " + armor.getName();
        s += "\nWeapon: " + weapon.getName();
        return s;
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
            }
            if (inventory.get(num) instanceof Weapon) {
                if (weapon != null) {
                    attack -= weapon.getDmg();
                }
                weapon = (Weapon) inventory.get(num);
                attack += weapon.getDmg();
            }
        }
    }
}
