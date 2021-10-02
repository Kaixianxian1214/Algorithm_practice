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
    Node1 root;    // 根节点

    // 插入结点
    public void addNode(int val){
        if(root == null){
            root = new Node1(val);
        }else{
            root = root.addNode(root, val);
        }
    }

    // 树的高度
    public int height(){
        return root.height(root);
    }

    // 中序遍历
    public void infixOrder(){
        if(root == null){
            System.out.println("二叉排序树空～");
            return;
        }
        root.infixOrder();
    }

    // 查找结点
    public Node1 searchNode(int val){
        if(root == null){
            return  null;
        }else{
            return root.searchNode(val);
        }
    }

    // 查找parent
    public Node1 searchParent(int val){
        if(root == null){
            return  null;
        }else{
            return root.searchParent(val);
        }
    }
}


// Node节点
class Node1 {
    public int val;
    public Node1 left;
    public Node1 right;

    public Node1(int val) {
        this.val = val;
    }

    // 添加结点
    public Node1 addNode(Node1 node, int val){
        // 若当前结点为空
        if(node == null){
            return new Node1(val);
        }

        if(val < node.val){
            // 左子树插入
            node.left = addNode(node.left, val);
            if(height(node.left) - height(node.right) == 2){
                if(val < node.left.val){
                    // 加在左子树的左边，调整
                    node = llRotate(node.left);
                }else{
                    // 加在左子树的右边，调整
                    node = lrRotate(node.left);
                }
            }
        }else{
            // 右子树插入
            node.right = addNode(node.right, val);
            if(height(node.right) - height(node.left) == 2){
                if(val < node.right.val){
                    // 加在右子树的左边
                    node = rlRotate(node.right);
                }else{
                    // 加在右子树的右边
                    node = rrRotate(node);
                }
            }
        }
        return node;
    }

    // LL旋转
    public Node1 llRotate(Node1 node){
        Node1 newNode = node.left;
        node.left = newNode.right;
        newNode.right = node;
        return node;
    }

    // ***四种类型的旋转
    // RR旋转
    public Node1 rrRotate(Node1 node){
        Node1 newNode = node.right;
        node.right = newNode.left;
        newNode.left = node;
        return newNode;
    }
    // LR旋转
    public Node1 lrRotate(Node1 node){
        // 先对node.left做一次RR，在对node做一次LL
        node.left = rrRotate(node.left);
        return llRotate(node);
    }
    // RL旋转
    public Node1 rlRotate(Node1 node){
        // 先对node.right做一次LL，在对node做一次RR
        node.right = llRotate(node.right);
        return rrRotate(node);
    }
    // 树的高度
    public int height(Node1 node){
        if(node == null){
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // 查找一个结点的parent
    public Node1 searchParent(int val){
        if((this.left != null && this.left.val == val) ||
                (this.right != null && this.right.val == val)){
            return this;
        }else{
            if(this.left != null && val < this.val){
                // 左递归
                return this.left.searchParent(val);
            }else if(this.right != null && val > this.val){
                // 右递归
                return this.right.searchParent(val);
            }else{
                return null;
            }
        }
    }

    // 查找某一个结点
    public Node1 searchNode(int val){
        if(this.val == val){
            return this;
        }
        if(this.left != null && val < this.val){
            // 左递归
            return this.left.searchNode(val);
        }else if(this.right != null && val >this.val){
            // 左递归
            return this.right.searchNode(val);
        }else{
            return null;
        }
    }

    // 中序遍历
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
