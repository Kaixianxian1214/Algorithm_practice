package binaryTree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        initTree(tree);

//        // testǰ�����
//        System.out.println("ǰ�������");
//        tree.preTraverse();
//
//        // test�������
//        System.out.println("���������");
//        tree.infixTraverse();
//
//        // test�������
//        System.out.println("���������");
//        tree.postTraverse();

//        // testǰ�����
//        for (int i = 0; i < 7; i++){
//            System.out.println(tree.searchTree(i));
//        }

        // testɾ���ڵ�
        tree.preTraverse();
        tree.delNode(3);
        tree.preTraverse();
    }

    public static void initTree(BinaryTree tree) {
        // �ֶ�����һ���򵥵Ķ�����
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

    // ǰ�����
    public void preTraverse() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("��ǰ������Ϊ��~");
        }
    }

    // �������
    public void infixTraverse() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("��ǰ������Ϊ��~");
        }
    }

    // �������
    public void postTraverse() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("��ǰ������Ϊ��~");
        }
    }

    // ǰ�����
    public Node searchTree(int no){
        if(root != null){
            return root.searchNode(no);
        }else{
            return null;
        }
    }

    // ɾ���ڵ��������
    public void delNode(int no){
        if(root == null){
            // ����
            System.out.println("���գ�����ɾ��~");
        }else if(root.no == no) {
            root = null;
        }else{
            // ����
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

    // ǰ��
    public void preOrder() {
        System.out.println(this);      // ��������ڵ�
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // ����
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // ����
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // ǰ���������
    public Node searchNode(int no) {
        if (this.no == no) {
            return this;
        }
        Node resNode = null;
        if(this.left != null){
            resNode =  this.left.searchNode(no);
        }

        // ��Ҫ���������Ĳ��ҽ���ж�
        if(resNode != null){
            return  resNode;
        }
        if(this.right != null){
            resNode = this.right.searchNode(no);
        }
        return resNode;
    }

    // ɾ����ɾ��Ҷ�ӽڵ��������
    public void delNode(int no){
        if(this.left != null && this.left.no == no){
            // ɾ��������
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            // ɾ��������
            this.right = null;
            return;
        }
        // ���ҵݹ�
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