package linkedlist;

import java.util.Stack;

// 单向链表
public class SingleLinkedList_Test {

	public static void main(String[] args) {
		// test
		SingleNode node1 = new SingleNode(1, "a", "a1");
		SingleNode node2 = new SingleNode(2, "b", "b2");
		SingleNode node3 = new SingleNode(3, "c", "c3");
		SingleNode node4 = new SingleNode(4, "d", "d4");
		
		// 创建链表
		SingleLinkedList singleList = new SingleLinkedList();
		singleList.addByOrder(node1);
		singleList.addByOrder(node4);
		singleList.addByOrder(node3);
		singleList.addByOrder(node2);
		System.out.println("节点情况~");
		singleList.list();
			
//		// test修改节点的代码
//		SingleNode node5 = new SingleNode(5, "4%", "444$");
//		singleList.updateList(node5);
		
//		// test删除节点
//		singleList.delNode(4);
//		System.out.println("删除后节点情况~");	
//		singleList.list();
		
//		// test遍历节点个数
//		System.out.println("有效的结点个数是："+getLength(singleList.getHead()));
		
//		// 查找倒数第k个节点
//		System.out.println(findLastKnode(singleList.getHead(), 0));
		
//		// test反置链表1 -- 每次从原链表最后面摘取
//		reverseList(singleList.getHead());
//		singleList.list();
		
//		// test反置链表2 -- 依次从原链表顺序摘取
//		reverseList2(singleList.getHead());
//		singleList.list();
		
		// test反序打印 -- 是用到栈
		reversePrint(singleList.getHead());
		
	}
	
	// 输出链表节点个数
	public static int getLength(SingleNode head) {
		SingleNode temp = head.next;
		int count = 0;
		while(temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	} 

	// 输出链表倒数第k个节点内容
	public static SingleNode findLastKnode(SingleNode head, int index) {
		int size = getLength(head);
		SingleNode temp= head.next;
		if(index <= 0 || size < index) {
			System.out.println("输入的index有误~");         // 出现问题返回null
			return null;
		}
		
		int times = size - index;          // 5 ---> 倒数第2,,, 变换三次
		while(times != 0) {
			temp = temp.next;
			times--;
		}
		return temp;
	}
	
	// 反置链表1 -- 每次从原链表最后面摘取
	public static void reverseList(SingleNode head) {
		
		SingleNode reverse = new SingleNode(0, "", "");
		SingleNode trans = reverse;                        // 需要这是辅助变量trans不断后移，reverse作为首部保持不变
		while(head.next != null) {
			SingleNode temp = head;
			
			while(temp.next.next != null) {        // 此时的temp为最后一个节点的前一个节点
				temp = temp.next;
			}
			
			trans.next = temp.next;                // 摘取最后一个节点
			trans = trans.next;
			temp.next = null;                      // 删去最后一个节点
			temp = head;
		}
		head.next = reverse.next;
	}
	
	// 反置链表2 -- 依次从原链表顺序摘取
	public static void reverseList2(SingleNode head) {
		SingleNode reverse = new SingleNode(0, "", "");
		SingleNode cur = head.next;
		SingleNode next = null;                         // 指向当前节点的下一个节点
		
		while(cur != null) {                            // 当原链表中还有节点
			next = cur.next;
			cur.next = reverse.next;
			reverse.next = cur;
			cur = next;
		}
		head.next = reverse.next;
	}
	
	// 反向打印链表，用到栈的方法
	public static void reversePrint(SingleNode head) {
		Stack<SingleNode> stack = new Stack<SingleNode>();
		SingleNode cur = head.next;
		
		while(cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		while(stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}
}

// 定义SingleLinkedList进行管理
class SingleLinkedList{
	SingleNode head = new SingleNode(0, "", "");
		
	public SingleNode getHead() {
		return head;
	}

	// 添加节点,仅仅尾部插入无顺序
	public void addNode(SingleNode node) {
		// 创建辅助指针temp遍历链表
		SingleNode temp = head;
		
		
		while(temp.next != null) {
			temp = temp.next;
		}
		// 找到最后一个节点
		temp.next = node;
	}
	
	
	
	// 添加节点，按照排名（no）的顺序
	// 先找到节点的在插入位置中的前一个节点，在进行插入
	// 若节点的序号已经存在，在插入失败，显示提示信息
	public void addByOrder(SingleNode node) {
		SingleNode temp = head;
		
		while(temp.next != null) {
			if(temp.next.no > node.no || temp.next == null) {   // 找到插入位置的前一个结点位置temp,并退出循环
				break;
			}else if (temp.next.no == node.no) {    // 该编号已经存在
				System.out.println("该序号已经存在，插入失败~");
				return;
			}
			temp = temp.next;
		}
		node.next = temp.next;
		temp.next = node;
	}
	
	// 给定no删除节点
	public void delNode(int no) {
		SingleNode temp = head;
		
		while(temp.next != null) {
			if(temp.next.no == no) {      // 找到该节点的前一个结点temp
				temp.next = temp.next.next;
				return;
			}
			temp = temp.next;
		}
		System.out.println("未找到编号"+no+"的节点，无法删除~");
 	}
	
	// 根据所给节点的no修改更新，当no不存在输出提示信息
	public void updateList(SingleNode node) {
		SingleNode temp = head.next;
		
		while(temp != null) {
			if(temp.no == node.no) {      //找到节点，并修改信息
				temp.name = node.name;
				temp.nickname = node.nickname;
				return;
			}
			temp = temp.next;
		}
		
		System.out.println("未找到编号"+node.no+"的节点");
	}
	
	//遍历链表
	public void list() {
		// 创建辅助指针temp遍历链表
		if(head.next == null) {              // 链表为空直接返回
			System.out.println("链表为空~");
			return;
		}
		SingleNode temp = head.next;
		
		while(temp != null) {               // 打印节点信息
			System.out.println(temp);
			temp = temp.next;
		}
	}	
}

// 定义节点
class SingleNode{
	public int no;
	public String name;
	public String nickname;
	public SingleNode next;     // 指向下一个节点
	
	// 构造方法
	public SingleNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	
	// 重写toString
	@Override
	public String toString() {
		return "SingleNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}	
}