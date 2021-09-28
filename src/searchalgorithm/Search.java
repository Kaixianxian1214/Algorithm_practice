package searchalgorithm;

import java.util.ArrayList;

public class Search {
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 17, 99, 102};

//        // test 二分查找--非递归--只找一个
//        System.out.println("所要查找的index为："+binarySearch1(array, 0, array.length - 1, -1));

        // test 二分查找--非递归--找出所有的index
        System.out.println("所要查找的index为：" + binarySearch2(array, 0, array.length - 1, 11));
    }

    // 二分查找--非递归方法--只找一个
    public static int binarySearch1(int[] array, int left, int right, int findVal) {
        if (left > right) {
            // 未找到
            return -1;
        } else {
            int mid = left + (right - left) / 2;
            if (array[mid] == findVal) {
                return mid;
            } else if (array[mid] > findVal) {
                return binarySearch1(array, left, mid - 1, findVal);
            } else {
                return binarySearch1(array, mid + 1, right, findVal);
            }
        }
    }

    // 二分查找--非递归--找出所有的index
    // 存在一个问题，这种方法找出来的index并不是按照index从小到大排序的
    public static ArrayList<Integer> binarySearch2(int[] array, int left, int right, int findVal) {
        if (left > right) {
            // 未找到
            return new ArrayList<>();
        } else {
            int mid = left + (right - left) / 2;
            if (array[mid] == findVal) {
                // 找到了
                ArrayList<Integer> resList = new ArrayList<>();
                int index = mid - 1;
                while (index >= 0 && array[index] == findVal) {
                    resList.add(index);
                    index--;
                }
                resList.add(mid);
                index = mid + 1;
                while (index < array.length && array[index] == findVal) {
                    resList.add(index);
                    index++;
                }
                return resList;
            } else if (array[mid] > findVal) {
                // 在左边查找
                return binarySearch2(array, left, mid - 1, findVal);
            } else {
                // 在右边查找
                return binarySearch2(array, mid + 1, right, findVal);
            }
        }
    }
}
