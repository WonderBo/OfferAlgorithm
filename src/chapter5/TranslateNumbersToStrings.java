/**
 * @description 给定一个数字，按照如下规则翻译成字符串：0：a，1：b......25：z，一个数字可能有多种翻译，请实现函数计算出一个数字的翻译方法种数
 */
package chapter5;

public class TranslateNumbersToStrings {

	/**
	 * 
	 * @param number 数字
	 * @return int 翻译种数
	 * @description 参数验证 + 动态规划解决问题
	 */
	public int getTranslationCount(int number) {
		// 输入验证
		if(number < 0) {
			return 0;
		}
		
		// 转化为字符串格式
		String numberString = new Integer(number).toString();
		
		return getTranslationCount(numberString);
	}
	
	/**
	 * 
	 * @param numberString 数字字符串
	 * @return int 翻译种数
	 * @description 动态规划：从上往下递归考虑问题，从下往上循环求解问题。引入存储数组存储中间结果避免重复计算，从递归边界条件以此往前推算
	 */
	public int getTranslationCount(String numberString) {
		// 中间结果存储数组（长度与字符串长度相同）
		int[] translationCount = new int[numberString.length()];
		int count = 0;
		
		// 从数组尾开始遍历
		for(int i = numberString.length() - 1; i >= 0; i--) {
			// 如果为数组最后一项则赋值1
			if(i == numberString.length() - 1) {
				count = 1;
			}
			
			// 当指针不在数组尾，则由该位置的数位与后位置的数位一起决定计算方法
			if(i < numberString.length() - 1) {
				int behindDigit = numberString.charAt(i + 1) - '0';
				int beforeDigit = numberString.charAt(i) - '0';
				int combineNumber = beforeDigit * 10 + behindDigit;
				
				if(combineNumber >= 10 && combineNumber <= 25) {
					// 如果指针在数组倒数第二位，防止指针越界，需要做特殊处理而不能根据公式求解
					if( i == numberString.length() - 2) {
						count += 1;
					} else {
						count = translationCount[i + 2] + translationCount[i + 1];
					}
				}
			}
			// 将中间结果存储到数组中，以备后用
			translationCount[i] = count;
		}
		
		return translationCount[0];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TranslateNumbersToStrings translateNumbersToStrings = new TranslateNumbersToStrings();
		System.out.println(translateNumbersToStrings.getTranslationCount(12258));
	}

}
