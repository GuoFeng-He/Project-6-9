public class Player{
    private int score;
    private int moves;
    private int health;
    private int attack;
    private String name;

    public Player(String name, int health, int attack) {
        super(name.substring(0, 1)); // symbol is first initial
        score = 0;
        moves = 0;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public int getMoves() {
        return moves;
    }

    public void move() {
        moves++;
    }

    public void addPoints(int amt) {
        score += amt;
    }
}
