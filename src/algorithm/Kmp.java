package algorithm;

public class Kmp {
    public static void main(String[] args) {
        System.out.println(kmpSolution("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));
    }
    public static int kmpSolution(String str1, String str2){
        int[] next = kmpNext(str2);
        for(int i = 0, j = 0; i < str1.length(); i++){
            if (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }


    // 求next数组
    public static int[] kmpNext(String arr){
        int[] next = new int[arr.length()];
        next[0] = 0;
        for(int i = 1, j = 0; i < arr.length(); i++){
            while(j > 0 && arr.charAt(j) != arr.charAt(i)){
                j = next[j - 1];
            }
            if(arr.charAt(j) == arr.charAt(i)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
