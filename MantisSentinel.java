public class MantisSentinel extends Enemy{
    public MantisSentinel(int health, int attack){
        super(health, attack);
    }

    @Override
    public String toString(){
        return "Mantis Sentinel";
    }

    @Override
    public int attackOne(){
        int damage = super.getAttack();
        System.out.println("The Mantis Sentinel shot you for " + damage + " damage!");
        return damage;
    }

    @Override
    // dmg + dot
    public int attackTwo(){
        int damage = super.getAttack();
        System.out.println("The Mantis Sentinel blasted you for " + damage + " damage! You will be taking " + (damage / 2) + " damage for the next 2 turns." );
        return damage;
    }

    @Override
    public void selectAction(){
        int random = (int)(Math.random() * 2);
        if (random == 1){
            attackOne();
        } else {
            attackTwo();
        }
    }
}
