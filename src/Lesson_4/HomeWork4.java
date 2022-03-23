package Lesson_4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HomeWork4 {

    private static final int SIZE = 5;
    private static char[][] map = new char[SIZE][SIZE];
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char DOTS_TO_WIN = 4;

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (isWin(DOT_X)) {
                System.out.println("Вы победили!!!");
                break;
            }
            if (isDraw()) {
                break;
            }
            compTurn();
            printMap();
            if (isWin(DOT_O)) {
                System.out.println("Компьютер победил!!!");
                break;
            }
            if (isDraw()) {
                break;
            }
        }
    }
    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(map[i], DOT_EMPTY);
        }
    }
    private static void printHeader() {
        for (int i = 0; i <= SIZE; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    private static void printBody() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    private static void printMap() {
        printHeader();
        printBody();
    }
    private static void humanTurn() {
        int x, y;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Введите координаты X и Y");
            x = scan.nextInt() - 1;
            y = scan.nextInt() - 1;
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
                System.out.println("Данные введены неверно");
            } else if (map[x][y] != DOT_EMPTY) {
                System.out.println("В клетке уже есть данные");
            } else {
                break;
            }
        } while (true);
        map[x][y] = DOT_X;
    }
    private static void compTurn() {
        int[] cell = getNextCellToWin(DOT_O);
        if (cell == null) {
            cell = getNextCellToWin(DOT_X);
            if (cell == null) {
                cell = getRandomlyEmptyCell();
            }
        }
        int x = cell[0];
        int y = cell[1];
        map[x][y] = DOT_O;
    }
    private static boolean isDraw() {
        if (isMapFilled()) {
            System.out.println("Ничья!!!");
            return true;
        }
        return false;
    }
    private static int[] getNextCellToWin(char symbol) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if(map[x][y] == DOT_EMPTY && isGameMoveWinning(x, y, symbol)) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }
    private static boolean isGameMoveWinning(int x, int y, char symbol) {
        map[x][y] = symbol;
        boolean result = isWin(symbol);
        map[x][y] = DOT_EMPTY;
        return result;
    }
    private static boolean checkRowsAndColumns(char symbol) {
        for (int i = 0; i < SIZE; i++) {
            int rowCounter = 0;
            int columnCounter = 0;
            for (int j = 0; j < SIZE; j++) {
                rowCounter = map[i][j] == symbol ? rowCounter + 1 : 0;
                if (map[j][i] == symbol) {
                    columnCounter = columnCounter + 1;
                } else {
                    columnCounter = 0;
                }
                if (rowCounter == DOTS_TO_WIN || columnCounter == DOTS_TO_WIN) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean checkDiagonals(char symbol) {
        int mainDiagonalCounter = 0;
        int sideDiagonalCounter = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == symbol) {
                mainDiagonalCounter++;
            }
            if (map[i][map.length - i - 1] == symbol) {
                sideDiagonalCounter++;
            }
            if (mainDiagonalCounter == DOTS_TO_WIN || sideDiagonalCounter == DOTS_TO_WIN) {
                return true;
            }
        }
        return false;
    }
    private static boolean isWin(char symbol) {
        if (checkRowsAndColumns(symbol)) {
            return true;
        } else {
            return checkDiagonals(symbol);
        }
    }
    private static int[] getRandomlyEmptyCell() {
        int x, y;
        Random rand = new Random();
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (map[x][y] != DOT_EMPTY);
        return new int[]{x, y};
    }
    private static boolean isMapFilled() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}





