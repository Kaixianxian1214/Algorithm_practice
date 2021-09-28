package queue;

import java.util.Scanner;


public class CircleQueue {

	public static void main(String[] args) {
		// ���Զ���
		
		cirQueue queue = new cirQueue(4);        // ��ʱ���е����洢Ϊ3����Ԫ
		Scanner scanner = new Scanner(System.in);
		char key = ' ';
		boolean loop = true;
		
		while(loop) {
			System.out.println("s(show):��ʾ����");
			System.out.println("e(exit):�˳�����");
			System.out.println("a(add):������ݵ�����");
			System.out.println("g(get):�Ӷ���ȡ������");
			System.out.println("h(head):��ʾ�����ײ�����");
			key = scanner.next().charAt(0);
			switch(key) {
			case 's':
				try {
					queue.showQueue();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'a':
				System.out.println("������һ����~");
				int val = scanner.nextInt();
				queue.addQueue(val);
				break;
			case 'g':
				try {
					int res = queue.getQueue();
					System.out.println("ȡ���������ǣ�"+res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int head = queue.headQueue();
					System.out.println("�ײ���"+head);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				scanner.close();
				loop = false;
				break;
			}
		}
		System.out.println("�����˳�~");
	}
}

class cirQueue{
	private int maxSize; // �������
	private int front;   // �ײ�
	private int rear;    // ָ��β������һλ
	private int []arr;   // ���ڴ������
	
	// ���췽��
	public cirQueue(int maxSize){
		this.maxSize = maxSize;
		arr = new int[maxSize];
	}
	
	// �п�
	public boolean isEmpty() {
		return front == rear;
	}
	
	// ����
	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}
	
	// addQ
	public void addQueue(int n) {
		if(isFull()) {
			System.out.println("������~");
			return;
		}
		arr[rear] = n;
		rear = (rear + 1) % maxSize;
	}
	
	// getQ
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("���п�~");
		}
		int val = arr[front];
		front = (front + 1) % maxSize;
		return val;
	}
	
	public int queueSize() {
		return (rear + maxSize - front) % maxSize;
	}
	
	// show
	public void showQueue(){
		if(isEmpty()) {
			throw new RuntimeException("���п�~");
		}
		for(int i = front; i < front + queueSize(); i++) {
			System.out.printf("%d\t", arr[i % maxSize]);
		}
		System.out.println("");;
	}
	
	// head
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("���п�~");
		}
		return arr[front];
	}
}