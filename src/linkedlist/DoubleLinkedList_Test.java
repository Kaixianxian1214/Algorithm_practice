package linkedlist;

// ˫������
public class DoubleLinkedList_Test {

	public static void main(String[] args) {
		// Test

		Node node1 = new Node(1, "a", "a1");
		Node node2 = new Node(2, "b", "b2");
		Node node3 = new Node(3, "c", "c3");
		Node node4 = new Node(4, "d", "d4");
		
		
//		// test��β������
//		DoubleLinkList doubleList = new DoubleLinkList();
//		doubleList.addNode(node1);
//		doubleList.addNode(node3);
//		doubleList.addNode(node4);
//		doubleList.addNode(node2);
//		System.out.println("��ʾ����~");
//		doubleList.list();
		
		
		// test��˳�����
		DoubleLinkList doubleList = new DoubleLinkList();
		doubleList.addByOrder(node1);
		doubleList.addByOrder(node3);
		doubleList.addByOrder(node4);
		doubleList.addByOrder(node2);
		System.out.println("��ʾ����~");
		doubleList.list();
		
		
		
		//testɾ��
		doubleList.delNode(3);
		doubleList.list();
		
	}

}


class DoubleLinkList{
	private Node head = new Node(0, "", "");

	public Node getHead() {
		return head;
	}	
	
	// ��ӽڵ㰴��no��С˳��
	
	public void addByOrder(Node node) {
		Node temp = head;
		
		while(temp.next != null) {           // �ǲ��뵽���λ��
			if(temp.next.no > node.no) {     // �ҵ�ǰһ���ڵ�
				node.next = temp.next;
				node.pre = temp;
				temp.next.pre = node;
				temp.next = node;
				return;
			}
			temp = temp.next;
		}
		temp.next = node;
		node.pre = temp;
	}
	
	// ��ӽڵ�,����β��������˳��
	public void addNode(Node node) {
		// ��������ָ��temp��������
		Node temp = head;

		while (temp.next != null) {
			temp = temp.next;
		}
		// �ҵ����һ���ڵ�
		temp.next = node;
		node.pre = temp;
	}

	//��������
	public void list() {
		// ��������ָ��temp��������
		if(head.next == null) {              // ����Ϊ��ֱ�ӷ���
			System.out.println("����Ϊ��~");
			return;
		}
		Node temp = head.next;
		
		while(temp != null) {               // ��ӡ�ڵ���Ϣ
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	// ����noɾ���ڵ�
	public void delNode(int no) {
		Node cur = head.next;

		while (cur != null) {
			if (cur.no == no) {         // �ҵ�Ҫɾ���Ľڵ�cur
				cur.pre.next = cur.next;
				if(cur.next != null) {       // ��Ҫ�ж���ɾ���Ľڵ��ǲ������һ���ڵ�
					cur.next.pre = cur.pre;
				}
				return;
			}
			cur = cur.next;
		}
		System.out.println("δ�ҵ����" + no + "�Ľڵ㣬�޷�ɾ��~");
	}
}


//����ڵ�
class Node{
	public int no;
	public String name;
	public String nickname;
	public Node next;     // ָ����һ���ڵ�
	public Node pre;      // ָ��ǰһ���ڵ�
	
	// ���췽��
	public Node(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	
	// ��дtoString
	@Override
	public String toString() {
		return "Node [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}	
}