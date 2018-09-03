/**
 * @description 将一个十进制正数转换为给定的n进制数（通常用于唯一数字映射成其他格式(范围为n且固定)，比如将序号映射为ID）
 */
package chapter2;

import java.util.Scanner;
import java.util.Stack;

public class DecimalismToNSystem {

	/**
	 * 
	 * @param number 十进制正数
	 * @param n 进制
	 * @return String
	 * @description 利用栈和取模，除法运算将一个十进制正数转换为给定的n进制数
	 */
	public String decimalismToNSystem(int number, int n) {
		// 输入验证
		if(number < 0 || n < 2) {
			return null;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		// 循环入栈
		while(number != 0) {
			stack.push(number % n);
			number /= n;
		}

		StringBuffer stringBuffer = new StringBuffer();
		// 循环出栈
		while(stack.size() != 0) {
			stringBuffer.append(stack.pop());
		}

		return stringBuffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecimalismToNSystem decimalismToNSystem = new DecimalismToNSystem();
		Scanner scanner = new Scanner(System.in);  
		System.out.print("请输入一个十进制数：");  
		int number = scanner.nextInt();  
		System.out.print("请输入转化进制：");  
		int n = scanner.nextInt();
		scanner.close();
		System.out.println("转化后是数为：" + decimalismToNSystem.decimalismToNSystem(number, n));
	}

}
