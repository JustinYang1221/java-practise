package hank.stack;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author: justin
 * @date: 2022/11/15
 */
public class StackTest {

    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);
        Map<Character, Character> signMap = new HashMap<>();
        signMap.put('(', ')');
        signMap.put('{', '}');
        signMap.put('[', ']');

        Stack<Character> stack = new Stack<>();
        int length = 0;
        while (sc.hasNext()) {
            String input=sc.next();
            length = input.length();
            //Complete the code
            Character tempChar;
            for(int i=0; i< length;i++ ){
                tempChar = input.charAt(i);
                if (stack.isEmpty()){
                    if (tempChar == '}' || tempChar == ']' || tempChar == ')'){
                        stack.push('f');
                        break;
                    }
                    else
                        stack.push(tempChar);
                }else{
                    //stack.peek()!=null && (signMap.get(stack.peek())!=null ) &&
                    if (tempChar == signMap.get(stack.peek())){
                        stack.pop();
                    }else{
                        stack.push(tempChar);
                    }
                }
            }
            if (stack.isEmpty())
                System.out.println(true);
            else
                System.out.println(false);

            stack.clear();
        }

        System.out.println(stack);

    }

    @Test
    public void nullTest(){
        Map<Character, Character> signMap = new HashMap<>();
        signMap.put('(', ')');
        signMap.put('{', '}');
        signMap.put('[', ']');

        Character a = signMap.get('}');
        System.out.println(a);
    }
}
