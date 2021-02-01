package Lesson_5;

public class Main {
    public static int[][] moves;
    private static int size = 8;

    public static void main(String[] args) {
        int[][] desk = new int[size][size];
        //  checkQueen(desk, 1, 1, 1);
        //  printDesk(desk);
//        printDesk(steps(1,1,4));
//        printDesk(steps(2,2,8));
//        checkQueen(desk, steps(1, 1, 4));
//        checkQueen(desk, steps(2, 2, 8));
//        printDesk(desk);
        king( desk,0,0,1);
        printDesk(desk);

    }
    public static int [][] kingSteps = {
            {0,1},{1,0}
    };
    private static boolean isPossible(int[][] desk, int x, int y) {
        return x >= 0 && x < desk[0].length &&
                y >= 0 && y < desk.length &&
                desk[y][x] == 0;
    }
    public static boolean king(int[][] desk, int x, int y, int move){
        if(move >= desk.length+ desk[0].length ) return true;
        int nextX;
        int nextY;
        for (int i = 0; i < kingSteps.length - 1; i++) {
// no ideas
            if (isPossible(desk, nextX, nextY) && king(desk, nextX, nextY, move + 1))
                return true;
        }
        desk[y][x] = 0;
        return false;
    }
    public static int[][] steps(int x, int y, int index) {
        int[][] moves = new int[size][size];
        for (int i = 0; i < moves.length; i++) {
            moves[i][y] = index;
            moves[x][i] = index;
        }
      // diagonals

//        for (int i = 0; i < moves.length; i++) {
//           moves[0][y+i]=1;
//
//        }
        return moves;
    }

    public static void checkQueen(int[][] desk, int[][] steps) {
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk.length; j++) {
                if (desk[i][j] == 0) {
                    desk[i][j] = steps[i][j];
                } else {
                    desk[i][j] = steps[i][j] + desk[i][j];
                }
            }
        }

    }

    private static void printDesk(int[][] desk) {
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[i].length; j++) {
                System.out.printf("%3d", desk[i][j]);
            }
            System.out.println();
        }
    }
}
