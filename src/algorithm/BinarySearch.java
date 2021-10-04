package algorithm;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 67, 100};
        // test 二分查找非递归实现
        System.out.println(binarySearch(arr, 101));
    }

    // 二分查找 --> 非递归实现
    public static int binarySearch(int[] array, int target){
        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(target == array[mid]){
                return mid;
            }else if(target < array[mid]){
                // 左边二分查找
                right = mid - 1;
            }else{
                // 右边二分查找
                left = mid + 1;
            }
        }
        // 未找到
        return -1;
    }
}
