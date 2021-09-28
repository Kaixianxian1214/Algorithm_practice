package recursion;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        initMaze(map);
        printMaze(map);
        findWay(map, 1, 1);
        printMaze(map);
    }

    // 寻找通路
    // 当map[i][j]的值为：0 --> 没有走过，1 --> 墙，2 --> 通路，3 --> 死路
    // 寻找方向为下右左上
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {       // 当此点未走过时
                // 假设该条路是通路，置2
                map[i][j] = 2;
                if (findWay(map, i + 1, j)) {
                    //向下
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    // 向右
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    // 向左
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    // 向上
                    return true;
                } else {      // 则该路走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {        // 此点已经走过时
                return false;
            }
        }
    }

    public static void initMaze(int[][] map) {
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
    }

    public static void printMaze(int map[][]) {
        for (int[] i : map) {
            System.out.println(Arrays.toString(i));
        }
    }
}
