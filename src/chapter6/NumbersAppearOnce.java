/**
 * @description 一个整型数组中除了两个数字之外，其他数字都出现了两次，请找出这两个只出现一次的数字，时间复杂度为O(n)，空间复杂度为O(1)
 */
package chapter6;

public class NumbersAppearOnce {

	/**
	 * 
	 * @param intArray 整型数组
	 * @return 两个只出现一次的数字
	 * @description 利用异或的性质（任何一个数字异或他自己都等于0），将该问题转化为选择合适条件划分数组（位运算：利用二进制0/1的特性进行分组）
	 */
	public static int[] getNumbersAppearOnce(int[] intArray) {
		// 输入验证
		if(intArray == null || intArray.length < 2) {
			return null;
		}
		
		int[] returnArray = new int[2];
		
		// 计算数组所有元素异或结果
		int resultExclusiveOR = 0;
		for(int i = 0; i < intArray.length; i++) {
			resultExclusiveOR ^= intArray[i];
		}
		
		// 按照二进制形式从右往左第一位为1的位置，对数组元素进行划分（但不需要辅助数组保存分组结果），并根据异或特性，分别计算出各小组出现一次的数字
		int firstBitOf1 = getFirstBitOf1(resultExclusiveOR);
		for(int i = 0; i < intArray.length; i++) {
			if(is1OfBitN(intArray[i], firstBitOf1)) {
				returnArray[0] ^= intArray[i];
			} else {
				returnArray[1] ^= intArray[i];
			}
		}
		
		return returnArray;
	}
	
	/**
	 * 
	 * @param num 整数
	 * @return 利用位运算求出二进制形式从右往左第一位为1的位置
	 */
	public static int getFirstBitOf1(int num) {
		int indexBit = 0;
		while(((num & 1) == 0) && (indexBit < 32)) {	// indexBit < 32 防止异常输入num = 0时出现死循环
			num = num >> 1;
			indexBit ++;
		}
		
		return indexBit;
	}
	
	/**
	 * 
	 * @param num 整数
	 * @param n 第n位
	 * @return 整数的第n位是否为1
	 * @description 利用位运算求出整数的第n位是否为1
	 */
	public static boolean is1OfBitN(int num, int n) {
		num = num >> n;
		if((num & 1) == 1) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = {2, 4, 3, 6, 3, 2, 5, 5};
		int[] resultArray = getNumbersAppearOnce(intArray);
		System.out.print("数组中只出现一次的两个数字为：");
		for(int i = 0; i < resultArray.length; i++) {
			System.out.print(resultArray[i] + " ");
		}
	}

}
