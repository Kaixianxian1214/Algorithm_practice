package arraystack;

import java.util.Scanner;

public class ArrayStackDemo1 {

	public static void main(String[] args) {
		// Test 111
		
		ArrayStack stack = new ArrayStack(4);
		
		String key = "";
		boolean loop = true;
		Scanner scanner = new Scanner(System.in);
		
		while(loop) {
			System.out.println("show");
			System.out.println("exit");
			System.out.println("push");
			System.out.println("pop");
			System.out.println("?????????????");
			
			key = scanner.next();
			
			switch (key) {       // ???
			case "show":
				stack.traverse();
				break;
			case "push":
				System.out.println("?????????????");
				stack.push(scanner.nextInt());
				break;
			case "pop":
				try {
					System.out.println("??????????"+stack.pop());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case "exit":
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}
		}
		System.out.println("???????~");
		
	}

}

class ArrayStack {
	int maxSize;
	int[] arr;
	int top = -1; // ???????????-1

	public ArrayStack(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		arr = new int[maxSize];
	}

	// ????
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// ????
	public boolean isEmpty() {
		return top == -1;
	}

	// ???
	public void push(int n) {
		if (isFull()) {
			System.out.println("???~");
			return;
		}
		arr[++top] = n;
	}

	// ???
	public int pop() {
		if (isEmpty()) {
			// ?????
			throw new RuntimeException("???~");
		}
		return arr[top--];
	}

	// ????
	public void traverse() {
		if (isEmpty()) {
			System.out.println("???~");
			return;
		}
		System.out.print("????????????");
		for (int i = top; i >= 0; i--) {
			System.out.println(arr[i]+"\t");
		}
		System.out.println("");
	}
}
