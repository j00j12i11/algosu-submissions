import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String origin = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<origin.length(); i++) {
			char cur = origin.charAt(i);
			
			if (cur >= 'A' && cur <= 'Z') {
				sb.append(cur);
				continue;
			} 
			if (cur == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.pop());
				}
				if (!stack.isEmpty() && stack.peek() == '(') {
					stack.pop(); 
				}
				continue;
			}
			if (cur == '(') {
				stack.add(cur);
			} else {
				while(!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(cur)) {
					sb.append(stack.pop());
				}
				stack.push(cur);
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);
	}
	
	static int getPriority(char operator) {
		switch(operator) {
		case '+': 
		case '-':
			return 1; 
		case '*':
		case '/':
			return 2;
		}
		return 0;
		// 맨 마지막은 여는괄호
	}
	
}
