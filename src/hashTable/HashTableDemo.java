package hashTable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {

        // test HashTable
        HashTable table = new HashTable(7);
        Scanner sc = new Scanner(System.in);
        String key;

        while(true){
            System.out.println("add����ӽڵ�");
            System.out.println("show��������ϣ��");
            System.out.println("exit���˳�");
            System.out.println("search������");
            key = sc.next();
            int id;

            switch (key){
                case "add":
                    System.out.println("����ڵ�id��");
                    id = sc.nextInt();
                    System.out.println("����ڵ�������");
                    String name = sc.next();
                    Node node = new Node(id, name);
                    table.addNode(node);
                    break;
                case "show":
                    table.traverse();
                    break;
                case "search":
                    System.out.println("������ҵ�id��");
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

    // ������
    public HashTable(int size){
        // ����������
        this.size = size;
        listArray = new LinkedList[size];
        // !��Ҫ��û�и������ʼ��
        for(int i = 0; i < size; i++){
            listArray[i] = new LinkedList();
        }
    }

    // ����
    public void search(int id){
        int listNo = hashFunc(id);
        Node res = listArray[listNo].search(id);
        if(res == null){
            System.out.println("δ�鵽��id�������Ϣ");
        }else{
            System.out.println("��id�ڵ����ϢΪ��"+ res);
        }
    }

    // ���
    public void addNode(Node node){
        int listNo = hashFunc(node.id);
        listArray[listNo].addNode(node);
    }

    // ����
    public void traverse(){
        for(int i = 0; i < size; i++){
            listArray[i].traverse(i);
        }
    }


    // ��ϣ����
    public int hashFunc(int no){
        return no % size;
    }
}

// ����ͷ��������
class LinkedList{
    public Node head;
    // �����ͷ�ڵ�Ҳ�洢����

    // ��������
    public Node search(int id){
        if(head == null){
            // �����
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

    // ��ӽڵ�Ĭ����Ϊֱ���ں�����Ӽ���
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

    // ��������
    public void traverse(int no){
        if(head == null){
            System.out.println("��"+(no + 1) + "����Ϊ��");
            return;
        }
        // ����
        System.out.print("��"+(no + 1) + "�������ϢΪ��");
        Node temp = head;
        while(temp != null){
            System.out.print(temp +"\t");
            temp = temp.next;
        }
        System.out.println("");
    }
}

// �ڵ�
class Node{
    public int id;
    public String name;
    Node next;             // Ĭ������Ϊnull

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
