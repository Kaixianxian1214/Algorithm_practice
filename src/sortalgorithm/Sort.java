package sortalgorithm;
import java.util.Arrays;


public class Sort {
    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 11, 4, 13, 11,3, 2, 1, 0, -1, -2, -3};

//        // test冒泡排序
//        bubbleSort(array);


//        // test选择排序
//        selectSort(array);

//        // test插入排序
//        insertSort(array);

//        // test shell排序
//        shellSort2(array);

//        // test 快排
//        quickSort(array, 0, array.length - 1);

//        // test 归并排序
//        int[] temp = new int[array.length];
//        mergeSort(array, 0, array.length - 1, temp);

//        // test 基数排序
//        radixSort(array);
//        System.out.println("排序后的数组为：" + Arrays.toString(array));

        // test 堆排序
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    // 堆排序
    public static void heapSort(int[] array){
        // 创建一个大顶堆
        heapInsert(array, array.length - 1);
        int len = array.length - 1;

        while(len > 0){
            // len == 1是说明数组中还有两个数无序
            swap(array, 0, len);
            len--;
            heapInsert(array, len);
        }
    }

    public static void heapInsert(int[] array, int len){
        for(int i = 0; i <= len; i++){
            int curIndex = i;
            int fatherIndex = (curIndex - 1) / 2;

            while(array[curIndex] > array[fatherIndex]){
                swap(array, curIndex, fatherIndex);
                // 当交换后，需要比较新的array[curIndex]和其父节点值的大小，直至无法交换
                curIndex = fatherIndex;
                fatherIndex = (curIndex - 1) / 2;
            }
        }
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    // 桶排序
    public static void radixSort(int[] array){

        // 创建10个桶，表示每个位
        // count记录每个桶中有多少个数
        int[][] bucket = new int[10][array.length];
        int[] bucketCount = new int[10];

        // 先要找出array中最大的数
        int max = 0;
        for(int elem :array ){
            if(max < elem){
                max = elem;
            }
        }
        // 得到最大数的位数
        int maxLen = (max+"").length();

        // 开始排序
        for(int i = 0; i < maxLen; i++){
            for(int elem : array){
                // 得到每个数在当前位上的数
                int numLocal = elem / (int)Math.pow(10, i) % 10;

                // 往桶里面加数
                bucket[numLocal][bucketCount[numLocal]] = elem;
                bucketCount[numLocal]++;
            }
            // 放完后再将其返还到array中
            int index = 0;
            for(int j = 0; j < 10; j++){
                if(bucketCount[j] != 0){
                    for(int k = 0; k < bucketCount[j]; k++){
                        array[index++] = bucket[j][k];
                    }
                }
                // 放回到array后需将count置为0
                bucketCount[j] = 0;
            }
        }
    }


    // 归并排序
    // 合+并的方法
    public static void mergeSort(int[] array, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            // 左右递归分解
            mergeSort(array, left, mid, temp);
            mergeSort(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }
    // 合并的方法
    // left为左边索引，mid为中间索引即右半部分开头位置，right为右边索引，temp为中转数组
    public static void merge(int[] array, int left, int mid, int right,int[] temp){
        int i = left;
        int j = mid + 1;
        int index = 0;

        while(i <= mid && j <= right){
            if(array[i] <= array[j]) {
                // 将左边的加入temp[]
                temp[index] = array[i];
                index++;
                i++;
            }else {
                // 将右边的加入temp[]
                temp[index] = array[j];
                index++;
                j++;
            }
        }

        // 将剩下的未导入的一次性加入temp[]
        while(i <= mid){
            temp[index] = array[i];
            index++;
            i++;
        }
        while(j <= right){
            temp[index] = array[j];
            index++;
            j++;
        }

        // 将temp 中的数拷贝回array
        index = 0;
        i = left;
        while(i <= right){
            array[i] = temp[index];
            i++;
            index++;
        }
    }

    // 快排
    public static void quickSort(int[] array, int l, int r){
        if(l < r){
            // 当l < r才会进行排序
            int i = l;
            int j = r;
            int base = array[l];         // 默认选择第一个数为基准

            while(i < j){
                // 先从右到左找第一个小于基准的数
                while(i < j && array[j] >= base){
                    j--;
                }
                if(i < j){
                    // 当i < j才进行交换，若等于则i,j重合
                    array[i++] = array[j];
                }

                // 从做到右找第一个大于base的数
                while(i < j && array[i] <= base){
                    i++;
                }
                if(i < j){
                    // 当i < j才进行交换，若等于则i,j重合
                    array[j--] = array[i];
                }
            }
            // 此时i=j指向同一个位置
            array[i] = base;
            // 左右递归
            quickSort(array, l, i - 1);
            quickSort(array, i + 1, r);
        }
    }

    // 希尔排序-插入式
    public static void shellSort2(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int insert = array[i];
                int index = i - gap;
                while (index >= 0 && array[index] > insert) {
                    array[index + gap] = array[index];
                    index -= gap;
                }
                // 找到插入的位置
                array[index + gap] = insert;
            }
        }
    }

    // 希尔排序-交换式
    public static void shellSort1(int[] array) {
        int temp;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                // i会遍历后面i - gap个数，来调整位置
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 从后往前策略
                    if (array[j] > array[j + gap]) {
                        // 当后面的小于前面的
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
    }

    // 直接插入排序
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insert = array[i];
            int index = i - 1;
            while (index >= 0 && insert < array[index]) {
                array[index + 1] = array[index];
                index--;
            }
            // 当退出循环，说明找到了插入的位置
            array[index + 1] = insert;
        }
    }

    // 选择排序
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    index = j;
                }
            }
            // 遍历结束，进行交换
            if (index != i) {   // 当最小的不在开头处时，才进行交换
                array[index] = array[i];
                array[i] = min;
            }
        }
    }


    // 冒泡排序
    public static void bubbleSort(int[] array) {
        int temp;
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            // j = len - 1 - i
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // 如果进行交换则flag = true】
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if (!flag) {      // 冒泡排序的优化
                // 未经过替换，跳出循环
                break;
            } else {
                flag = false;
            }
        }
    }
}
