package binaryTree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        // test 数组顺序存储二叉树
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree tree = new ArrBinaryTree(array);
        tree.preSearch();
    }
}

class ArrBinaryTree {
    int arrayTree[];

    public ArrBinaryTree(int[] array) {
        arrayTree = array;
    }

    public void preSearch() {
        search(0);
    }

    public void search(int index) {
        if (arrayTree == null || arrayTree.length == 0) {
            return;
        }
        System.out.print(arrayTree[index] + "\t");

        // 左右递归
        if ((2 * index) + 1 < arrayTree.length) {
            search(2 * index + 1);
        }
        if ((2 * index) + 2 < arrayTree.length) {
            search(2 * index + 2);
        }
    }
}
