package sortalgorithm;
import java.util.Arrays;


public class Sort {
    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 11, 4, 13, 11,3, 2, 1, 0, -1, -2, -3};

//        // testð������
//        bubbleSort(array);


//        // testѡ������
//        selectSort(array);

//        // test��������
//        insertSort(array);

//        // test shell����
//        shellSort2(array);

//        // test ����
//        quickSort(array, 0, array.length - 1);

//        // test �鲢����
//        int[] temp = new int[array.length];
//        mergeSort(array, 0, array.length - 1, temp);

//        // test ��������
//        radixSort(array);
//        System.out.println("����������Ϊ��" + Arrays.toString(array));

        // test ������
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    // ������
    public static void heapSort(int[] array){
        // ����һ���󶥶�
        heapInsert(array, array.length - 1);
        int len = array.length - 1;

        while(len > 0){
            // len == 1��˵�������л�������������
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
                // ����������Ҫ�Ƚ��µ�array[curIndex]���丸�ڵ�ֵ�Ĵ�С��ֱ���޷�����
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


    // Ͱ����
    public static void radixSort(int[] array){

        // ����10��Ͱ����ʾÿ��λ
        // count��¼ÿ��Ͱ���ж��ٸ���
        int[][] bucket = new int[10][array.length];
        int[] bucketCount = new int[10];

        // ��Ҫ�ҳ�array��������
        int max = 0;
        for(int elem :array ){
            if(max < elem){
                max = elem;
            }
        }
        // �õ��������λ��
        int maxLen = (max+"").length();

        // ��ʼ����
        for(int i = 0; i < maxLen; i++){
            for(int elem : array){
                // �õ�ÿ�����ڵ�ǰλ�ϵ���
                int numLocal = elem / (int)Math.pow(10, i) % 10;

                // ��Ͱ�������
                bucket[numLocal][bucketCount[numLocal]] = elem;
                bucketCount[numLocal]++;
            }
            // ������ٽ��䷵����array��
            int index = 0;
            for(int j = 0; j < 10; j++){
                if(bucketCount[j] != 0){
                    for(int k = 0; k < bucketCount[j]; k++){
                        array[index++] = bucket[j][k];
                    }
                }
                // �Żص�array���轫count��Ϊ0
                bucketCount[j] = 0;
            }
        }
    }


    // �鲢����
    // ��+���ķ���
    public static void mergeSort(int[] array, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            // ���ҵݹ�ֽ�
            mergeSort(array, left, mid, temp);
            mergeSort(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }
    // �ϲ��ķ���
    // leftΪ���������midΪ�м��������Ұ벿�ֿ�ͷλ�ã�rightΪ�ұ�������tempΪ��ת����
    public static void merge(int[] array, int left, int mid, int right,int[] temp){
        int i = left;
        int j = mid + 1;
        int index = 0;

        while(i <= mid && j <= right){
            if(array[i] <= array[j]) {
                // ����ߵļ���temp[]
                temp[index] = array[i];
                index++;
                i++;
            }else {
                // ���ұߵļ���temp[]
                temp[index] = array[j];
                index++;
                j++;
            }
        }

        // ��ʣ�µ�δ�����һ���Լ���temp[]
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

        // ��temp �е���������array
        index = 0;
        i = left;
        while(i <= right){
            array[i] = temp[index];
            i++;
            index++;
        }
    }

    // ����
    public static void quickSort(int[] array, int l, int r){
        if(l < r){
            // ��l < r�Ż��������
            int i = l;
            int j = r;
            int base = array[l];         // Ĭ��ѡ���һ����Ϊ��׼

            while(i < j){
                // �ȴ��ҵ����ҵ�һ��С�ڻ�׼����
                while(i < j && array[j] >= base){
                    j--;
                }
                if(i < j){
                    // ��i < j�Ž��н�������������i,j�غ�
                    array[i++] = array[j];
                }

                // ���������ҵ�һ������base����
                while(i < j && array[i] <= base){
                    i++;
                }
                if(i < j){
                    // ��i < j�Ž��н�������������i,j�غ�
                    array[j--] = array[i];
                }
            }
            // ��ʱi=jָ��ͬһ��λ��
            array[i] = base;
            // ���ҵݹ�
            quickSort(array, l, i - 1);
            quickSort(array, i + 1, r);
        }
    }

    // ϣ������-����ʽ
    public static void shellSort2(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int insert = array[i];
                int index = i - gap;
                while (index >= 0 && array[index] > insert) {
                    array[index + gap] = array[index];
                    index -= gap;
                }
                // �ҵ������λ��
                array[index + gap] = insert;
            }
        }
    }

    // ϣ������-����ʽ
    public static void shellSort1(int[] array) {
        int temp;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                // i���������i - gap������������λ��
                for (int j = i - gap; j >= 0; j -= gap) {
                    // �Ӻ���ǰ����
                    if (array[j] > array[j + gap]) {
                        // �������С��ǰ���
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
    }

    // ֱ�Ӳ�������
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insert = array[i];
            int index = i - 1;
            while (index >= 0 && insert < array[index]) {
                array[index + 1] = array[index];
                index--;
            }
            // ���˳�ѭ����˵���ҵ��˲����λ��
            array[index + 1] = insert;
        }
    }

    // ѡ������
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
            // �������������н���
            if (index != i) {   // ����С�Ĳ��ڿ�ͷ��ʱ���Ž��н���
                array[index] = array[i];
                array[i] = min;
            }
        }
    }


    // ð������
    public static void bubbleSort(int[] array) {
        int temp;
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            // j = len - 1 - i
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // ������н�����flag = true��
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if (!flag) {      // ð��������Ż�
                // δ�����滻������ѭ��
                break;
            } else {
                flag = false;
            }
        }
    }
}
