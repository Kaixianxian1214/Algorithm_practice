package algorithm;

public class HanoiTower {
    public static void main(String[] args) {
        solution(5, 'A', 'B', 'C');
    }
    
    // ����n���̴�a�ƶ���c���м����b
    public static void solution(int n, char a, char b, char c){
        // �����һ�����ӵ����ֱ��A-->C
        if(n == 1){
            System.out.println("��1���̣�"+a+"-->"+c);
            return;
        }
        // �����ڵ���2���Ƚ��������A-->B����������A-->C����������B-->C
        solution(n - 1, a, c, b);
        System.out.println("��" + n + "���̣�"+a+"-->"+c);
        solution(n-1, b, a, c);
    }
}
