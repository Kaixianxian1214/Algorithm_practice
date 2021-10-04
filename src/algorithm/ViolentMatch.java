package algorithm;

public class ViolentMatch {
    public static void main(String[] args) {
        System.out.println(solution("全都是泡沫", "泡沫2"));

    }
    public static int solution(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        // i， j分别指向s1、s2的指针
        int i = 0;
        int j = 0;
        while(i < s1.length && j < s2.length){
            if(s1[i] == s2[j]){
                // 匹配成功
                i++;
                j++;
            }else{
                // 未匹配成功，i转变成匹配前的i+1，j变为0
                i = i - (j - 1);
                j = 0;
            }
        }

        // 判断是否找到
        if(j == s2.length){
            // 找到，返回str1的index
            return i - j;
        }else{
            //未找到，返回-1
            return -1;
        }
    }
}
