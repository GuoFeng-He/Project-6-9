public class Ouroboros extends Boss{
    public Ouroboros(int health, double bossArmor, int attack){
        super(health, attack, bossArmor);
    }

    @Override
    public int basicAttack(){
        int damage = super.basicAttack();
        System.out.println("Ouroboros bites you for " + damage + " damage");
        return damage;
    }
}
