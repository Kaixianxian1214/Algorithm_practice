package linkedlist;

// 双向链表
public class DoubleLinkedList_Test {

	public static void main(String[] args) {
		// Test

		Node node1 = new Node(1, "a", "a1");
		Node node2 = new Node(2, "b", "b2");
		Node node3 = new Node(3, "c", "c3");
		Node node4 = new Node(4, "d", "d4");
		
		
//		// test最尾部加入
//		DoubleLinkList doubleList = new DoubleLinkList();
//		doubleList.addNode(node1);
//		doubleList.addNode(node3);
//		doubleList.addNode(node4);
//		doubleList.addNode(node2);
//		System.out.println("显示链表~");
//		doubleList.list();
		
		
		// test按顺序插入
		DoubleLinkList doubleList = new DoubleLinkList();
		doubleList.addByOrder(node1);
		doubleList.addByOrder(node3);
		doubleList.addByOrder(node4);
		doubleList.addByOrder(node2);
		System.out.println("显示链表~");
		doubleList.list();
		
		
		
		//test删除
		doubleList.delNode(3);
		doubleList.list();
		
	}

}


class DoubleLinkList{
	private Node head = new Node(0, "", "");

	public Node getHead() {
		return head;
	}	
	
	// 添加节点按照no大小顺序
	
	public void addByOrder(Node node) {
		Node temp = head;
		
		while(temp.next != null) {           // 非插入到最后位置
			if(temp.next.no > node.no) {     // 找到前一个节点
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
	
	// 添加节点,仅仅尾部插入无顺序
	public void addNode(Node node) {
		// 创建辅助指针temp遍历链表
		Node temp = head;

		while (temp.next != null) {
			temp = temp.next;
		}
		// 找到最后一个节点
		temp.next = node;
		node.pre = temp;
	}

	//遍历链表
	public void list() {
		// 创建辅助指针temp遍历链表
		if(head.next == null) {              // 链表为空直接返回
			System.out.println("链表为空~");
			return;
		}
		Node temp = head.next;
		
		while(temp != null) {               // 打印节点信息
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	// 给定no删除节点
	public void delNode(int no) {
		Node cur = head.next;

		while (cur != null) {
			if (cur.no == no) {         // 找到要删除的节点cur
				cur.pre.next = cur.next;
				if(cur.next != null) {       // 需要判断所删除的节点是不是最后一个节点
					cur.next.pre = cur.pre;
				}
				return;
			}
			cur = cur.next;
		}
		System.out.println("未找到编号" + no + "的节点，无法删除~");
	}
}


//定义节点
class Node{
	public int no;
	public String name;
	public String nickname;
	public Node next;     // 指向下一个节点
	public Node pre;      // 指向前一个节点
	
	// 构造方法
	public Node(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	
	// 重写toString
	@Override
	public String toString() {
		return "Node [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}	
}