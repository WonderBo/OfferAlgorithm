/**
 * @description 输入一个整数n，求1-n这n个整数的十进制表示中1出现的次数
 */
package chapter5;

public class NumberOf1 {

	/**
	 * 
	 * @param n 整数
	 * @return int
	 * @description 利用取模和除法运算纵向求解1-n整数中1出现的次数（时间复杂度为O(n * log n)）
	 */
	public int numberOf1Between1AndN_1(int n) {
		// 输入验证
		if(n <= 0) {
			return 0;
		}

		int number = 0;
		int temp = 0;
		for(int i = 1; i <= n; i++) {
			temp = i;
			while(temp >= 1) {
				// 取模运算判断个位是否为1
				if(temp % 10 == 1) {
					number ++;
				}
				// 除法运算去除个位
				temp /= 10;
			}
		}

		return number;
	}
	
	/**
	 * 
	 * @param n 整数
	 * @return int
	 * @description 利用分割整数n纵向求解1-n整数中1出现的次数（时间复杂度为O(log n)）
	 */
	public int numberOf1Between1AndN_2(int n) {
		// 输入验证
		if(n <= 0) {
			return 0;
		}
		
		// 将整型转换为字符串格式
		String numberString = new Integer(n).toString();
		
		return numberOf1(numberString);
	}

	/**
	 * 
	 * @param numberString 整数的字符串格式
	 * @return int
	 * @description 利用分割整数n纵向求解1-n整数中1出现的次数
	 */
	public int numberOf1(String numberString) {
		// 输入验证
		if(numberString == null || numberString.length() == 0) {
			return 0;
		}
		
		// 递归边界条件
		int firstNumber = numberString.charAt(0) - '0';
		if(numberString.length() == 1 && firstNumber == 0) {
			return 0;
		}
		if(numberString.length() == 1 && firstNumber > 0) {
			return 1;
		}
		
		// 假设numberString为“21345”，分割numberString为3部分
		int numberOf1FirstDigit = 0;	// 数字10000-19999的第一位中1的数目
		int numberOf1OtherDigit = 0;	// 数字1346-21345除第一位之外的数位中1的数目
		int numberOf1Recursive = 0;		// 数字1-1345中1的数目
		
		// 当数字第一位为1或者为其他 两种情况
		if(firstNumber == 1) {
			numberOf1FirstDigit = Integer.valueOf(numberString) - (int) Math.pow(10, numberString.length() - 1) * firstNumber + 1;
		} else if(firstNumber > 1) {
			numberOf1FirstDigit = (int) Math.pow(10, numberString.length() - 1);
		}
		
		// 求解数字1346-21345除第一位之外的数位中1的数目需要做排列组合（注意数字范围是否为10的n次方，否则不能做排列组合）
		numberOf1OtherDigit = firstNumber * (numberString.length() - 1) * (int) Math.pow(10, numberString.length() - 2);
		// 求解数字1-1345中1的数目需要做递归（注意整型与字符串做递归的区别）
		numberOf1Recursive = numberOf1(new Integer(Integer.valueOf(numberString) - (int) Math.pow(10, numberString.length() - 1) * firstNumber).toString());
		
		return numberOf1FirstDigit + numberOf1OtherDigit + numberOf1Recursive;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumberOf1 numberOf1 = new NumberOf1();
		int n = 21345;
		System.out.println("纵向求解1-" + n +"整数中1出现的次数" + numberOf1.numberOf1Between1AndN_1(n));
		System.out.println("横向求解1-" + n +"整数中1出现的次数" + numberOf1.numberOf1Between1AndN_2(n));
	}

}
