package arraystack;

// �򵥼�������ֻ֧��һλ���Ӽ��˳�����֧����λ��

public class Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String expression = "-30-3*16+42";
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);

		char ch;
		String numString = "";
		for (int i = 0; i < expression.length(); i++) {
						
			ch = expression.charAt(i);
			
			if(i == 0 && ch == '-') {     // ��λ�Ǹ���
				numStack.push(0);
				operStack.push(ch);
				continue;
			}
			
			if (operStack.isOperator(ch)) { // ����������
				// ���ջ���ղ�������������ȼ�С��ջ�е���������ȼ�
				if(operStack.isEmpty()) {       // �������ջ�գ���ջ
					operStack.push(ch);
					continue;
				}
				
				boolean flag = true;
				
				
				while(flag) {
					if(operStack.isEmpty()) {
						operStack.push(ch);
						flag = false;
					}else if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						int res = operStack.calculate(numStack.pop(), numStack.pop(), operStack.pop());
						numStack.push(res);
					}else {
						operStack.push(ch);
						flag = false;
					}
				}
			} else { // ���������				
				// ��������ֲ������һλ��������Ҫ�ж���һλ
				if(i + 1 == expression.length() || operStack.isOperator(expression.charAt(i + 1))) {
					numString += ch;
					numStack.push(Integer.parseInt(numString));
					numString = "";                                // ÿ��ѹ�����Ҫ��numString����Ϊ��
				}else {        // ��λ��ƴ��
					numString = numString + ch;
				}
			}
		}
		
		// ��ȫ��ɨ������ʽ���������е����������������
		while(!operStack.isEmpty()) {
			int res = operStack.calculate(numStack.pop(), numStack.pop(), operStack.pop());
			numStack.push(res);
		}
		System.out.println("����Ľ���ǣ�"+numStack.peek());
	}
}

class ArrayStack2 {
	int maxSize;
	int[] arr;
	int top = -1; // ջ��ָ���ʼ��Ϊ-1

	public ArrayStack2(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		arr = new int[maxSize];
	}

	// ����
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// �п�
	public boolean isEmpty() {
		return top == -1;
	}

	// ��ջ
	public void push(int n) {
		if (isFull()) {
			System.out.println("ջ��~");
			return;
		}
		arr[++top] = n;
	}

	// ��ջ
	public int pop() {
		if (isEmpty()) {
			// �׳��쳣
			throw new RuntimeException("ջ��~");
		}
		return arr[top--];
	}

	// ����ջ����ֵ
	public int peek() {
		if(isEmpty()) {
			System.out.println("ջ��~");
			// �׳��쳣
		}
		return arr[top];
	}
	// ����
	public void traverse() {
		if (isEmpty()) {
			System.out.println("ջ��~");
			return;
		}
		System.out.print("ջ�е�����Ϊ��");
		for (int i = top; i >= 0; i--) {
			System.out.println(arr[i] + "\t");
		}
		System.out.println("");
	}

	// �ж��ǲ����������
	public boolean isOperator(int oper) {
		if (oper == '+' || oper == '-' || oper == '*' || oper == '/') {
			return true;
		} else {
			return false;
		}
	}

	// ��������������ȼ��������������ֻ��'*'��'/'��'+'��'-'
	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 2;
		} else if (oper == '+' || oper == '-') {
			return 1;
		} else { // �����ڵ������
			return -1;
		}
	}

	// ����
	public int calculate(int num1, int num2, int oper) {
		switch (oper) {
		case '+':
			System.out.println(num2+""+(char)oper+""+num1);
			return num1 + num2;
		case '-':
			System.out.println(num2+""+(char)oper+""+num1);
			return num2 - num1;
		case '*':
			System.out.println(num2+""+(char)oper+""+num1);
			return num2 * num1;
		case '/':
			System.out.println(num2+""+(char)oper+""+num1);
			return num2 / num1;
		default: // �����ڵ�����
			return -1;
		}
	}
}