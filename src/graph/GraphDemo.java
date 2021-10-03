package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphDemo {
    public static void main(String[] args) {
        Graph graph = initGraph();
//        // test 邻接矩阵
//        graph.printMatrix();

        // test 深度优先遍历
        graph.dfs(0);

//        // test 广度优先遍历
//        graph.bfs(0);

    }

    public static Graph initGraph() {
        Graph graph = new Graph(8);
        // 插入结点
        String[] VertexValue = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (String vertex : VertexValue) {
            graph.insertNode(vertex);
        }
        // 插入边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        return graph;
    }
}

class Graph {
    ArrayList<String> vertexList;
    int[][] edges;
    int numOfEdges;
    boolean[] visited;   // 不赋初值为全false

    // 广度优先遍历
    public void bfs(int i) {
        ArrayList<Integer> queue = new ArrayList<>(vertexList.size());
        queue.add(i);
        visited[i] = true;
        while (queue.size() != 0) {
            // 当队列不空
            int index = queue.get(0);
            queue.remove(0);
            System.out.println(vertexList.get(index));
            int[] neighbor = getNeighbor(index);
            // 将所有未访问过的邻接结点入队列，并置为访问过
            for (int k : neighbor) {
                if (!visited[k]) {
                    queue.add(k);
                    visited[k] = true;
                }
            }
        }
    }

    // 得到一个结点的所有邻接结点
    public int[] getNeighbor(int index) {
        int count = 0;
        for (int i = 0; i < edges.length; i++) {
            if (edges[index][i] > 0) {
                count++;
            }
        }
        int[] neighbor = new int[count];
        count = 0;
        for (int i = 0; i < edges.length; i++) {
            if (edges[index][i] > 0) {
                neighbor[count++] = i;
            }
        }
        return neighbor;
    }

    // 深度优先遍历 --> 递归实现，只针对连通图，若为非连通图即需要循环调用dfs
    public void dfs(int i) {
        if (visited[i]) {
            // 访问过
            return;
        }
        visited[i] = true;
        System.out.println(vertexList.get(i));
        for (int j = 0; j < getNeighbor(i).length; j++) {
            if (!visited[getNeighbor(i)[j]]) {
                dfs(getNeighbor(i)[j]);
            }
        }
    }

    // 构造函数，初始化输入图中结点个数
    public Graph(int num) {
        vertexList = new ArrayList<>(num);
        edges = new int[num][num];
        visited = new boolean[num];
    }

    // 插入结点
    public void insertNode(String node) {
        vertexList.add(node);
    }

    // 插入边
    public void insertEdge(int n1, int n2, int weight) {
        edges[n1][n2] = weight;
        edges[n2][n1] = weight;
        numOfEdges++;
    }

    // 得到邻接矩阵
    public void printMatrix() {
        for (int[] arr : edges) {
            System.out.println(Arrays.toString(arr));
        }
    }

}