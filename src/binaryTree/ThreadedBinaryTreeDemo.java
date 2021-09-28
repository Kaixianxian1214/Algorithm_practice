package binaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedTree tree = new ThreadedTree();
        initTree1(tree);

        // test中序线索化二叉树
        tree.threadedTree();
        tree.preOrder(tree.root);

    }

    public static void initTree1(ThreadedTree tree){
        // 手动创建一个简单的二叉树
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

// 定义树管理节点
class ThreadedTree{
    Node1 root;
    // pre指向当前节点的前驱
    Node1 pre;

    // 中序遍历线索二叉树
    public void preOrder(Node1 root){
        if(root == null){
            // 空树
            return;
        }

        // 先找到最左下的节点
        Node1 node = root;

        while(node != null){
            // 找到子树中的最左节点
            while(node.leftFlag == 0){
                node = node.left;
            }
            // 输出该节点
            System.out.println(node);

            // 连带输出其后继节点（rightFlag == 1）
            while(node.rightFlag == 1){
                node = node.right;
                System.out.println(node);
            }

            // 输出完后，此时node位于子树的最右节点的根节点，此时根节点已经输出过，返回此时整个子树的根节点
            node = node.right;
        }
    }

    // 中序线索化二叉树
    public void threadedTree(){
        threadedBinaryTree(root);
    }

    public void threadedBinaryTree(Node1 root){
        if(root == null){
            // 空树
            return;
        }

        // 递归左子树
        if(root.left != null){
            threadedBinaryTree(root.left);
        }

        // 线索化当前节点
        if(root.left == null){
            root.left = pre;
            root.leftFlag = 1;
        }

        if(pre != null && pre.right == null){
            pre.right = root;
            pre.rightFlag = 1;
        }

        pre =root;

        // 递归右子树
        if(root.right != null){
            threadedBinaryTree(root.right);
        }
    }

}

// 定义节点
class Node1{
    int no;
    Node1 left;
    Node1 right;

    // 此时需添加两个flag说明左右节点的类型，0表示为孩子节点，1表示线索化
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