package linkedlist;

import java.util.Stack;

// ��������
public class SingleLinkedList_Test {

	public static void main(String[] args) {
		// test
		SingleNode node1 = new SingleNode(1, "a", "a1");
		SingleNode node2 = new SingleNode(2, "b", "b2");
		SingleNode node3 = new SingleNode(3, "c", "c3");
		SingleNode node4 = new SingleNode(4, "d", "d4");
		
		// ��������
		SingleLinkedList singleList = new SingleLinkedList();
		singleList.addByOrder(node1);
		singleList.addByOrder(node4);
		singleList.addByOrder(node3);
		singleList.addByOrder(node2);
		System.out.println("�ڵ����~");
		singleList.list();
			
//		// test�޸Ľڵ�Ĵ���
//		SingleNode node5 = new SingleNode(5, "4%", "444$");
//		singleList.updateList(node5);
		
//		// testɾ���ڵ�
//		singleList.delNode(4);
//		System.out.println("ɾ����ڵ����~");	
//		singleList.list();
		
//		// test�����ڵ����
//		System.out.println("��Ч�Ľ������ǣ�"+getLength(singleList.getHead()));
		
//		// ���ҵ�����k���ڵ�
//		System.out.println(findLastKnode(singleList.getHead(), 0));
		
//		// test��������1 -- ÿ�δ�ԭ���������ժȡ
//		reverseList(singleList.getHead());
//		singleList.list();
		
//		// test��������2 -- ���δ�ԭ����˳��ժȡ
//		reverseList2(singleList.getHead());
//		singleList.list();
		
		// test�����ӡ -- ���õ�ջ
		reversePrint(singleList.getHead());
		
	}
	
	// �������ڵ����
	public static int getLength(SingleNode head) {
		SingleNode temp = head.next;
		int count = 0;
		while(temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	} 

	// �����������k���ڵ�����
	public static SingleNode findLastKnode(SingleNode head, int index) {
		int size = getLength(head);
		SingleNode temp= head.next;
		if(index <= 0 || size < index) {
			System.out.println("�����index����~");         // �������ⷵ��null
			return null;
		}
		
		int times = size - index;          // 5 ---> ������2,,, �任����
		while(times != 0) {
			temp = temp.next;
			times--;
		}
		return temp;
	}
	
	// ��������1 -- ÿ�δ�ԭ���������ժȡ
	public static void reverseList(SingleNode head) {
		
		SingleNode reverse = new SingleNode(0, "", "");
		SingleNode trans = reverse;                        // ��Ҫ���Ǹ�������trans���Ϻ��ƣ�reverse��Ϊ�ײ����ֲ���
		while(head.next != null) {
			SingleNode temp = head;
			
			while(temp.next.next != null) {        // ��ʱ��tempΪ���һ���ڵ��ǰһ���ڵ�
				temp = temp.next;
			}
			
			trans.next = temp.next;                // ժȡ���һ���ڵ�
			trans = trans.next;
			temp.next = null;                      // ɾȥ���һ���ڵ�
			temp = head;
		}
		head.next = reverse.next;
	}
	
	// ��������2 -- ���δ�ԭ����˳��ժȡ
	public static void reverseList2(SingleNode head) {
		SingleNode reverse = new SingleNode(0, "", "");
		SingleNode cur = head.next;
		SingleNode next = null;                         // ָ��ǰ�ڵ����һ���ڵ�
		
		while(cur != null) {                            // ��ԭ�����л��нڵ�
			next = cur.next;
			cur.next = reverse.next;
			reverse.next = cur;
			cur = next;
		}
		head.next = reverse.next;
	}
	
	// �����ӡ�����õ�ջ�ķ���
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

// ����SingleLinkedList���й���
class SingleLinkedList{
	SingleNode head = new SingleNode(0, "", "");
		
	public SingleNode getHead() {
		return head;
	}

	// ��ӽڵ�,����β��������˳��
	public void addNode(SingleNode node) {
		// ��������ָ��temp��������
		SingleNode temp = head;
		
		
		while(temp.next != null) {
			temp = temp.next;
		}
		// �ҵ����һ���ڵ�
		temp.next = node;
	}
	
	
	
	// ��ӽڵ㣬����������no����˳��
	// ���ҵ��ڵ���ڲ���λ���е�ǰһ���ڵ㣬�ڽ��в���
	// ���ڵ������Ѿ����ڣ��ڲ���ʧ�ܣ���ʾ��ʾ��Ϣ
	public void addByOrder(SingleNode node) {
		SingleNode temp = head;
		
		while(temp.next != null) {
			if(temp.next.no > node.no || temp.next == null) {   // �ҵ�����λ�õ�ǰһ�����λ��temp,���˳�ѭ��
				break;
			}else if (temp.next.no == node.no) {    // �ñ���Ѿ�����
				System.out.println("������Ѿ����ڣ�����ʧ��~");
				return;
			}
			temp = temp.next;
		}
		node.next = temp.next;
		temp.next = node;
	}
	
	// ����noɾ���ڵ�
	public void delNode(int no) {
		SingleNode temp = head;
		
		while(temp.next != null) {
			if(temp.next.no == no) {      // �ҵ��ýڵ��ǰһ�����temp
				temp.next = temp.next.next;
				return;
			}
			temp = temp.next;
		}
		System.out.println("δ�ҵ����"+no+"�Ľڵ㣬�޷�ɾ��~");
 	}
	
	// ���������ڵ��no�޸ĸ��£���no�����������ʾ��Ϣ
	public void updateList(SingleNode node) {
		SingleNode temp = head.next;
		
		while(temp != null) {
			if(temp.no == node.no) {      //�ҵ��ڵ㣬���޸���Ϣ
				temp.name = node.name;
				temp.nickname = node.nickname;
				return;
			}
			temp = temp.next;
		}
		
		System.out.println("δ�ҵ����"+node.no+"�Ľڵ�");
	}
	
	//��������
	public void list() {
		// ��������ָ��temp��������
		if(head.next == null) {              // ����Ϊ��ֱ�ӷ���
			System.out.println("����Ϊ��~");
			return;
		}
		SingleNode temp = head.next;
		
		while(temp != null) {               // ��ӡ�ڵ���Ϣ
			System.out.println(temp);
			temp = temp.next;
		}
	}	
}

// ����ڵ�
class SingleNode{
	public int no;
	public String name;
	public String nickname;
	public SingleNode next;     // ָ����һ���ڵ�
	
	// ���췽��
	public SingleNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	
	// ��дtoString
	@Override
	public String toString() {
		return "SingleNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}	
}