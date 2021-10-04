package algorithm;

import java.util.Arrays;

public class Dynamic {
    public static void main(String[] args) {
        solution(4);
    }

    // 传入参数为bag的最大容量
    public static void solution(int bagWeight) {
        // 前面补充0，是为了方便1对应第一个商品，以此类推
        int[] weight = {0, 1, 4, 3};      // 三个商品对应的价值
        int[] value = {0, 1500, 3000, 2000};     // 三个商品对应的重量
        int[][] v = new int[value.length][bagWeight + 1];   // 需要维护的二维数组
        // path用来存储第i行第j列中装入方案第1、2、3种商品的个数
        int[][][] path = new int[value.length][bagWeight + 1][4];

        // 进行三次的动态规划
        for (int i = 1; i < value.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (weight[i] > j) {
                    // 当该物品的质量小于背包容量时，更新数值和路径
                    v[i][j] = v[i - 1][j];
                    for(int k = 1; k < 4; k++){
                        path[i][j][k] = path[i - 1][j][k];
                    }
                } else {
                    // 取更大值更新装入策略
                    // v[i][j] = Math.max(v[i - 1][j], value[i] + v[i - 1][j - weight[i]]);
                    if (v[i - 1][j] > value[i] + v[i - 1][j - weight[i]]) {
                        v[i][j] = v[i - 1][j];
                        for(int k = 1; k < 4; k++){
                            path[i][j][k] = path[i - 1][j][k];
                        }
                    } else {
                        v[i][j] = value[i] + v[i - 1][j - weight[i]];
                        for(int k = 1; k < 4; k++){
                            path[i][j][k] = path[i - 1][j][k];
                        }
                        // 将第i个物品装入，此时第i个物品数量计为1
                        path[i][j][i] = 1;
                    }
                }
            }
        }

        // 输出整个动态规划后的数组
        for (int i = 0; i < value.length; i++) {
            System.out.println(Arrays.toString(v[i]));
        }

        // 输出所有规划的各个商品的数量
        for (int i = 1; i < 4; i++) {
            for(int j = 1; j < bagWeight + 1; j++){
                System.out.println(Arrays.toString(path[i][j]));
            }
        }
    }
}
