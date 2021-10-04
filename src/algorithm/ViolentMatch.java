package algorithm;

public class ViolentMatch {
    public static void main(String[] args) {
        System.out.println(solution("ȫ������ĭ", "��ĭ2"));

    }
    public static int solution(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        // i�� j�ֱ�ָ��s1��s2��ָ��
        int i = 0;
        int j = 0;
        while(i < s1.length && j < s2.length){
            if(s1[i] == s2[j]){
                // ƥ��ɹ�
                i++;
                j++;
            }else{
                // δƥ��ɹ���iת���ƥ��ǰ��i+1��j��Ϊ0
                i = i - (j - 1);
                j = 0;
            }
        }

        // �ж��Ƿ��ҵ�
        if(j == s2.length){
            // �ҵ�������str1��index
            return i - j;
        }else{
            //δ�ҵ�������-1
            return -1;
        }
    }
}
