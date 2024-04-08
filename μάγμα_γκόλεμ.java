//magma golem
public class μάγμα_γκόλεμ extends Enemy{
    private int buffedTurns;

    public μάγμα_γκόλεμ() {
        super("μάγμα γκόλεμ", 2500, 150);
    }
    @Override
    public int attackOne(){
        int damage = getAttack();
        System.out.println("The μάγμα γκόλεμ has smashed you for " + damage);
        return damage;
    }
    @Override
    public void abilityOne(){
        buffDefense(20);
        System.out.println("The μάγμα γκόλεμ hardens and takes 20% less damage for 2 turns.");
        buffedTurns = 2;
    }
    @Override
    public int[] selectAction(Player player){
        buffedTurns --;
        int random = (int)(Math.random() * 2);
        if (random == 0){
            return new int[]{attackOne(), 0};
        }
        if (buffedTurns < 0){
            buffedTurns = 0;
        }
        abilityOne();
        return new int[]{0, 0};
    }
}
