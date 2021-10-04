package algorithm;

import java.util.Arrays;

public class Dynamic {
    public static void main(String[] args) {
        solution(4);
    }

    // �������Ϊbag���������
    public static void solution(int bagWeight) {
        // ǰ�油��0����Ϊ�˷���1��Ӧ��һ����Ʒ���Դ�����
        int[] weight = {0, 1, 4, 3};      // ������Ʒ��Ӧ�ļ�ֵ
        int[] value = {0, 1500, 3000, 2000};     // ������Ʒ��Ӧ������
        int[][] v = new int[value.length][bagWeight + 1];   // ��Ҫά���Ķ�ά����
        // path�����洢��i�е�j����װ�뷽����1��2��3����Ʒ�ĸ���
        int[][][] path = new int[value.length][bagWeight + 1][4];

        // �������εĶ�̬�滮
        for (int i = 1; i < value.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (weight[i] > j) {
                    // ������Ʒ������С�ڱ�������ʱ��������ֵ��·��
                    v[i][j] = v[i - 1][j];
                    for(int k = 1; k < 4; k++){
                        path[i][j][k] = path[i - 1][j][k];
                    }
                } else {
                    // ȡ����ֵ����װ�����
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
                        // ����i����Ʒװ�룬��ʱ��i����Ʒ������Ϊ1
                        path[i][j][i] = 1;
                    }
                }
            }
        }

        // ���������̬�滮�������
        for (int i = 0; i < value.length; i++) {
            System.out.println(Arrays.toString(v[i]));
        }

        // ������й滮�ĸ�����Ʒ������
        for (int i = 1; i < 4; i++) {
            for(int j = 1; j < bagWeight + 1; j++){
                System.out.println(Arrays.toString(path[i][j]));
            }
        }
    }
}
