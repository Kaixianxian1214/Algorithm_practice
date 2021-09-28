package queue;

import java.util.Scanner;


public class CircleQueue {

	public static void main(String[] args) {
		// 测试队列
		
		cirQueue queue = new cirQueue(4);        // 此时队列的最大存储为3个单元
		Scanner scanner = new Scanner(System.in);
		char key = ' ';
		boolean loop = true;
		
		while(loop) {
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出队列");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列取出数据");
			System.out.println("h(head):显示队列首部数据");
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
				System.out.println("请输入一个数~");
				int val = scanner.nextInt();
				queue.addQueue(val);
				break;
			case 'g':
				try {
					int res = queue.getQueue();
					System.out.println("取出的数据是："+res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int head = queue.headQueue();
					System.out.println("首部是"+head);
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
		System.out.println("程序退出~");
	}
}

class cirQueue{
	private int maxSize; // 最大容量
	private int front;   // 首部
	private int rear;    // 指向尾部的下一位
	private int []arr;   // 用于存放数据
	
	// 构造方法
	public cirQueue(int maxSize){
		this.maxSize = maxSize;
		arr = new int[maxSize];
	}
	
	// 判空
	public boolean isEmpty() {
		return front == rear;
	}
	
	// 判满
	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}
	
	// addQ
	public void addQueue(int n) {
		if(isFull()) {
			System.out.println("队列满~");
			return;
		}
		arr[rear] = n;
		rear = (rear + 1) % maxSize;
	}
	
	// getQ
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列空~");
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
			throw new RuntimeException("队列空~");
		}
		for(int i = front; i < front + queueSize(); i++) {
			System.out.printf("%d\t", arr[i % maxSize]);
		}
		System.out.println("");;
	}
	
	// head
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列空~");
		}
		return arr[front];
	}
}