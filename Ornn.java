public class Ornn extends Enemy{

    public Ornn() {
        super(5000, 50);
    }
    @Override
    public int attackOne(){
        int damage = getAttack();
        System.out.println("Ornn creates a magma pillar under you, dealing " + damage + " damage.");
        return damage;
    }
    @Override
    public void abilityOne(){
        buffAttack(1.1);
        System.out.println("Ornn upgrades his weapon and increases his damage by 10%.");
    }
    @Override
    public int[] selectAction(Player player){
        int random = (int)(Math.random() * 2);
        if (random == 0){
            return new int[] {attackOne(),0};
        }
        abilityOne();
        return new int[]{attackTwo(), 0};
    }
}
