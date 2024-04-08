public class Yarthul extends Boss{
    private int shield;
    public static int phase;
    private boolean weakened;
    public Yarthul(){
        super(5000, 300, .4);
        shield = 2500;
        phase = 1;
        weakened = false;
    }

    @Override
    public String toString(){
        return "Yarthul";
    }

    public void takeDamage(int damage, Player player){
        if (shield > 0){
            shield -= damage;
            System.out.println("You hit Yarthul's magma armor for " + damage + " damage");
            abilityThree(player);
            if (shield <= 0){
                System.out.println("You broke Yarthul's shield! He will now be taking two times more damage.");
                shield = 0;
                weakened = true;
            }
        } else {
            damage *= 2;
            System.out.println("You hit Yarthul for " + damage + " damage!");
            super.takeDamage(damage);
        }
    }

    @Override
    public int[] basicAttack(){
        int damage = super.getAttack();
        if (phase == 1) {
            System.out.println("Yarthul slashed you for " + damage + " damage");
            return new int[]{damage, 0};
        } else {
            damage = (int)(super.getAttack() * 1.5);
            System.out.println("Yarthul breathed fire, scorching you for " + damage + " damage and will be burning you for " +
                    (damage / 2) + " damage for the next two turns");
            return new int[]{damage, 1};
        }
    }

    @Override
    public int attackOne(){
        int damage = super.getAttack() * 2;
        if (phase == 1){
            System.out.println("Yarthul bites you for " + damage + " damage");
        } else {
            damage = super.getAttack() * 3;
            System.out.println("Yarthul blows you up with a fireball for " + damage + " damage. You will be burning for the next" +
                    "two turns");
        }
        return damage;
    }

    @Override
    public void abilityOne(){
        int shield = getHealth() / 2;
        System.out.println("Yarthul reinforced itself with a shield of " + shield + " hitpoints");
        this.shield += shield;
    }

    public void abilityTwo(Player player){
        if (!player.getStatusEffects().isEmpty()){
            int heal = (int)(getHealth() * 0.15);
            System.out.println("Because you're burning, Yarthul healed " + heal + " health");
            setHealth(getHealth() + heal);
        } else {
            System.out.println("Yarthul enrages, increasing his attack!");
            buffAttack(1.1);
        }
    }

    // hitting armor has a "thorn-like" effect
    public void abilityThree(Player player){
        int damage = super.getAttack() / 5;
        System.out.println("Hitting Yarthul's magma armor scorched you for " + damage + " damage.");
        player.damage(new int[]{damage, 1});
    }

    // placeholder method
    @Override
    public int[] selectAction(Player player){
        int random = (int)(Math.random() * 6);
        if (random == 0 || random == 1){
            return basicAttack();
        } else if (random == 2){
            attackOne();
        } else if (random == 3){
            abilityOne();
        } else if (random == 4){
            abilityTwo(player);
        }
        return new int[]{0, 0};
    }

    @Override
    public void printEnemy(){
        super.printEnemy();
        System.out.println("Shield (\uD83D\uDEE1\uFE0F): " + shield);
    }
}
