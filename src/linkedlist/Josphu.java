package linkedlist;

// Լɪ������

public class Josphu {

	public static void main(String[] args) {
		// Test

		// ����
		CircleSingleList circle = new CircleSingleList(5);

		// test ����
		circle.traverse();

		circle.solution(5, 1, 1);

	}

}

class CircleSingleList {
	public Node1 first; // ������ͷ�ڵ�

	// ����num��������
	public CircleSingleList(int num) { // ���������Ľڵ���Ŀ
		if (num < 1) { // �����ڵ�Ƿ�
			System.out.println("�����ڵ����Ƿ�~");
			return;
		}

		Node1 last = first; // lastָ�����һ���ڵ�
		for (int i = 1; i <= num; i++) {
			Node1 addNode = new Node1(i);

			if (i != 1) {
				addNode.next = first;
				last.next = addNode;
			} else { // ��Ҫ��ӵ�һ���ڵ�ʱ��ĵ������
				first = addNode;
				addNode.next = first;
			}
			last = addNode;
		}
	}

	// ��������
	public void traverse() {
		if (first == null) {
			System.out.println("�����~");
			return;
		}

		Node1 cur = first;
		do { // �˴�Ҫ��ֻ֤��һ���ڵ��ʱ���ܹ�������
			System.out.println(cur.toString());
		} while ((cur = cur.next) != first);
	}

	// Լɪ���������
	public void solution(int num, int m, int k) { // ����num,�ӵ�k����ʼ,����m��
		// У�����
		if (num < k || k < 1 || first == null) {
			System.out.println("��������~");
		}

		// �ӵ�k��λ�ÿ�ʼ
		for (int i = 0; i < k - 1; i++) {
			first = first.next;
		}
		Node1 temp = first; // ����������ָ��Ҫ���ֵ�ǰһ��λ��
		while (temp.next != first) {
			temp = temp.next;
		}

		while (temp != first) {
			// ����m��
			for (int i = 0; i < m - 1; i++) {
				first = first.next;
				temp = temp.next;
			}

			// ����first���Ľڵ�
			System.out.println("���ֵĽڵ���" + first.toString());
			first = first.next;
			temp.next = first;
		}

		System.out.println("���ʣ�µ������" + first.toString());
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
