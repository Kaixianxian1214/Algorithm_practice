package binaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedTree tree = new ThreadedTree();
        initTree1(tree);

        // test����������������
        tree.threadedTree();
        tree.preOrder(tree.root);

    }

    public static void initTree1(ThreadedTree tree){
        // �ֶ�����һ���򵥵Ķ�����
        Node1 node1 = new Node1(1);
        Node1 node2 = new Node1(3);
        Node1 node3 = new Node1(6);
        Node1 node4 = new Node1(8);
        Node1 node5 = new Node1(10);
        Node1 node6 = new Node1(14);

        tree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
    }
}

// ����������ڵ�
class ThreadedTree{
    Node1 root;
    // preָ��ǰ�ڵ��ǰ��
    Node1 pre;

    // �����������������
    public void preOrder(Node1 root){
        if(root == null){
            // ����
            return;
        }

        // ���ҵ������µĽڵ�
        Node1 node = root;

        while(node != null){
            // �ҵ������е�����ڵ�
            while(node.leftFlag == 0){
                node = node.left;
            }
            // ����ýڵ�
            System.out.println(node);

            // ����������̽ڵ㣨rightFlag == 1��
            while(node.rightFlag == 1){
                node = node.right;
                System.out.println(node);
            }

            // �����󣬴�ʱnodeλ�����������ҽڵ�ĸ��ڵ㣬��ʱ���ڵ��Ѿ�����������ش�ʱ���������ĸ��ڵ�
            node = node.right;
        }
    }

    // ����������������
    public void threadedTree(){
        threadedBinaryTree(root);
    }

    public void threadedBinaryTree(Node1 root){
        if(root == null){
            // ����
            return;
        }

        // �ݹ�������
        if(root.left != null){
            threadedBinaryTree(root.left);
        }

        // ��������ǰ�ڵ�
        if(root.left == null){
            root.left = pre;
            root.leftFlag = 1;
        }

        if(pre != null && pre.right == null){
            pre.right = root;
            pre.rightFlag = 1;
        }

        pre =root;

        // �ݹ�������
        if(root.right != null){
            threadedBinaryTree(root.right);
        }
    }

}

// ����ڵ�
class Node1{
    int no;
    Node1 left;
    Node1 right;

    // ��ʱ���������flag˵�����ҽڵ�����ͣ�0��ʾΪ���ӽڵ㣬1��ʾ������
    int leftFlag = 0;
    int rightFlag = 0;

    public Node1(int no){
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node1{" +
                "no=" + no +
                '}';
    }
}