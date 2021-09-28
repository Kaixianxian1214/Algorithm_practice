package hashTable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {

        // test HashTable
        HashTable table = new HashTable(7);
        Scanner sc = new Scanner(System.in);
        String key;

        while(true){
            System.out.println("add：添加节点");
            System.out.println("show：遍历哈希表");
            System.out.println("exit：退出");
            System.out.println("search：查找");
            key = sc.next();
            int id;

            switch (key){
                case "add":
                    System.out.println("输入节点id：");
                    id = sc.nextInt();
                    System.out.println("输入节点姓名：");
                    String name = sc.next();
                    Node node = new Node(id, name);
                    table.addNode(node);
                    break;
                case "show":
                    table.traverse();
                    break;
                case "search":
                    System.out.println("输入查找的id：");
                    id = sc.nextInt();
                    table.search(id);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
            }
        }
    }
}

// HashTable
class HashTable{
    public LinkedList[] listArray;
    public int size;

    // 构造器
    public HashTable(int size){
        // 传入链表数
        this.size = size;
        listArray = new LinkedList[size];
        // !还要对没有个链表初始化
        for(int i = 0; i < size; i++){
            listArray[i] = new LinkedList();
        }
    }

    // 查找
    public void search(int id){
        int listNo = hashFunc(id);
        Node res = listArray[listNo].search(id);
        if(res == null){
            System.out.println("未查到该id的相关信息");
        }else{
            System.out.println("该id节点的信息为："+ res);
        }
    }

    // 添加
    public void addNode(Node node){
        int listNo = hashFunc(node.id);
        listArray[listNo].addNode(node);
    }

    // 遍历
    public void traverse(){
        for(int i = 0; i < size; i++){
            listArray[i].traverse(i);
        }
    }


    // 哈希函数
    public int hashFunc(int no){
        return no % size;
    }
}

// 管理头结点的链表
class LinkedList{
    public Node head;
    // 链表的头节点也存储数据

    // 查找链表
    public Node search(int id){
        if(head == null){
            // 链表空
            return null;
        }

        Node temp = head;
        while(temp != null){
            if(temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // 添加节点默认以为直接在后面添加即可
    public void addNode(Node node){
        if(head == null){
            head = node;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    // 遍历链表
    public void traverse(int no){
        if(head == null){
            System.out.println("第"+(no + 1) + "链表为空");
            return;
        }
        // 不空
        System.out.print("第"+(no + 1) + "链表的信息为：");
        Node temp = head;
        while(temp != null){
            System.out.print(temp +"\t");
            temp = temp.next;
        }
        System.out.println("");
    }
}

// 节点
class Node{
    public int id;
    public String name;
    Node next;             // 默认设置为null

    public Node(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
