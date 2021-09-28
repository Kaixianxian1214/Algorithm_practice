package linkedlist;

// 约瑟夫问题

public class Josphu {

	public static void main(String[] args) {
		// Test

		// 构建
		CircleSingleList circle = new CircleSingleList(5);

		// test 遍历
		circle.traverse();

		circle.solution(5, 1, 1);

	}

}

class CircleSingleList {
	public Node1 first; // 不设置头节点

	// 根据num创建链表
	public CircleSingleList(int num) { // 给定创建的节点数目
		if (num < 1) { // 给定节点非法
			System.out.println("给定节点数非法~");
			return;
		}

		Node1 last = first; // last指向最后一个节点
		for (int i = 1; i <= num; i++) {
			Node1 addNode = new Node1(i);

			if (i != 1) {
				addNode.next = first;
				last.next = addNode;
			} else { // 需要添加第一个节点时候的单独情况
				first = addNode;
				addNode.next = first;
			}
			last = addNode;
		}
	}

	// 遍历链表
	public void traverse() {
		if (first == null) {
			System.out.println("链表空~");
			return;
		}

		Node1 cur = first;
		do { // 此处要保证只有一个节点的时候能够遍历到
			System.out.println(cur.toString());
		} while ((cur = cur.next) != first);
	}

	// 约瑟夫问题求解
	public void solution(int num, int m, int k) { // 总数num,从第k个开始,报数m次
		// 校验参数
		if (num < k || k < 1 || first == null) {
			System.out.println("参数有误~");
		}

		// 从第k个位置开始
		for (int i = 0; i < k - 1; i++) {
			first = first.next;
		}
		Node1 temp = first; // 辅助变量，指向要出局的前一个位置
		while (temp.next != first) {
			temp = temp.next;
		}

		while (temp != first) {
			// 报数m次
			for (int i = 0; i < m - 1; i++) {
				first = first.next;
				temp = temp.next;
			}

			// 出局first处的节点
			System.out.println("出局的节点是" + first.toString());
			first = first.next;
			temp.next = first;
		}

		System.out.println("最后剩下的玩家是" + first.toString());
	}

}

class Node1 {
	public int no;
	public Node1 next;

	public Node1(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "Node1 [no=" + no + "]";
	}
}
