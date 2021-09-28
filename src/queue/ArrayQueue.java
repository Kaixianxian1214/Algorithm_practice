package queue;

import java.util.Scanner;

public class ArrayQueue {

	public static void main(String[] args) {
		Queue queue = new Queue(3);
		Scanner scanner = new Scanner(System.in);
		char key = ' ';
		boolean loop = true;
		
		while(loop) {
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出队列");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列取出数据");
			System.out.println("h(head):显示队列首部数据");
			key = scanner.next().charAt(0);      // 接受一个字符
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
				System.out.println("请输入一个数：");
				int val = scanner.nextInt();
				queue.addQueue(val);
				break;
			case 'g':       // 取出数据，可能存在异常
				try {
					int res = queue.getQueue();
					System.out.printf("取出的数据是%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'h':       // 取出数据，可能存在异常
				try {
					int head = queue.headQueue();
					System.out.printf("取出的数据是%d\n", head);
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
		System.out.println("程序退出~");
	}

}


// 使用数组模拟类

class Queue{
	private int maxSize; // 最大容量
	private int front;   // 指向首部的前一位
	private int rear;    // 尾部
	private int []arr;   // 用于存放数据
	
	// 构造方法
	public Queue(int maxSize){
		this.maxSize = maxSize;
		front = -1;     //!front指向队列头的前一个位置
		rear = -1;
		arr = new int[maxSize];
	}
	
	// 判满
	public boolean isFull() {
		return rear == maxSize - 1;
	}
	
	// 判空
	public boolean isEmpty() {
		return front == rear;
	}
	
	//addData
	public void addQueue(int n) {
		if(isFull()) {
			System.out.println("队列满~");
			return;
		}
		arr[++rear] = n;
	}
	
	//取出数据
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列空~");
		}
		front++;
		return arr[front];
	}
	
	//显示队列中的数据
	public void showQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列空~");
		}
		for(int i = front; i < rear; i++) {
			System.out.printf("%d\t", arr[i+1]);
		}
	}
	
	//显示队列首部数据
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列空~");
		}
		return arr[front+1]; 
	}
	
	

}