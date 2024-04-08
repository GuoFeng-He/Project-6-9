import java.util.ArrayList;

public class Combat {
    private ArrayList<Enemy> combatList;
    public static int skillPoints;

    public Combat(String area, int numOfMobs, Player player){
        skillPoints = 0;
        combatList = new ArrayList<>();
        for (int i = 0; i < numOfMobs; i++){
                combatList.add(Enemy.randomEnemy(area));
        }

        // fighting
        while (!combatDone(player)){
            for (Enemy e : combatList){
                e.printEnemy();
            }
            player.applyStatusEffects();
            System.out.println("\n" + player.getStats());
            player.act(combatList);

            for (int i = 0; i < combatList.size(); i++){
                Enemy e = combatList.get(i);
                if (e.getHealth() <= 0){
                    e.death(combatList);
                    i--;
                } else {
                    e.attack(player);
                }
            }
        }
    }

    public boolean combatDone(Player player){
        return (combatList.isEmpty() || player.getHealth() <= 0);
    }
}
