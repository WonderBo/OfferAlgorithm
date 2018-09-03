/**
 * @description 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部，请定义一个函数实现字符串左旋转操作的功能
 */
package chapter6;

public class LeftRotateString {

	/**
	 * 
	 * @param str 字符串
	 * @param startIndex 翻转起始下标
	 * @param endIndex 翻转末尾下标
	 * @return
	 * @description 首尾指针翻转字符串指定部分的字符顺序
	 */
	public static String reverseWords(String str, int startIndex, int endIndex) {
		char[] charArray = str.toCharArray();
		while(startIndex < endIndex) {
			char tempChar = charArray[startIndex];
			charArray[startIndex] = charArray[endIndex];
			charArray[endIndex] = tempChar;

			startIndex ++;
			endIndex --;
		}

		return String.valueOf(charArray);
	}
	
	/**
	 * 
	 * @param str 字符串
	 * @param n 字符串分界点
	 * @return
	 * @description 两次翻转字符串（句子/整体 翻转+ 单词/部分翻转）从而实现左旋转字符串
	 * 				倒序：奇数次翻转（eg.1），顺序：偶数次翻转（eg.2），而且翻转句子包含翻转一次单词
	 * 				字符串相关代码（同数组）注意两个问题：1.空指针问题检查； 2.内存访问越界问题判断。
	 */
	public static String leftRotateString(String str, int n) {
		// 输入验证
		if(str == null || str.length() == 0 || n < 0 || n > str.length()) {
			return null;
		}
		
		// 翻转整个字符串（整体角度）
		int startIndex = 0;
		int endIndex = str.length() - 1;
		String returnStr = reverseWords(str, startIndex, endIndex);
		
		// 由于整体字符串被翻转，因此字符串的前后部分也已经被翻转位置
		// 翻转前一部分字符串（部分角度）
		endIndex = str.length() - 1 - n;
		returnStr = reverseWords(returnStr, startIndex, endIndex);
		
		// 翻转后一部分字符串（部分角度）
		startIndex = str.length() - n;
		endIndex = str.length() - 1;
		returnStr = reverseWords(returnStr, startIndex, endIndex);
		return returnStr;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcdefg";
		System.out.println("左旋转后的字符串为：" + leftRotateString(str, 2));
	}

}
