import java.io.IOException;
import java.util.ArrayList;

public class Enemy {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private double damageBoost;
    private double defenseBoost;
    private int turnsTaken;
    private int buffDuration;
    private static double enemyScaling;

    public Enemy(String name, int health, int attack){
        this.name = name;
        this.health = health;
        this.attack = attack;
        defense = 1;
        defenseBoost = 0;
        damageBoost = 0;
        turnsTaken = 0;
        buffDuration = 0;
        enemyScaling = 1;
    }

    public int getTurnsTaken(){
        return turnsTaken;
    }
    public int getAttack(){
        return attack;
    }
    public void buffAttack(double multiplier){
        attack *= multiplier;
    }
    public void buffDefense(double multiplier){
        defense *= multiplier;
    }
    public int getHealth(){
        return health;
    }

    // damage boost management
    public double getDamageBoost(){
        return damageBoost;
    }


    public void addDamageBoost(double boost, int turns){
        damageBoost += boost;
        buffDuration = turns;
    }
    public void addDefenseBoost(double boost, int turns){
        defenseBoost += boost;
        buffDuration = turns;
    }
    public void removeDefenseBoost(){
        defenseBoost = 0;
    }
    public void removeDamageBoost(){
        damageBoost = 0;
    }

    public void takeDamage(int damage){
        health -= (int)(damage * (1/defense));
    }

    public void setHealth(int health){
        this.health = health;
    }

    // placeholder for attacks and abilities
    public int attackOne(){
        return 0;
    }
    public int attackTwo(){
        return 0;
    }
    public int attackThree(){
        return 0;
    }
    public void abilityOne(){}
    public void abilityTwo(){}

    public int selectAction(){
        buffDuration--;
        return 0;
    }

    public void attack(Player player){
         player.damage(selectAction());
    }

    public void pause(int ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // completely arbitrary health/attack values subject to change
    public static Enemy randomEnemy(String area){
        int random = (int)(Math.random() * 3);
        if (random == 0){
            if (Grid.worldNum == 1){
                return new MantisSentinel((int)(2000 * enemyScaling), (int)(200 * enemyScaling));
            } else if (Grid.worldNum == 2) {
//                return; // to be implemented enemy
            } else {
//                return void; // to be implemented enemy (for lab zero)
            }
        } else if (random == 1){
            if (Grid.worldNum == 1){
                return new MantisWarrior((int)(3500 * enemyScaling), (int)(400 * enemyScaling));
            } else if (Grid.worldNum == 2){
                return; // to be implemented enemy
            } else {
                return; // to be implemented enemy (for lab zero)
            }
        } else {
            if (Grid.worldNum == 1{
                return; // to be implemented enemy
            }else if (Grid.worldNum == 2){
                return; // to be implemented enemy
            } else {
                return; // to be implemented enemy (for lab zero)
            }
        }
    }

    public void printEnemy(){
        System.out.println(this);
        System.out.println("Health (\uD83E\uDDE1): " + getHealth());
        System.out.println("Attack(⚔): " + getAttack());
    }

    public void death(ArrayList<Enemy> mobList){
        for (int i = 0; i < mobList.size(); i++){
            if (this.equals(mobList.get(i))){
                mobList.remove(i);
                break;
            }
        }
    }
}
