package queue;

import java.util.Scanner;

public class ArrayQueue {

	public static void main(String[] args) {
		Queue queue = new Queue(3);
		Scanner scanner = new Scanner(System.in);
		char key = ' ';
		boolean loop = true;
		
		while(loop) {
			System.out.println("s(show):��ʾ����");
			System.out.println("e(exit):�˳�����");
			System.out.println("a(add):������ݵ�����");
			System.out.println("g(get):�Ӷ���ȡ������");
			System.out.println("h(head):��ʾ�����ײ�����");
			key = scanner.next().charAt(0);      // ����һ���ַ�
			switch(key) {
			case 's':
				try {
					queue.showQueue();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'a':
				System.out.println("������һ������");
				int val = scanner.nextInt();
				queue.addQueue(val);
				break;
			case 'g':       // ȡ�����ݣ����ܴ����쳣
				try {
					int res = queue.getQueue();
					System.out.printf("ȡ����������%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'h':       // ȡ�����ݣ����ܴ����쳣
				try {
					int head = queue.headQueue();
					System.out.printf("ȡ����������%d\n", head);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'e':
				scanner.close();
				loop = false;
				break;
			default:
				break;
			
			}
			
		}
		System.out.println("�����˳�~");
	}

}


// ʹ������ģ����

class Queue{
	private int maxSize; // �������
	private int front;   // ָ���ײ���ǰһλ
	private int rear;    // β��
	private int []arr;   // ���ڴ������
	
	// ���췽��
	public Queue(int maxSize){
		this.maxSize = maxSize;
		front = -1;     //!frontָ�����ͷ��ǰһ��λ��
		rear = -1;
		arr = new int[maxSize];
	}
	
	// ����
	public boolean isFull() {
		return rear == maxSize - 1;
	}
	
	// �п�
	public boolean isEmpty() {
		return front == rear;
	}
	
	//addData
	public void addQueue(int n) {
		if(isFull()) {
			System.out.println("������~");
			return;
		}
		arr[++rear] = n;
	}
	
	//ȡ������
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("���п�~");
		}
		front++;
		return arr[front];
	}
	
	//��ʾ�����е�����
	public void showQueue() {
		if(isEmpty()) {
			throw new RuntimeException("���п�~");
		}
		for(int i = front; i < rear; i++) {
			System.out.printf("%d\t", arr[i+1]);
		}
	}
	
	//��ʾ�����ײ�����
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("���п�~");
		}
		return arr[front+1]; 
	}
	
	

}