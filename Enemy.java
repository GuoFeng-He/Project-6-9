import java.io.IOException;
import java.util.ArrayList;

public class Enemy {
    private String name;
    private int health;
    private int attack;
    private double damageBoost;
    private int turnsTaken;
    private int buffDuration;
    private static double enemyScaling;

    public Enemy(int health, int attack){
        name = "";
        this.health = health;
        this.attack = attack;
        damageBoost = 0;
        turnsTaken = 0;
        buffDuration = 0;
        enemyScaling = 1;
    }

    public int getAttack(){
        return attack;
    }
    public void buffAttack(double multiplier){
        attack *= multiplier;
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

    public void removeDamageBoost(boolean instant){
        if (instant){
            damageBoost = 0;
            return;
        }
        if (buffDuration > 0) {
            buffDuration--;
        } else {
            damageBoost = 0;
        }
    }

    public void takeDamage(int damage){
        health -= damage;
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

    public int[] selectAction(Player player){
        return new int[]{};
    }

    public void attack(Player player){
         player.damage(selectAction(player));
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
        if (random == 0) {
            if (Grid.worldNum == 1) {
                return new MantisSentinel();
            } else {
                return; // to be implemented enemy
            }
        } else if (random == 1) {
            if (Grid.worldNum == 1) {
                return new MantisWarrior();
            } else {
                return; // to be implemented enemy
            }
        } else {
            if (Grid.worldNum == 1) {
                return; // to be implemented enemy
            } else {
                return; // to be implemented enemy
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
            if (this.equals(mobList.get(i)) && !(mobList.get(i) instanceof Boss)){
                mobList.remove(i);
                break;
            } else if (mobList.get(i) instanceof Boss && ((Boss) mobList.get(i)).phase == 2){
                mobList.remove(i);
                break;
            }
        }
    }
}
