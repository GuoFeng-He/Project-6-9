public class ForsakenGrove extends Grid{

    public ForsakenGrove() {
        super(new MantisLord(), "\uD83E\uDEB2", "\uD83D\uDFE9", player);
        addEnemy(new MantisWarrior());
        addEnemy(new MantisSentinel());
        System.out.println("Welcome to the Forsaken Grove!");
        printGrid();
        movePlayer();
    }
}
