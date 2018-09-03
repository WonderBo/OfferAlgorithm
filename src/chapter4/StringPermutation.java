/**
 * @description 输入一个字符串，打印出该字符串中字符的所有排列
 */
package chapter4;

public class StringPermutation {

	/**
	 * 
	 * @param str
	 * @description 带条件的排列问题：先求出这些数字的所有排列，然后一一判断每个排列是不是满足题目给定的要求
	 */
	public static void stringPermutation(String str) {
		// 输入验证
		if(str == null || str.length() == 0) {
			return;
		}
		
		char[] charArray = str.toCharArray();
		stringPermutationCore(charArray, 0);
	}
	
	/**
	 * 
	 * @param charArray
	 * @param index
	 * @description 递归解决排列问题：将字符串分解为两部分（首字符和后续字符串）
	 */
	public static void stringPermutationCore(char[] charArray, int index) {
		// 递归边界条件
		if(index == charArray.length - 1) {
			System.out.println(new String(charArray));
		}
		
		for(int i = index; i < charArray.length; i++) {
			// 遍历字符数组（包括第一个字符）与第一个字符进行交换
			char temp = charArray[i];
			charArray[i] = charArray[index];
			charArray[index] = temp;
			
			// 此处 index + 1 不可替换为 ++ index，因为后面有关于index的操作，不能修改index的值
			stringPermutationCore(charArray, index + 1);
			
			// 将交换后的字符交换回来（避免出现重复排列）
			temp = charArray[i];
			charArray[i] = charArray[index];
			charArray[index] = temp;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abc";
		stringPermutation(str);
	}

}
