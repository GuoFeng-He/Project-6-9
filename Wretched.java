public class Wretched extends Enemy{
    public Wretched(int health, int attack, boolean isVeteran){
        super(health, attack);
    }

    // Attack 1: Swing
    @Override
    public int attackOne(){
        if (getVet()){
            return (int)(getAttack() * 1.5);
        }
        return getAttack();
    }

    @Override
    public void abilityOne(){
        System.out.println("The Veteran Wretched's anger grows! Its attack has increased.");
        buffAttack(1.25);
    }

    @Override
    public void selectAction(){

    }
}
