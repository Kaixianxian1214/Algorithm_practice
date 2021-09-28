package arraystack;

import java.util.ArrayList;
import java.util.Stack;

// 中缀表达式转后缀表达式
public class InfixToSuffix {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        System.out.println("转换后的列表为："+toStringList(expression));
        System.out.println("转换后的列表为："+transToSuffix(toStringList(expression)));
    }

    public static ArrayList<String> transToSuffix(ArrayList<String> expr){
        Stack<String> operStack = new Stack<>();
        ArrayList<String> resList = new ArrayList<>();

        for(String ele : expr){
            // 如果是数字，直接保存到resList
            if(ele.matches("\\d+")){
                resList.add(ele);
            }else{
                // 如果栈空或者栈顶为“(”或者该操作符为"("
                if(operStack.isEmpty() || operStack.peek().equals("(") || ele.equals("(")){
                    operStack.push(ele);
                }else if(ele.equals(")")){
                    // 如果是右括号
                    while(!operStack.peek().equals("(")){
                        resList.add(operStack.pop());
                    }
                    operStack.pop();
                }else{
                    // 如果是其他操作符
                    while(!operStack.isEmpty() && priority(ele) <= priority(operStack.peek())){
                        resList.add(operStack.pop());
                    }
                    operStack.push(ele);
                }
            }
        }
        while (!operStack.isEmpty()){
            // 弹出所有的操作符到resList
            resList.add(operStack.pop());
        }
        return resList;
    }

    // 返回优先级，优先级越大数字越大
    public static int priority(String oper){
        if(oper.equals("+") || oper.equals("-")){
            return 1;
        }else if(oper.equals("*") || oper.equals("/")){
            return 2;
        }else{
            System.out.println("操作符有误~");
            return -1;
        }
    }

    public static ArrayList<String> toStringList(String expr){
        // expression保存到ArrayList
        ArrayList<String> ls = new ArrayList<>();

        String numString = "";
        numString += "";
        for(int index = 0; index < expr.length(); index++){
            String ch = expr.substring(index, index + 1);

            if(ch.matches("\\d+")){
                // 如果是数字
                numString += ch;
                if (index == expr.length() - 1){
                    // 如果是最后一位数字
                    ls.add(numString);
                    numString = "";
                }else if (!expr.substring(index + 1, index + 2).matches("\\d+")){
                    // 如果下一位不是数字
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
