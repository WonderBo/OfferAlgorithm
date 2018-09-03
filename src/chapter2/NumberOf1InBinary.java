/**
 * @description 二进制中1的个数（输入一整数，输出该数二进制表示中1的个数）
 */
package chapter2;

public class NumberOf1InBinary {
	
	/**
	 * 
	 * @param num	十进制数
	 * @return int	十进制数字的二进制表达中1的数量
	 * @description 由于将数字右移需要考虑数字的正负，因此换成将flag数左移达到相同的效果。不过要考虑移动的结束条件。
	 * 				缺点在于循环次数与整数二进制位数相关
	 */
	public static int numberOf1_MoveLeft(int num) {
		int count = 0;
		
		int flag = 1;
		while(flag > 0) {
			if((num & flag) > 0) {
				count ++;
			}
			
			//需要左移31次直至 1 所在的位数溢出（整形4个字节，最左位为符号位，其余31位为数值位）
			flag = flag << 1;
		}
		
		return count;
	}
	
	/**
	 * 
	 * @param num	十进制数
	 * @return int	十进制数字的二进制表达中1的数量
	 * @description 将数字减一后，从最右边的1开始，其右边各数位相当于取反，再与原数做位与运算，相当于将最右的1变成0，而保留左边的各位（举一反三）。
	 * 				优点在于整数的二进制表示有多少个1则进行多少次循环（这样的操作）
	 */
	public static int numberOf1_SubtractAnd(int num) {
		int count = 0;
		
		while(num > 0) {
			//减1后与操作
			num = (num - 1) & num;
			count ++;
		}
		
		return count;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numberOf1 = numberOf1_MoveLeft(7);
		System.out.println("二进制中1的个数为：" + numberOf1);
		
		numberOf1 = numberOf1_SubtractAnd(63);
		System.out.println("二进制中1的个数为：" + numberOf1);
	}

}
