package binarySortTree;

public class AvlTreeDemo {
    public static void main(String[] args) {
        int[] array = {4, 3, 6 ,5 ,7, 8, 1};
        AVLTree tree = new AVLTree();
        for(int i : array){
            tree.addNode(i);
        }
        tree.infixOrder();
    }
}

class AVLTree{
    Node1 root;    // ���ڵ�

    // ������
    public void addNode(int val){
        if(root == null){
            root = new Node1(val);
        }else{
            root = root.addNode(root, val);
        }
    }

    // ���ĸ߶�
    public int height(){
        return root.height(root);
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
    public Node1 searchNode(int val){
        if(root == null){
            return  null;
        }else{
            return root.searchNode(val);
        }
    }

    // ����parent
    public Node1 searchParent(int val){
        if(root == null){
            return  null;
        }else{
            return root.searchParent(val);
        }
    }
}


// Node�ڵ�
class Node1 {
    public int val;
    public Node1 left;
    public Node1 right;

    public Node1(int val) {
        this.val = val;
    }

    // ��ӽ��
    public Node1 addNode(Node1 node, int val){
        // ����ǰ���Ϊ��
        if(node == null){
            return new Node1(val);
        }

        if(val < node.val){
            // ����������
            node.left = addNode(node.left, val);
            if(height(node.left) - height(node.right) == 2){
                if(val < node.left.val){
                    // ��������������ߣ�����
                    node = llRotate(node.left);
                }else{
                    // �������������ұߣ�����
                    node = lrRotate(node.left);
                }
            }
        }else{
            // ����������
            node.right = addNode(node.right, val);
            if(height(node.right) - height(node.left) == 2){
                if(val < node.right.val){
                    // ���������������
                    node = rlRotate(node.right);
                }else{
                    // �������������ұ�
                    node = rrRotate(node);
                }
            }
        }
        return node;
    }

    // LL��ת
    public Node1 llRotate(Node1 node){
        Node1 newNode = node.left;
        node.left = newNode.right;
        newNode.right = node;
        return node;
    }

    // ***�������͵���ת
    // RR��ת
    public Node1 rrRotate(Node1 node){
        Node1 newNode = node.right;
        node.right = newNode.left;
        newNode.left = node;
        return newNode;
    }
    // LR��ת
    public Node1 lrRotate(Node1 node){
        // �ȶ�node.left��һ��RR���ڶ�node��һ��LL
        node.left = rrRotate(node.left);
        return llRotate(node);
    }
    // RL��ת
    public Node1 rlRotate(Node1 node){
        // �ȶ�node.right��һ��LL���ڶ�node��һ��RR
        node.right = llRotate(node.right);
        return rrRotate(node);
    }
    // ���ĸ߶�
    public int height(Node1 node){
        if(node == null){
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // ����һ������parent
    public Node1 searchParent(int val){
        if((this.left != null && this.left.val == val) ||
                (this.right != null && this.right.val == val)){
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
    public Node1 searchNode(int val){
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
