package binaryTree;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class HuffmanCodeDemo {
    public static void main(String[] args) {

        // test Huffman编码
        String str = "i like like like java do you like a java";
        getHuffmanCode(str);
    }

    // 将字符串转化为Huffman编码
    public static void getHuffmanCode(String str){
        // 通过str创建哈夫曼树
        Node3 root = createHuffmanTree(str);
        // 输出创建的哈夫曼树对应的哈夫曼编码
        getHuffmanNodes(root, "", new StringBuilder());

        // 将字符串转化为Huffman编码
        StringBuilder stringBuilder = new StringBuilder();
        byte[] byteArray = str.getBytes();
        for(byte ele : byteArray){
            stringBuilder.append(huffmanCode.get(ele));
        }
        System.out.println(stringBuilder);
    }

    // huffmanCode用来存储字符和编码的对应关系
    static HashMap<Byte, StringBuilder> huffmanCode = new HashMap<>();

    // 将Huffman编码表存放在Map中
    public static void getHuffmanNodes(Node3 node, String code, StringBuilder stringBuilder){
        // 先将上层string保存，并将code拼接
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);

        // 判断该节点是否是叶子结点
        if(node.data == null){
            // 说明该节点是非叶子结点,非叶子结点一定会有所有结点的
            getHuffmanNodes(node.left, "0", stringBuilder2);
            getHuffmanNodes(node.right, "1", stringBuilder2);
        }else{
            // 如果是叶子结点，则将键值对保存
            huffmanCode.put(node.data, stringBuilder2);
        }
    }

    // 创建Huffman树
    public static Node3 createHuffmanTree(String str) {
        // 先将str转化为List
        ArrayList<Node3> nodeList = toList(str);
        // 创建Huffman树
        while (nodeList.size() > 1) {
            // 从小到大排序
            Collections.sort(nodeList);

            Node3 leftNode = nodeList.get(0);
            Node3 rightNode = nodeList.get(1);

            // 没有data直接设置为null
            Node3 parent = new Node3(null, (leftNode.weight + rightNode.weight));
            parent.left = leftNode;
            parent.right = rightNode;
            // 从nodeList删去最小的两个节点，并将parent加入
            nodeList.remove(0);
            nodeList.remove(0);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    public static ArrayList<Node3> toList(String str) {
        byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);

        // 创建一个Map,key存放字符,value存放字符的次数
        HashMap<Byte, Integer> byteMap = new HashMap<>();
        for (byte ele : byteArray) {
            // merge函数，若key不存在，则put，若key存在，则按照最后的lambda表达式进行映射新的value
            byteMap.merge(ele, 1, (oldValue, newValue) -> oldValue + 1);
        }

        // 创建一个nodeList保存信息
        ArrayList<Node3> nodeList = new ArrayList<>();

        // 遍历map用到.Entry<, > : map.entrySet()
        for (HashMap.Entry<Byte, Integer> ele : byteMap.entrySet()) {
            nodeList.add(new Node3(ele.getKey(), ele.getValue()));
        }

        return nodeList;
    }
}


// 节点，带权值
class Node3 implements Comparable<Node3> {
    Byte data;    // 存放字符
    int weight;   // 存放权值
    Node3 left;
    Node3 right;

    public Node3(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 前序遍历
    public void preOrder() {
        // 如果是空树的话，就不能调用方法
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

    // 从小到大排序
    @Override
    public int compareTo(Node3 o) {
        return this.weight - o.weight;
    }
}
