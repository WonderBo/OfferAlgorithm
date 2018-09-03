/**
 * @description 数字以 0123456789101112...的格式序列化到一个字符序列中，该序列下标以0开始计数，比如第5位为5，求任意第n位对应的数字
 */
package chapter5;

public class DigitsInSequence {
	
	/**
	 * 
	 * @param n 字符序列的下标
	 * @return int 下标对应数位
	 * @description 为减小时间复杂度，在完全数字序列中找出绝对下标对应的数位 转化为 在相同位数的数字序列范围内找出相对下标对应的数位，从而减少计算次数
	 */
	public int getDigitInSequence(int n) {
		// 输入验证
		if(n < 0) {
			return -1;
		}
		
		int digits = 1;	// 数字位数
		int aboveThreshold = getCountOfNDigit(digits) * digits;	// 位数边界值
		// 循环确定数字的位数
		while(n > aboveThreshold) {
			digits ++;
			aboveThreshold += getCountOfNDigit(digits) * digits;
		}
		
		int indexOfRange = n - (aboveThreshold - getCountOfNDigit(digits) * digits);	// 在digits位数字符序列范围中的相对下标
		int beginNumberOfNDigit = getBeginNumberOfNDigit(digits);	// digits位数的起始数
		
		int number = beginNumberOfNDigit + indexOfRange / digits;	// 确定所在数字
		// 循环除法后取模确定所在数字的具体数位（注意序列下标以0开始计数）
		for(int i = 0; i < digits - 1 - indexOfRange % digits; i++) {
			number /= 10;
		}
		int digit = number % 10;	// 对应数位
	
		return digit;
	}
	
	/**
	 * 
	 * @param digits 数字位数
	 * @return int 该位数数字的数量
	 * @description 求解有digits位的数字的数量
	 */
	public int getCountOfNDigit(int digits) {
		// 输入验证
		if(digits <= 0) {
			return -1;
		}
		
		if(digits == 1) {
			return 10;
		}
		return (int) Math.pow(10, digits) - (int) Math.pow(10, digits - 1);
	}
	
	/**
	 * 
	 * @param digits 数字位数
	 * @return int 该位数数字的起始数
	 * @description 求解有digits位的数字的起始数
	 */
	public int getBeginNumberOfNDigit(int digits) {
		// 输入验证
		if(digits <= 0) {
			return -1;
		}
		
		if(digits == 1) {
			return 0;
		}
		return (int) Math.pow(10, digits - 1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DigitsInSequence digitsInSequence = new DigitsInSequence();
		System.out.println(digitsInSequence.getDigitInSequence(1001));
	}

}
