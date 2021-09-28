package arraystack;

import java.util.ArrayList;
import java.util.Stack;

public class PolandCal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String expression = "4 5 * 8 - 60 + 8 2 / +";   // �м��ÿո����
		System.out.println("expression"+getStirng(expression));
		
		int res = dealCalculate(getStirng(expression));
		System.out.println("����Ľ��Ϊ:"+res);
	}

	
	public static ArrayList<String> getStirng(String expression){
		String [] split = expression.split(" ");                   // ����" "���зָ�
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
				// \d+ƥ��һ���������֣�����ǲ���������ջ
				numStack.push(ele);
			}else {
				// ����������
				int num2 = Integer.parseInt(numStack.pop());
				int num1 = Integer.parseInt(numStack.pop());				
				int res = calculate(num1, num2, ele);
				numStack.push(String.valueOf(res));
			}
		}
		return Integer.parseInt(numStack.pop());
	}
	
    // �������㣬num1Ϊǰ��������num2Ϊ�������
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
		default:                      // ��Ч������
			throw new RuntimeException("����������~");
		}
	}
}
