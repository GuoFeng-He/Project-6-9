import java.io.IOException;

public class Enemy {
    private int health;
    private int attack;
    private double damageBoost;
    private boolean isVeteran;
    private int turnsTaken;
    private int buffDuration;

    public Enemy(int health, int attack){
        this.health = health;
        this.attack = attack;
        damageBoost = 0;
        turnsTaken = 0;
        buffDuration = 0;
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
    public boolean getVet(){
        return isVeteran;
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

    public void selectAction(){
        buffDuration--;
    }

    public void pause(int ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
