package algorithm;

public class HanoiTower {
    public static void main(String[] args) {
        solution(5, 'A', 'B', 'C');
    }
    
    // 将第n个盘从a移动到c，中间借助b
    public static void solution(int n, char a, char b, char c){
        // 如果是一个盘子的情况直接A-->C
        if(n == 1){
            System.out.println("第1个盘："+a+"-->"+c);
            return;
        }
        // 当大于等于2，先将上面的盘A-->B，在最下面A-->C，最后上面的B-->C
        solution(n - 1, a, c, b);
        System.out.println("第" + n + "个盘："+a+"-->"+c);
        solution(n-1, b, a, c);
    }
}
