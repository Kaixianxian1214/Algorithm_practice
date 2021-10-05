package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Horse {
    public static void main(String[] args) {
        solution(3, 4, 1);
        for(int[] ele : chessBoard){
            System.out.println(Arrays.toString(ele));
        }
    }

    public static int X = 8;     // ��ʾ���̵���
    public static int Y = 8;     // ��ʾ���̵���
    public static boolean[][] isVisited = new boolean[X][Y];    // ���������ĳ���Ƿ񱻷���
    public static int[][] chessBoard = new int[X][Y];
    public static boolean isFinished;

    //
    public static void solution(int row, int col, int step){
        // ���Ȼ�õ�ǰλ���ܹ���ͨ����һ��
        chessBoard[row][col] = step;
        isVisited[row][col] = true;
        ArrayList<Point> next = next(new Point(row, col));
        sort(next);
        while(!next.isEmpty()){
            Point nextPoint = next.remove(0);
            if(!isVisited[nextPoint.x][nextPoint.y]){
                solution(nextPoint.x, nextPoint.y, step + 1);
            }
        }
        if(step < X*Y && !isFinished){
            // ����
            chessBoard[row][col] = 0;
            isVisited[row][col] = false;
        }else{
            isFinished = true;
        }
    }

    // ���ص�ǰ���ܹ���ͨ�ĵ�
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> pointList = new ArrayList<>();
        if(curPoint.x + 2 < Y && curPoint.y + 1 < X){
            pointList.add(new Point(curPoint.x + 2, curPoint.y + 1));
        }
        if(curPoint.x + 1 < Y && curPoint.y + 2 < X){
            pointList.add(new Point(curPoint.x + 1, curPoint.y + 2));
        }
        if(curPoint.x - 2 >= 0 && curPoint.y + 1 < X){
            pointList.add(new Point(curPoint.x - 2, curPoint.y + 1));
        }
        if(curPoint.x - 1 >= 0 && curPoint.y + 2 < X){
            pointList.add(new Point(curPoint.x - 1, curPoint.y + 2));
        }
        if(curPoint.x - 2 >= 0 && curPoint.y - 1 >= 0){
            pointList.add(new Point(curPoint.x - 2, curPoint.y - 1));
        }
        if(curPoint.x - 1 >= 0 && curPoint.y - 2 >= 0){
            pointList.add(new Point(curPoint.x - 1, curPoint.y - 2));
        }
        if(curPoint.x + 2 < Y && curPoint.y - 1 >= 0){
            pointList.add(new Point(curPoint.x + 2, curPoint.y - 1));
        }
        if(curPoint.x + 1 < Y && curPoint.y - 2 >= 0){
            pointList.add(new Point(curPoint.x + 1, curPoint.y - 2));
        }
        return pointList;
    }

    public static void sort(ArrayList<Point> pointList){
        pointList.sort(Comparator.comparingInt(o -> next(o).size()));
    }
}

class Point{
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}