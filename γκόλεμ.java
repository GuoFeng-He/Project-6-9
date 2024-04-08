//golem
public class γκόλεμ extends Enemy{
    private int buffedTurns;

    public γκόλεμ() {
        super(2500, 150);
    }

    @Override
    public String toString() {
        return "γκόλεμ";
    }

    @Override
    public int attackOne(){
        int damage = getAttack();
        System.out.println("The γκόλεμ has smashed you for " + damage);
        return damage;
    }
    @Override
    public void abilityOne(){
        buffDefense(1.2);
        System.out.println("The γκόλεμ hardens and takes 20% less damage for 2 turns.");
        buffedTurns = 2;
    }
    @Override
    public int[] selectAction(Player player){
        buffedTurns --;
        int random = (int)(Math.random() * 2);
        if (random == 0){
            return new int[] {attackOne(),0};
        }
        if (buffedTurns < 0){
            buffedTurns = 0;
        }
        abilityOne();
        return new int[]{attackTwo(), 0};
    }
}
