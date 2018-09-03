/**
 * @description 字符串中第一个只出现一次的字符
 */
package chapter5;

public class FirstNotRepeatingChar {

	/**
	 * 
	 * @param str 字符串
	 * @return
	 * @description 利用辅助数组得到第一个只出现一次的字符（时间复杂度为O(n)，空间复杂度为O(1)）
	 */
	public static char getFirstNotRepeatingChar(String str) {
		// 输入验证
		if(str == null) {
			return '\0';
		}
		
		final int charNum = 256;	// char为8bit，因此最多2的8次方个字符
		int[] timesArray = new int[charNum];	// 数组下标为字符ASCII码值，值为字符出现次数
		char[] charArray = str.toCharArray();	// 字符串转换得到的字符数组
		// 第一次遍历计算出字符出现次数
		for(int i = 0; i < charArray.length; i++) {
			timesArray[charArray[i]] ++;
		}
		
		// 第二次遍历取出第一个出现次数为1的字符
		for(int i = 0; i < charArray.length; i++) {
			if(timesArray[charArray[i]] == 1) {
				return charArray[i];
			}
		}
		
		return '\0';
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abaccdeff";
		System.out.println("第一次出现的字符为：" + FirstNotRepeatingChar.getFirstNotRepeatingChar(str));
	}

}
