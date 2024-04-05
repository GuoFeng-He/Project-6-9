import java.util.ArrayList;

public class Player{
    private int score;
    private int moves;
    private int health;
    private int attack;
    private String name;
    private int gold;
    private ArrayList<Items>inventory;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.attack = 5;
        score = 0;
        moves = 0;
        this.name = name;
        inventory = new ArrayList<Items>();
        gold = 0;
    }
    public int getHealth(){
        return health;
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
        health = h;
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
}
