import java.util.ArrayList;
import java.util.Arrays;

public class ForsakenGrove extends Grid{

    public ForsakenGrove() {
        super(new MantisLords(6000, 250, .2), "\uD83E\uDEB2", "\uD83D\uDFE9", player);
        addEnemy(new MantisWarrior());
        addEnemy(new MantisSentinel());
        printGrid();
        movePlayer();
    }
}
