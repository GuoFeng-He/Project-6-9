public class αβίωσητέφρα extends Grid{

    public αβίωσητέφρα() {
        super(new Yarthul(), "\uD83E\uDD9E", "🟥", player);
        addEnemy(new γκόλεμ());
        addEnemy(new φλογοβόλος());
        printGrid();
        movePlayer();
    }

}
