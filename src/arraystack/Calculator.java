package arraystack;

// 简单计算器，只支持一位数加减乘除，不支持两位数

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
			
			if(i == 0 && ch == '-') {     // 首位是负号
				numStack.push(0);
				operStack.push(ch);
				continue;
			}
			
			if (operStack.isOperator(ch)) { // 如果是运算符
				// 如果栈不空并且新运算符优先级小于栈中的运算符优先级
				if(operStack.isEmpty()) {       // 如果符号栈空，入栈
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
			} else { // 如果是数字				
				// 如果该数字不是最后一位，不是需要判断下一位
				if(i + 1 == expression.length() || operStack.isOperator(expression.charAt(i + 1))) {
					numString += ch;
					numStack.push(Integer.parseInt(numString));
					numString = "";                                // 每次压入后需要对numString重置为空
				}else {        // 多位数拼接
					numString = numString + ch;
				}
			}
		}
		
		// 当全部扫描完表达式，弹出所有的数和运算符并计算
		while(!operStack.isEmpty()) {
			int res = operStack.calculate(numStack.pop(), numStack.pop(), operStack.pop());
			numStack.push(res);
		}
		System.out.println("计算的结果是："+numStack.peek());
	}
}

class ArrayStack2 {
	int maxSize;
	int[] arr;
	int top = -1; // 栈顶指针初始化为-1

	public ArrayStack2(int maxSize) {
		// TODO Auto-generated constructor stub
		this.maxSize = maxSize;
		arr = new int[maxSize];
	}

	// 判满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// 判空
	public boolean isEmpty() {
		return top == -1;
	}

	// 入栈
	public void push(int n) {
		if (isFull()) {
			System.out.println("栈满~");
			return;
		}
		arr[++top] = n;
	}

	// 出栈
	public int pop() {
		if (isEmpty()) {
			// 抛出异常
			throw new RuntimeException("栈空~");
		}
		return arr[top--];
	}

	// 返回栈顶的值
	public int peek() {
		if(isEmpty()) {
			System.out.println("栈空~");
			// 抛出异常
		}
		return arr[top];
	}
	// 遍历
	public void traverse() {
		if (isEmpty()) {
			System.out.println("栈空~");
			return;
		}
		System.out.print("栈中的数据为：");
		for (int i = top; i >= 0; i--) {
			System.out.println(arr[i] + "\t");
		}
		System.out.println("");
	}

	// 判断是不是运算符？
	public boolean isOperator(int oper) {
		if (oper == '+' || oper == '-' || oper == '*' || oper == '/') {
			return true;
		} else {
			return false;
		}
	}

	// 设置运算符的优先级，假设运算符中只有'*'、'/'、'+'、'-'
	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 2;
		} else if (oper == '+' || oper == '-') {
			return 1;
		} else { // 不存在的运算符
			return -1;
		}
	}

	// 计算
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
		default: // 不存在的运算
			return -1;
		}
	}
}