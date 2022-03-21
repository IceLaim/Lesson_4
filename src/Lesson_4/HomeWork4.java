package Lesson_4;

public class HomeWork4 {

    public static final int SIZE = 3;
    public static final char[][] map = new char[SIZE][SIZE];
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_EMPTY = '•';



    public static void main(String[] args) {
        initMap();
    }
    public static void initMap(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
            }
        }

    }




