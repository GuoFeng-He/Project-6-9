import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Grid {
    private String[][] grid;
    private int[] playerPos;
    private int[] exitPos;
    private static int worldNum = 1;
    private String enemyEmoji;
    private ArrayList<Enemy> worldEnemies;
    private final String playerEmoji = "🤡";
    private final String exitEmoji = "\uD83D\uDFE9";
    private Boss boss;

    public Grid(Boss boss,String enemyEmoji, String tileColor) {
        grid = new String[8][8];
        playerPos = new int[2];
        exitPos = new int[2];
        this.boss = boss;
        this.enemyEmoji = enemyEmoji;
        createGrid(tileColor);
        printGrid();
        movePlayer();
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
                if (direction == 0 && playerPos[0] + 1 < grid.length && grid[playerPos[0] + 1][playerPos[1]].equals("⬛")) {
                    grid[playerPos[0] + 1][playerPos[1]] = enemyEmoji; // down
                    System.out.println("You have encountered a monster!");
                } else if (direction == 1 && playerPos[0] - 1 >= 0 && grid[playerPos[0] - 1][playerPos[1]].equals("⬛")) {
                    grid[playerPos[0] - 1][playerPos[1]] = enemyEmoji; // up
                    System.out.println("You have encountered a monster!");
                } else if (direction == 2 && playerPos[1] + 1 < grid.length && grid[playerPos[0]][playerPos[1] + 1].equals("⬛")) {
                    grid[playerPos[0]][playerPos[1] + 1] = enemyEmoji; // right
                    System.out.println("You have encountered a monster!");
                } else if (direction == 3 && playerPos[1] - 1 >= 0 && grid[playerPos[0]][playerPos[1] - 1].equals("⬛")) {
                    grid[playerPos[0]][playerPos[1] - 1] = enemyEmoji; // left
                    System.out.println("You have encountered a monster!");
                }
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
        if (grid[playerPos[0] - 1][playerPos[1]].equals(enemyEmoji)) {
            System.out.println("You have entered battle");
            // combat here
            return;
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
        if (grid[playerPos[0] + 1][playerPos[1]].equals(enemyEmoji)) {
            System.out.println("You have entered battle");
            // combat here
            return;
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
        if (grid[playerPos[0]][playerPos[1] - 1].equals(enemyEmoji)) {
            System.out.println("You have entered battle");
            // combat here
            return;
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
        if (grid[playerPos[0]][playerPos[1] + 1].equals(enemyEmoji)) {
            System.out.println("You have entered battle");
            // combat here
            return;
        }
        if (playerPos[1] < grid[0].length-1) {
            grid[playerPos[0]][playerPos[1]] = "⬜";
            playerPos[1]++;
            grid[playerPos[0]][playerPos[1]] = playerEmoji;
        }
    }
    public boolean reachEnd(){
        if (playerPos[0] == exitPos[0] && playerPos[1] == exitPos[1]){
            return true;
        }
        return false;
    }
}