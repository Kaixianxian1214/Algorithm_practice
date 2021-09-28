package recursion;

import java.util.Arrays;

public class Queen {
    public static void main(String[] args) {
        // arr[i] = val表示，第i+1个皇后摆在第val+1列的位置
        int[] arr = new int[8];
        check(arr, 0);
    }

    // 防止queen
    public static void check(int[] arr, int n) {
        if (n == 8) {
            // 当n=9时，表示已经完成所有皇后的放置
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = 0; i < 8; i++) {
            // 将其放置在第i列
            arr[n] = i;
            // arr[i] == arr[n]表示在同一列
            // Math.abs(n-i) == Math.abs(arr[n]-arr[i])表示在同一个斜线上
            if (judge(arr, n)){
                // 不冲突的话， 继续放置第n+1个
                check(arr, n + 1);
            }
        }
    }

    // 判断新加第n个会不会和前面加的所有的冲突
    public static boolean judge(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }
        return true;
    }

}
