/**
 * @description 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理
 */
package chapter6;

public class ReverseWordsInSentence {

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
	 * @return
	 * @description 两次翻转字符串（句子/整体 翻转+ 单词/部分翻转）从而实现翻转单词顺序
	 * 				倒序：奇数次翻转（eg.1），顺序：偶数次翻转（eg.2），而且翻转句子包含翻转一次单词
	 * 				字符串相关代码（同数组）注意两个问题：1.空指针问题检查； 2.内存访问越界问题判断。
	 */
	public static String reverseWordsInSentence(String str) {
		// 输入验证
		if(str == null || str.length() == 0) {
			return null;
		}

		int startIndex = 0;
		int endIndex = str.length() - 1;
		// 翻转整个英文句子（整体角度）
		String returnStr = reverseWords(str, startIndex, endIndex);

		// 翻转句子中的每个英文单词（部分角度）
		endIndex = 0;
		while(startIndex < returnStr.length()) {
			// 翻转字符串要注意末尾指针到达字符串最后一个字符时的特殊情况，并且要防止指针越界
			if(returnStr.charAt(startIndex) != ' ' && (endIndex < returnStr.length() && returnStr.charAt(endIndex) != ' ')) {
				endIndex ++;
			} else if(returnStr.charAt(startIndex) != ' ' && (endIndex == returnStr.length() || returnStr.charAt(endIndex) == ' ')) {
				// 翻转单词
				returnStr = reverseWords(returnStr, startIndex, -- endIndex);
				endIndex ++;
				startIndex = endIndex;
			} else {
				startIndex ++;
				endIndex ++;
			}
		}

		return returnStr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "i am a student.";
		System.out.println("翻转后的字符串为：" + reverseWordsInSentence(str));
	}

}
