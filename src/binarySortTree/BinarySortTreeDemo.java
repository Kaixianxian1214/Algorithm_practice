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
        System.out.println("删除后:");
        tree.infixOrder();
    }
}

// 创建树
class BinarySortTree{
    Node root;    // 根节点

    // 添加节点
    public void addNode(Node node){
        if(root == null){
            root = node;
        }else{
            root.addNode(node);
        }
    }

    // 删除结点
    public void delNode(int val) {
        if(root == null){
            return;
        }

        Node target = searchNode(val);
        if(target == null){
            // 当没有找到要删除的值
            return;
        }
        // 获取parent
        Node parent = searchParent(val);

        // 删除的结点是叶子结点
        if(target.left == null && target.right == null){
            if(target.val == root.val){
                // 是头结点
                root = null;
            }else if(parent.left.val == target.val){
                // 是父结点的左孩子
                parent.left = null;
            }else{
                // 是父结点的右孩子
                parent.right = null;
            }
        }else if(target.left != null && target.right == null){
            // 删除的结点没有右子结点，有左子结点
            if(parent == null){
                // 当删除的结点是根节点时
                root = target.left;
                return;
            }
            if(parent.left.val == target.val){
                // 是父结点的左子结点
                parent.left = target.left;
            }else{
                // 是父结点的右子结点
                parent.right = target.left;
            }
        }else if(target.left == null && target.right != null){
            if(parent == null){
                // 当删除的结点是根节点时
                root = target.right;
                return;
            }
            // 删除的结点没有左子结点，没有右子结点
            if(parent.left.val == target.val){
                // 是父结点的左子结点
                parent.left = target.right;
            }else{
                // 是父结点的右子结点
                parent.right = target.right;
            }
        }else{
            // 删除的结点既有左子结点，又有右子结点 --> 找到target的最左子结点来替换
            Node temp = target.left;
            while(temp.right != null){
                temp = temp.right;
            }
            int trans = temp.val;
            // 替换目标结点的值
            temp = null;
            target.val = trans;
            // 删除最左子结点
        }
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
    public Node searchNode(int val){
        if(root == null){
            return  null;
        }else{
            return root.searchNode(val);
        }
    }

    // 查找parent
    public Node searchParent(int val){
        if(root == null){
            return  null;
        }else{
            return root.searchParent(val);
        }
    }
}


// Node节点
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }

    // 查找一个结点的parent
    public Node searchParent(int val){
        if((this.left != null && this.left.val == val) || (this.right != null && this.right.val == val)){
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
    public Node searchNode(int val){
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

    // 添加结点的方法
    public void addNode(Node node) {
        if (node == null) {
            // 结点为空，返回
            return;
        }

        if (node.val < this.val) {
            // 加入结点比当前结点小
            if (this.left == null) {
                this.left = node;
            } else {
                // 当前结点左结点不为空
                this.left.addNode(node);
            }
        } else {
            // 加入节点比当前结点大或者等
            if (this.right == null) {
                this.right = node;
            } else {
                // 当前结点右结点不为空
                this.right.addNode(node);
            }
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