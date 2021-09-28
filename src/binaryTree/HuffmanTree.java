package binaryTree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        createHuffmanTree(array).preOrder();
    }

    public static Node2 createHuffmanTree(int[] array){
        // 将array每个元素转化为节点，并保存到ArrayList
        ArrayList<Node2> nodeList = new ArrayList<>();
        for(int i : array){
            nodeList.add(new Node2(i));
        }

        // 当nodeList中的元素只有一个时，Huffman树创建成功
        while(nodeList.size() > 1){
            // 从小到大排序
            Collections.sort(nodeList);
            // 取出最小值的两个节点构建成一个数，并将新节点保存到nodeList
            Node2 leftNode = nodeList.get(0);
            Node2 rightNode = nodeList.get(1);
            Node2 parent = new Node2(leftNode.val + rightNode.val);
            parent.left = leftNode;
            parent.right = rightNode;
            nodeList.add(parent);
            // 将这两个最小节点从nodeList移除
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
        }
        // 返回最终的Huffman树
        return nodeList.get(0);
    }
}





// 为了让Node2对象排序，使用collections集合，让Node2实现comparable接口
class Node2 implements Comparable<Node2>{
    int val;
    Node2 left;
    Node2 right;

    public Node2(int val){
        this.val = val;
    }

    public void preOrder(){
        if (this == null){
            System.out.println("是空树~");
            return;
        }
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node2{" +
                "val=" + val +
                '}';
    }

    @Override
    public int compareTo(Node2 o) {
        // 表示对权值从小到大排序
        return this.val - o.val;
    }
}