package arraystack;

import java.util.ArrayList;
import java.util.Stack;

// ��׺���ʽת��׺���ʽ
public class InfixToSuffix {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        System.out.println("ת������б�Ϊ��"+toStringList(expression));
        System.out.println("ת������б�Ϊ��"+transToSuffix(toStringList(expression)));
    }

    public static ArrayList<String> transToSuffix(ArrayList<String> expr){
        Stack<String> operStack = new Stack<>();
        ArrayList<String> resList = new ArrayList<>();

        for(String ele : expr){
            // ��������֣�ֱ�ӱ��浽resList
            if(ele.matches("\\d+")){
                resList.add(ele);
            }else{
                // ���ջ�ջ���ջ��Ϊ��(�����߸ò�����Ϊ"("
                if(operStack.isEmpty() || operStack.peek().equals("(") || ele.equals("(")){
                    operStack.push(ele);
                }else if(ele.equals(")")){
                    // �����������
                    while(!operStack.peek().equals("(")){
                        resList.add(operStack.pop());
                    }
                    operStack.pop();
                }else{
                    // ���������������
                    while(!operStack.isEmpty() && priority(ele) <= priority(operStack.peek())){
                        resList.add(operStack.pop());
                    }
                    operStack.push(ele);
                }
            }
        }
        while (!operStack.isEmpty()){
            // �������еĲ�������resList
            resList.add(operStack.pop());
        }
        return resList;
    }

    // �������ȼ������ȼ�Խ������Խ��
    public static int priority(String oper){
        if(oper.equals("+") || oper.equals("-")){
            return 1;
        }else if(oper.equals("*") || oper.equals("/")){
            return 2;
        }else{
            System.out.println("����������~");
            return -1;
        }
    }

    public static ArrayList<String> toStringList(String expr){
        // expression���浽ArrayList
        ArrayList<String> ls = new ArrayList<>();

        String numString = "";
        numString += "";
        for(int index = 0; index < expr.length(); index++){
            String ch = expr.substring(index, index + 1);

            if(ch.matches("\\d+")){
                // ���������
                numString += ch;
                if (index == expr.length() - 1){
                    // ��������һλ����
                    ls.add(numString);
                    numString = "";
                }else if (!expr.substring(index + 1, index + 2).matches("\\d+")){
                    // �����һλ��������
                    ls.add(numString);
                    numString = "";
                }
            }else{
                ls.add(ch);
            }
        }
        return ls;
    }
}
