import java.io.IOException;

public class Enemy {
    private String name;
    private int health;
    private int attack;
    private double damageBoost;
    private int turnsTaken;
    private int buffDuration;
    private static double enemyScaling;
    private boolean isDead;

    public Enemy(int health, int attack){
        name = "";
        this.health = (int) (health * enemyScaling);
        this.attack = (int) (attack * enemyScaling);
        damageBoost = 0;
        turnsTaken = 0;
        buffDuration = 0;
        enemyScaling = 1;
    }

    public int getAttack(){
        return attack;
    }

    public boolean isDead() {
        return isDead;
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

    public void removeDamageBoost(){
        damageBoost = 0;
    }

    public void takeDamage(int damage){
        health -= damage;
        if (health <= 0){
            health = 0;
            isDead = true;
        }
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
         selectAction();

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
            if (area.equals("grove")){
                return new MantisSentinel();
            } else if (area.equals("volcano")){
                return; // to be implemented enemy
            } else {
                return; // to be implemented enemy (for lab zero)
            }
        } else if (random == 1){
            if (area.equals("grove")){
                return new MantisWarrior();
            } else if (area.equals("volcano")){
                return; // to be implemented enemy
            } else {
                return; // to be implemented enemy (for lab zero)
            }
        } else {
            if (area.equals("grove")){
                return; // to be implemented enemy
            }else if (area.equals("volcano")){
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
}
