package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphDemo {
    public static void main(String[] args) {
        Graph graph = initGraph();
//        // test �ڽӾ���
//        graph.printMatrix();

        // test ������ȱ���
        graph.dfs(0);

//        // test ������ȱ���
//        graph.bfs(0);

    }

    public static Graph initGraph() {
        Graph graph = new Graph(8);
        // ������
        String[] VertexValue = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (String vertex : VertexValue) {
            graph.insertNode(vertex);
        }
        // �����
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
    boolean[] visited;   // ������ֵΪȫfalse

    // ������ȱ���
    public void bfs(int i) {
        ArrayList<Integer> queue = new ArrayList<>(vertexList.size());
        queue.add(i);
        visited[i] = true;
        while (queue.size() != 0) {
            // �����в���
            int index = queue.get(0);
            queue.remove(0);
            System.out.println(vertexList.get(index));
            int[] neighbor = getNeighbor(index);
            // ������δ���ʹ����ڽӽ������У�����Ϊ���ʹ�
            for (int k : neighbor) {
                if (!visited[k]) {
                    queue.add(k);
                    visited[k] = true;
                }
            }
        }
    }

    // �õ�һ�����������ڽӽ��
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

    // ������ȱ��� --> �ݹ�ʵ�֣�ֻ�����ͨͼ����Ϊ����ͨͼ����Ҫѭ������dfs
    public void dfs(int i) {
        if (visited[i]) {
            // ���ʹ�
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

    // ���캯������ʼ������ͼ�н�����
    public Graph(int num) {
        vertexList = new ArrayList<>(num);
        edges = new int[num][num];
        visited = new boolean[num];
    }

    // ������
    public void insertNode(String node) {
        vertexList.add(node);
    }

    // �����
    public void insertEdge(int n1, int n2, int weight) {
        edges[n1][n2] = weight;
        edges[n2][n1] = weight;
        numOfEdges++;
    }

    // �õ��ڽӾ���
    public void printMatrix() {
        for (int[] arr : edges) {
            System.out.println(Arrays.toString(arr));
        }
    }

}