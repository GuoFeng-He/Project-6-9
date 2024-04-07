import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Grid {
    public static int worldNum = 1;
    private ArrayList<Enemy> worldEnemies;

    private String[][] grid;
    private int[] playerPos;
    private int[] exitPos;
    private int[] keyPos;

    private String enemyEmoji;
    private final String playerEmoji = "🤡";
    private final String exitEmoji = "\uD83D\uDFE9";
    private final String keyEmoji = "\uD83D\uDD11";
    private String tileColor;
    private Boss boss;
    private boolean hasKey;
    private boolean worldBeaten;

    public Grid(Boss boss, String enemyEmoji, String tileColor) {
        worldEnemies = new ArrayList<>();
        grid = new String[8][8];
        playerPos = new int[2];
        exitPos = new int[2];
        keyPos = new int[2];
        this.boss = boss;
        this.enemyEmoji = enemyEmoji;
        this.tileColor = tileColor;
        createGrid(tileColor);
    }

    public void addEnemy(Enemy e) {
        worldEnemies.add(e);
    }

    public void movePlayer() {
        while (!reachEnd()) {
            System.out.print("Enter a direction (w/a/s/d): ");
            Scanner scan = new Scanner(System.in);
            movePlayer(scan.nextLine());
            if (Math.random() > 0.5) {
                int direction = (int) (Math.random() * 4);
                if (direction == 0 && playerPos[0] + 1 < grid.length && grid[playerPos[0] + 1][playerPos[1]].equals(tileColor)) {
                    grid[playerPos[0] + 1][playerPos[1]] = enemyEmoji; // down
                    System.out.println("You have encountered a monster!");
                } else if (direction == 1 && playerPos[0] - 1 >= 0 && grid[playerPos[0] - 1][playerPos[1]].equals(tileColor)) {
                    grid[playerPos[0] - 1][playerPos[1]] = enemyEmoji; // up
                    System.out.println("You have encountered a monster!");
                } else if (direction == 2 && playerPos[1] + 1 < grid.length && grid[playerPos[0]][playerPos[1] + 1].equals(tileColor)) {
                    grid[playerPos[0]][playerPos[1] + 1] = enemyEmoji; // right
                    System.out.println("You have encountered a monster!");
                } else if (direction == 3 && playerPos[1] - 1 >= 0 && grid[playerPos[0]][playerPos[1] - 1].equals(tileColor)) {
                    grid[playerPos[0]][playerPos[1] - 1] = enemyEmoji; // left
                    System.out.println("You have encountered a monster!");
                }
            }
            if (playerPos[0] == keyPos[0] && playerPos[1] == keyPos[1]) {
                System.out.println("YOu have found the key!");
                hasKey = true;
            }
            printGrid();
        }
    }

    public void movePlayer(String direction) {
        switch (direction.toLowerCase()) {
            case "w" -> moveUp();
            case "s" -> moveDown();
            case "a" -> moveLeft();
            case "d" -> moveRight();
            default -> System.out.println("You have entered an invalid response. Try again.");
        }
    }

    public void createGrid(String tileColor) {
        for (String[] strings : grid) {
            Arrays.fill(strings, tileColor);
        }
        int row = (int) (Math.random() * grid.length);
        int col = (int) (Math.random() * grid.length);
        playerPos[0] = row;
        playerPos[1] = col;
        grid[row][col] = playerEmoji;

        int exitRow = (int) (Math.random() * grid.length);
        int exitCol = (int) (Math.random() * grid.length);
        exitPos[0] = exitRow;
        exitPos[1] = exitCol;
        grid[exitRow][exitCol] = exitEmoji;

        int keyRow = (int) (Math.random() * grid.length);
        int keyCol = (int) (Math.random() * grid.length);
        keyPos[0] = keyRow;
        keyPos[1] = keyCol;
    }

    public void printGrid() {
        System.out.println("--------------------------------------");
        for (String[] row : grid) {
            for (String column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
    }

    public void moveUp() {
        if (playerPos[0] == 0) {
            System.out.println("You can't go up!");
            return;
        }
        String gridAbove = grid[playerPos[0] - 1][playerPos[1]];
        if (gridAbove.equals(enemyEmoji)) {
            System.out.println("You have entered battle");
            // combat here
            return;
        } else if (gridAbove.equals(exitEmoji)) {
            if (hasKey) {
                System.out.println("You have survived this room! Congrats!");
                worldBeaten = true;
            } else {
                System.out.println("Please return when you have the key.");
            }
        }
        grid[playerPos[0]][playerPos[1]] = "⬜";
        playerPos[0]--;
        grid[playerPos[0]][playerPos[1]] = playerEmoji;
    }

    public void moveDown() {
        if (playerPos[0] == grid.length - 1) {
            System.out.println("You can't go any further down!");
            return;
        }
        String gridBelow = grid[playerPos[0] + 1][playerPos[1]];
        if (gridBelow.equals(enemyEmoji)) {
            System.out.println("You have entered battle");
            // combat here
            return;
        } else if (gridBelow.equals(exitEmoji)) {
            if (hasKey) {
                System.out.println("You have survived this room! Congrats!");
                worldBeaten = true;
            } else {
                System.out.println("Please return when you have the key.");
            }
        }
        if (playerPos[0] < grid.length-1) {
            grid[playerPos[0]][playerPos[1]] = "⬜";
            playerPos[0]++;
            grid[playerPos[0]][playerPos[1]] = playerEmoji;
        }
    }

    public void moveLeft() {
        if (playerPos[1] == 0) {
            System.out.println("You can't go left!");
            return;
        }
        String gridLeft = grid[playerPos[0]][playerPos[1] - 1];
        if (gridLeft.equals(enemyEmoji)) {
            System.out.println("You have entered battle");
            // combat here
            return;
        } else if (gridLeft.equals(exitEmoji)) {
            if (hasKey) {
                System.out.println("You have survived this room! Congrats!");
                worldBeaten = true;
            } else {
                System.out.println("Please return when you have the key.");
            }
        }
        grid[playerPos[0]][playerPos[1]] = "⬜";
        playerPos[1]--;
        grid[playerPos[0]][playerPos[1]] = playerEmoji;
    }

    public void moveRight() {
        if (playerPos[1] == grid[0].length - 1) {
            System.out.println("You can't go any further right!");
            return;
        }
        String gridRight = grid[playerPos[0]][playerPos[1] + 1];
        if (gridRight.equals(enemyEmoji)) {
            System.out.println("You have entered battle");
            // combat here
            return;
        } else if (gridRight.equals(exitEmoji)) {
            if (hasKey) {
                System.out.println("You have survived this room! Congrats!");
                worldBeaten = true;
            } else {
                System.out.println("Please return when you have the key.");
            }
        }
        if (playerPos[1] < grid[0].length-1) {
            grid[playerPos[0]][playerPos[1]] = "⬜";
            playerPos[1]++;
            grid[playerPos[0]][playerPos[1]] = playerEmoji;
        }
    }
    public boolean reachEnd(){
        if (playerPos[0] == exitPos[0] && playerPos[1] == exitPos[1]){
            if(hasKey) {
                System.out.println("You unlocked the door!");
                if (!boss.isDead()) {
                    System.out.println("You initialized a boss fight");
//                    Combat.bossFight();
                } else {
                    worldNum++;
                    return true;
                }
            }else{
                System.out.println("Find the missing key");
            }
        }
        return false;
    }
}