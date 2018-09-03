/**
 * @description 字符串转化为字符数组，并将其中每个空格替换成为%20
 */
package chapter2;

public class ReplaceBlank {

	/**
	 * 
	 * @param str
	 * @return
	 * @description 先将字符串转化为足够长度的字符数组，再利用双指针在当前字符数组从后往前(相较于从前往后会减少移动次数)移动或者替换字符
	 */
	public static String replaceBlank(String str) {
		//输入验证
		if(str == null || str.length() <= 0) {
			return null;
		}
		
		//字符串长度
		int strLength = str.length();
		//计算空格数
		int blankNum = 0;
		for(int i=0; i<strLength; i++) {
			if(str.charAt(i) == ' ') {
				blankNum++;
			}
		}
		
		//字符数组长度(即替换后字符串长度)
		int charArrayLength = strLength + 2 * blankNum;
		char[] charArray = new char[charArrayLength];
		//将未替换的字符串装入字符数组(不能利用toArray方法直接将字符串转化为字符数组，因为替换后可能发生溢出)
		for(int i=0; i<strLength; i++) {
			charArray[i] = str.charAt(i);
		}
		
		//前指针初始指向替换前字符数组字符末尾
		int indexOfStr = strLength - 1;
		//后指针初始化指向替换后字符数组字符末尾
		int indexOfCharArray = charArrayLength - 1;
		while(indexOfStr >= 0 && indexOfCharArray > indexOfStr) {	//循环判断，较于indexOfCharArray != indexOfStr的好处
			if(charArray[indexOfStr] == ' ') {
				charArray[indexOfCharArray] = '0';
				charArray[--indexOfCharArray] = '2';	//此处不可为indexOfCharArray--，否则发生替换错乱，下同
				charArray[--indexOfCharArray] = '%';
			} else {
				charArray[indexOfCharArray] = charArray[indexOfStr];
			}
			indexOfStr--;
			indexOfCharArray--;
		}
		
		//字符数组转化为字符串，不能使用toString方法
		String returnStr = String.valueOf(charArray);
		
		return returnStr;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "we are happy.";
		String returnStr = replaceBlank(str);
		System.out.println(returnStr);
		
	}

}
