import java.util.ArrayList;
import java.util.Arrays;

public class ForsakenGrove extends Grid{
    private static ArrayList<Enemy> worldEnemies = new ArrayList<>(Arrays.asList(new MantisSentinel(), new MantisWarrior()));

    public ForsakenGrove() {
        setEnemyEmoji("\uD83E\uDEB2");
        setWorldEnemies(worldEnemies);
    }

    @Override
    public void createGrid(String tileColor) {
        super.createGrid("🟩");
    }
}
