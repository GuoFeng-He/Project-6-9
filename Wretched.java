public class Wretched extends Enemy{
    public Wretched(int health, int attack){
        super(health, attack);
    }

    // Attack 1: Swing
    @Override
    public int attackOne(){
        return getAttack();
    }

    @Override
    public void abilityOne(){
        System.out.println("The Veteran Wretched's anger grows! Its attack has increased.");
        buffAttack(1.25);
    }

}
