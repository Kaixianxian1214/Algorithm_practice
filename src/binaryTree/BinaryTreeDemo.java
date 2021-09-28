package binaryTree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        initTree(tree);

//        // test前序遍历
//        System.out.println("前序遍历：");
//        tree.preTraverse();
//
//        // test中序遍历
//        System.out.println("中序遍历：");
//        tree.infixTraverse();
//
//        // test后序遍历
//        System.out.println("后序遍历：");
//        tree.postTraverse();

//        // test前序查找
//        for (int i = 0; i < 7; i++){
//            System.out.println(tree.searchTree(i));
//        }

        // test删除节点
        tree.preTraverse();
        tree.delNode(3);
        tree.preTraverse();
    }

    public static void initTree(BinaryTree tree) {
        // 手动创建一个简单的二叉树
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        tree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        node3.left = node5;
    }
}


class BinaryTree {
    Node root;

    // 前序遍历
    public void preTraverse() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("当前二叉树为空~");
        }
    }

    // 中序遍历
    public void infixTraverse() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("当前二叉树为空~");
        }
    }

    // 后序遍历
    public void postTraverse() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("当前二叉树为空~");
        }
    }

    // 前序查找
    public Node searchTree(int no){
        if(root != null){
            return root.searchNode(no);
        }else{
            return null;
        }
    }

    // 删除节点或者子树
    public void delNode(int no){
        if(root == null){
            // 树空
            System.out.println("树空，不可删除~");
        }else if(root.no == no) {
            root = null;
        }else{
            // 调用
            root.delNode(no);
        }
    }
}


class Node {
    int no;
    Node left;
    Node right;

    public Node(int no) {
        this.no = no;
    }

    // 前序
    public void preOrder() {
        System.out.println(this);      // 先输出父节点
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 前序遍历查找
    public Node searchNode(int no) {
        if (this.no == no) {
            return this;
        }
        Node resNode = null;
        if(this.left != null){
            resNode =  this.left.searchNode(no);
        }

        // 需要对左子树的查找结果判断
        if(resNode != null){
            return  resNode;
        }
        if(this.right != null){
            resNode = this.right.searchNode(no);
        }
        return resNode;
    }

    // 删除，删除叶子节点或者子树
    public void delNode(int no){
        if(this.left != null && this.left.no == no){
            // 删除左子树
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            // 删除右子树
            this.right = null;
            return;
        }
        // 左右递归
        if(this.left != null){
            this.left.delNode(no);
        }
        if(this.right != null) {
            this.right.delNode(no);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}