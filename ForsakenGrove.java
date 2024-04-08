import java.util.ArrayList;
import java.util.Arrays;

public class ForsakenGrove extends Grid{

    public ForsakenGrove() {
        super(new MantisLord(), "\uD83E\uDEB2", "\uD83D\uDFE9", );
        addEnemy(new MantisWarrior());
        addEnemy(new MantisSentinel());
        printGrid();
        movePlayer();
    }
}
