package searchalgorithm;

import java.util.ArrayList;

public class Search {
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 17, 99, 102};

//        // test ���ֲ���--�ǵݹ�--ֻ��һ��
//        System.out.println("��Ҫ���ҵ�indexΪ��"+binarySearch1(array, 0, array.length - 1, -1));

        // test ���ֲ���--�ǵݹ�--�ҳ����е�index
        System.out.println("��Ҫ���ҵ�indexΪ��" + binarySearch2(array, 0, array.length - 1, 11));
    }

    // ���ֲ���--�ǵݹ鷽��--ֻ��һ��
    public static int binarySearch1(int[] array, int left, int right, int findVal) {
        if (left > right) {
            // δ�ҵ�
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

    // ���ֲ���--�ǵݹ�--�ҳ����е�index
    // ����һ�����⣬���ַ����ҳ�����index�����ǰ���index��С���������
    public static ArrayList<Integer> binarySearch2(int[] array, int left, int right, int findVal) {
        if (left > right) {
            // δ�ҵ�
            return new ArrayList<>();
        } else {
            int mid = left + (right - left) / 2;
            if (array[mid] == findVal) {
                // �ҵ���
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
                // ����߲���
                return binarySearch2(array, left, mid - 1, findVal);
            } else {
                // ���ұ߲���
                return binarySearch2(array, mid + 1, right, findVal);
            }
        }
    }
}
