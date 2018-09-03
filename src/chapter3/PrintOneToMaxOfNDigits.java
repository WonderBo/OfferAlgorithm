/**
 * @description 输入数字n，按顺序打印出从1到最大的n位十进制数。比如输入3，则打印出1，2，3......999
 */
package chapter3;

public class PrintOneToMaxOfNDigits {

	/**
	 * 
	 * @param n 十进制数的位数
	 * @description 按顺序打印出从1到最大的n位十进制数，包括三部分：特殊验证（异常验证，边界条件），定义及初始化， 功能及其间逻辑
	 */
	public void print1ToMaxOfNDigits_AddOne(int n) {
		//输入验证
		if(n <= 0) {
			return;
		}

		//定义字符数组（字符串）解决大数问题
		char[] charArrayOfNumber = new char[n];
		for(int i = 0; i < charArrayOfNumber.length; i++) {
			//注意：初始化为字符'0'，而不是整形0(如果为整形0，即为对应的ascii码)
			charArrayOfNumber[i] = '0';
		}

		//主要功能：increment()功能：字符串表示的数字模拟加法；printCharArrayOfNumber()功能：打印字符串表示的数字
		while(!increment(charArrayOfNumber)) {
			printCharArrayOfNumber(charArrayOfNumber);
		}
	}

	/**
	 * 
	 * @param n	十进制数的位数
	 * @description 以数字排列的方式解决问题，运用递归实现n重循环排列每位数字
	 */
	public void print1ToMaxOfNDigits_ArrangeNumber(int n) {
		//输入验证
		if(n <= 0) {
			return;
		}

		//定义字符数组（字符串）解决大数问题
		char[] charArrayOfNumber = new char[n];
		for(int i = 0; i < charArrayOfNumber.length; i++) {
			//注意：初始化为字符'0'，而不是整形0(如果为整形0，即为对应的ascii码)
			charArrayOfNumber[i] = '0';
		}
		
		//主要功能：arrangeNumber()功能：排列数字
		arrangeNumber(charArrayOfNumber, 0);
	}

	/**
	 * 
	 * @param charArrayOfNumber 字符数组
	 * @return boolean
	 * @description 字符串表示的数字模拟加法，直至数字最高位溢出，则循环终止
	 */
	private boolean increment(char[] charArrayOfNumber) {
		boolean isOverFlow = false;					//溢出标志
		int numOfTakeOver = 0;						//进位数
		int numLength = charArrayOfNumber.length;	//字符数组长度

		//从最低位加1开始，加1满10则进位（最高位进位则终止循环），逐渐向高位传动
		for(int i = numLength - 1; i >= 0; i--) {
			//当前位的字符转化为整数(字符做加减法会自动转换为相应的ascii码，ascii码转化为字符需进行强制转换)
			int numBitSum = charArrayOfNumber[i] - '0' + numOfTakeOver;
			numOfTakeOver = 0;

			//如果为最低位则加1
			if(i == numLength - 1) {
				numBitSum ++;
			}

			//满10则进位
			if(numBitSum >= 10) {
				//特殊情况：最高位满10则溢出，即循环终止，并改变溢出标志
				//选择根据最高位的溢出情况而不是选择字符数组与最大的n位十进制数比较是否相等的原因是：时间复杂度，O(1) VS. O(n)
				if(i == 0) {
					isOverFlow = true;
				} else {
					//进位
					numBitSum -= 10;
					numOfTakeOver = 1;
					//当前位的整数转化为字符
					charArrayOfNumber[i] = (char) ('0' + numBitSum);
				}
			} else {
				//不产生进位即无传动，将当前位的整数转化为字符则跳出循环，加1完成
				charArrayOfNumber[i] = (char) ('0' + numBitSum);
				break;
			}
		}

		return isOverFlow;
	}

	/**
	 * 
	 * @param charArrayOfNumber 字符数组
	 * @description 按照用户阅读习惯打印字符串表示的数字(前面的0不能打印)
	 */
	private void printCharArrayOfNumber(char[] charArrayOfNumber) {
		boolean isBeginning0 = true;				//是否还以0开头
		int numLength = charArrayOfNumber.length;	//字符数组长度

		for(int i = 0; i < numLength; i++) {
			//判断条件中的isBeginning0作用在于避免后面数位与‘0’的重复比较（isBeginning0为false则后面条件忽略）
			if(isBeginning0 && charArrayOfNumber[i] != '0') {
				isBeginning0 = false;
			}

			if(!isBeginning0) {
				System.out.print(charArrayOfNumber[i]);
			}
		}
		System.out.println();
	}
	
	/**
	 * 
	 * @param charArrayOfNumber	字符数组
	 * @param index				数组索引
	 * @description 递归实现n位数字的排列（相当于n重0到9的循环）
	 */
	private void arrangeNumber(char[] charArrayOfNumber, int index) {
		//递归边界条件
		if(index == charArrayOfNumber.length) {
			printCharArrayOfNumber(charArrayOfNumber);
			return;
		}
		
		//递归
		for(int i = 0; i < 10; i++) {
			charArrayOfNumber[index] = (char) ('0' + i);
			arrangeNumber(charArrayOfNumber, index + 1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintOneToMaxOfNDigits printOneToMaxOfNDigits = new PrintOneToMaxOfNDigits();
		
		//模拟加1方式实现1到最大的n位十进制数的打印
		//printOneToMaxOfNDigits.print1ToMaxOfNDigits_AddOne(3);
		//数字排列方式实现1到最大的n位十进制数的打印
		printOneToMaxOfNDigits.print1ToMaxOfNDigits_ArrangeNumber(3);
	}

}
