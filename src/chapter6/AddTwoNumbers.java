/**
 * @description 写一个函数，求两个整数之和，要求在函数体内不得使用“+”，“-”，“*”，“/”四则运算符号
 */
package chapter6;

public class AddTwoNumbers {

	/**
	 * 
	 * @param num1 加数1
	 * @param num2 加数2
	 * @return
	 * @description 利用二进制分析整数问题，利用位运算解决整数问题，从而求得两个整数之和
	 * 				步骤一：各位相加但不计进位	解决：异或
	 * 				步骤二：记下进位			解决：位与运算
	 * 				步骤三：将前两步的结果相加	解决：递归或者循环，直至进位为0
	 */
	public static int getSumOfTwoNumbers(int num1, int num2) {
		int sum;
		int carry;
		do{
			sum = num1 ^ num2;
			carry = (num1 & num2) << 1;
			
			num1 = sum;
			num2 = carry;
		} while(num2 != 0);
		
		return sum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("两个整数之和：" + getSumOfTwoNumbers(5, 17));
	}

}
