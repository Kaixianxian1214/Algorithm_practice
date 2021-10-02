package binarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] array = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree tree = new BinarySortTree();
        for(int i : array){
            tree.addNode(new Node(i));
        }
        tree.infixOrder();
        tree.delNode(7);
        System.out.println("ɾ����:");
        tree.infixOrder();
    }
}

// ������
class BinarySortTree{
    Node root;    // ���ڵ�

    // ��ӽڵ�
    public void addNode(Node node){
        if(root == null){
            root = node;
        }else{
            root.addNode(node);
        }
    }

    // ɾ�����
    public void delNode(int val) {
        if(root == null){
            return;
        }

        Node target = searchNode(val);
        if(target == null){
            // ��û���ҵ�Ҫɾ����ֵ
            return;
        }
        // ��ȡparent
        Node parent = searchParent(val);

        // ɾ���Ľ����Ҷ�ӽ��
        if(target.left == null && target.right == null){
            if(target.val == root.val){
                // ��ͷ���
                root = null;
            }else if(parent.left.val == target.val){
                // �Ǹ���������
                parent.left = null;
            }else{
                // �Ǹ������Һ���
                parent.right = null;
            }
        }else if(target.left != null && target.right == null){
            // ɾ���Ľ��û�����ӽ�㣬�����ӽ��
            if(parent == null){
                // ��ɾ���Ľ���Ǹ��ڵ�ʱ
                root = target.left;
                return;
            }
            if(parent.left.val == target.val){
                // �Ǹ��������ӽ��
                parent.left = target.left;
            }else{
                // �Ǹ��������ӽ��
                parent.right = target.left;
            }
        }else if(target.left == null && target.right != null){
            if(parent == null){
                // ��ɾ���Ľ���Ǹ��ڵ�ʱ
                root = target.right;
                return;
            }
            // ɾ���Ľ��û�����ӽ�㣬û�����ӽ��
            if(parent.left.val == target.val){
                // �Ǹ��������ӽ��
                parent.left = target.right;
            }else{
                // �Ǹ��������ӽ��
                parent.right = target.right;
            }
        }else{
            // ɾ���Ľ��������ӽ�㣬�������ӽ�� --> �ҵ�target�������ӽ�����滻
            Node temp = target.left;
            while(temp.right != null){
                temp = temp.right;
            }
            int trans = temp.val;
            // �滻Ŀ�����ֵ
            temp = null;
            target.val = trans;
            // ɾ�������ӽ��
        }
    }

    // �������
    public void infixOrder(){
        if(root == null){
            System.out.println("�����������ա�");
            return;
        }
        root.infixOrder();
    }

    // ���ҽ��
    public Node searchNode(int val){
        if(root == null){
            return  null;
        }else{
            return root.searchNode(val);
        }
    }

    // ����parent
    public Node searchParent(int val){
        if(root == null){
            return  null;
        }else{
            return root.searchParent(val);
        }
    }
}


// Node�ڵ�
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }

    // ����һ������parent
    public Node searchParent(int val){
        if((this.left != null && this.left.val == val) || (this.right != null && this.right.val == val)){
            return this;
        }else{
            if(this.left != null && val < this.val){
                // ��ݹ�
                return this.left.searchParent(val);
            }else if(this.right != null && val > this.val){
                // �ҵݹ�
                return this.right.searchParent(val);
            }else{
                return null;
            }
        }
    }

    // ����ĳһ�����
    public Node searchNode(int val){
        if(this.val == val){
            return this;
        }
        if(this.left != null && val < this.val){
            // ��ݹ�
            return this.left.searchNode(val);
        }else if(this.right != null && val >this.val){
            // ��ݹ�
            return this.right.searchNode(val);
        }else{
            return null;
        }
    }

    // ��ӽ��ķ���
    public void addNode(Node node) {
        if (node == null) {
            // ���Ϊ�գ�����
            return;
        }

        if (node.val < this.val) {
            // ������ȵ�ǰ���С
            if (this.left == null) {
                this.left = node;
            } else {
                // ��ǰ������㲻Ϊ��
                this.left.addNode(node);
            }
        } else {
            // ����ڵ�ȵ�ǰ������ߵ�
            if (this.right == null) {
                this.right = node;
            } else {
                // ��ǰ����ҽ�㲻Ϊ��
                this.right.addNode(node);
            }
        }
    }

    // �������
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }

    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}