package binaryTree;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


// �޸�00

public class HuffmanCodeDemo {
    public static void main(String[] args) {
        // test Huffman����
        String str = "i like like like java do you like a java";
        Node3 root = createHuffmanTree(str);
        root.preOrder();
    }

    // ����Huffman��
    public static Node3 createHuffmanTree(String str) {
        // �Ƚ�strת��ΪList
        ArrayList<Node3> nodeList = toList(str);
        // ����Huffman��
        while (nodeList.size() > 1) {
            // ��С��������
            Collections.sort(nodeList);

            Node3 leftNode = nodeList.get(0);
            Node3 rightNode = nodeList.get(1);

            // û��dataֱ������Ϊnull
            Node3 parent = new Node3(null, (leftNode.weight + rightNode.weight));
            parent.left = leftNode;
            parent.right = rightNode;
            // ��nodeListɾȥ��С�������ڵ㣬����parent����
            nodeList.remove(0);
            nodeList.remove(0);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    public static ArrayList<Node3> toList(String str) {
        byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);

        // ����һ��Map,key����ַ�,value����ַ��Ĵ���
        HashMap<Byte, Integer> byteMap = new HashMap<>();
        for (byte ele : byteArray) {
            // merge��������key�����ڣ���put����key���ڣ���������lambda���ʽ����ӳ���µ�value
            byteMap.merge(ele, 1, (oldValue, newValue) -> oldValue + 1);
        }

        // ����һ��nodeList������Ϣ
        ArrayList<Node3> nodeList = new ArrayList<>();

        // ����map�õ�.Entry<, > : map.entrySet()
        for (HashMap.Entry<Byte, Integer> ele : byteMap.entrySet()) {
            nodeList.add(new Node3(ele.getKey(), ele.getValue()));
        }

        return nodeList;
    }
}


// �ڵ㣬��Ȩֵ
class Node3 implements Comparable<Node3> {
    Byte data;    // ����ַ�
    int weight;   // ���Ȩֵ
    Node3 left;
    Node3 right;

    public Node3(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // ǰ�����
    public void preOrder() {
        // ����ǿ����Ļ����Ͳ��ܵ��÷���
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node3{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // ��С��������
    @Override
    public int compareTo(Node3 o) {
        return this.weight - o.weight;
    }
}
