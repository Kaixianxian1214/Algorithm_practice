package arraystack;

import java.util.ArrayList;
import java.util.Stack;

public class PolandCal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String expression = "4 5 * 8 - 60 + 8 2 / +";   // 中间用空格隔开
		System.out.println("expression"+getStirng(expression));
		
		int res = dealCalculate(getStirng(expression));
		System.out.println("计算的结果为:"+res);
	}

	
	public static ArrayList<String> getStirng(String expression){
		String [] split = expression.split(" ");                   // 按照" "进行分割
		ArrayList<String> res = new ArrayList<String>();
		for(String ele : split) {
			res.add(ele);
		}
		return res;
	}
	
	public static int dealCalculate(ArrayList<String> expr) {
		Stack<String> numStack = new Stack<String>();
		System.out.println("---");
		for(String ele : expr) {
			if(ele.matches("\\d+")) {
				// \d+匹配一个或多个数字，如果是操作数，入栈
				numStack.push(ele);
			}else {
				// 如果是运算符
				int num2 = Integer.parseInt(numStack.pop());
				int num1 = Integer.parseInt(numStack.pop());				
				int res = calculate(num1, num2, ele);
				numStack.push(String.valueOf(res));
			}
		}
		return Integer.parseInt(numStack.pop());
	}
	
    // 四则运算，num1为前操作符，num2为后操作符
	public static int calculate(int num1, int num2, String oper) {
		switch(oper) {
		case "+":
			System.out.println(num1 + num2);
			return num1 + num2;
		case "-":
			System.out.println(num1 - num2);
			return num1 - num2;
		case "*":
			System.out.println(num1 * num2);
			return num1 * num2;
		case "/":
			System.out.println(num1 / num2);
			return num1 / num2;
		default:                      // 无效操作符
			throw new RuntimeException("操作符有误~");
		}
	}
}
