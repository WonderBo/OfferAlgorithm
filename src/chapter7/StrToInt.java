/**
 * @description 字符串转换为整型（关键在于考虑并解决各种特殊输入情况）
 */
package chapter7;

public class StrToInt {
	/**
	 * 
	 * @param str 字符串
	 * @return
	 * @throws Exception
	 * @description 将字符串转化为整型，注意各种特殊输入类型
	 */
	public static int strToInt(String str) throws Exception {
		// 输入验证
		if(str == null || str.equals("")) {
			throw new Exception("输入为空");
		}

		char[] charArray = str.toCharArray();
		int startIndex = 0;
		boolean positive = true;
		// 考虑符号位
		if(charArray[0] == '+') {
			startIndex ++;
		} else if(charArray[0] == '-') {
			startIndex ++;
			positive = false;
		}
		long resultNum = strToIntCore(charArray, startIndex, positive);
		
		return (int) resultNum;
	}

	/**
	 * 
	 * @param charArray 字符数组
	 * @param startIndex 数字字符开始下标
	 * @param positive 是否为正
	 * @return long 返回long类型的关键在于long类型的精度比int类型高，相当于一个数字容器，可以判断整型是否发生溢出
	 * @throws Exception
	 * @description 利用循环遍历字符数组，累加计算从而将将字符串转化为整型
	 */
	public static long strToIntCore(char[] charArray, int startIndex, boolean positive) throws Exception {
		long returnNum = 0;
		int flag = 1;
		if(!positive) {
			flag = -1;
		}
		
		for(int i = startIndex; i < charArray.length; i++){
			// 考虑非数字字符
			if(charArray[i] >= '0' && charArray[i] <= '9') {
				// 对字符做运算(加减等)时，会将字符隐式转化为其Ascii编码中对应的十进制数，此处是将字符型数字(减48)转化为整数型数字
				returnNum = returnNum * 10 + flag * (charArray[i] - '0');
				
				// 考虑整数发生上溢出或者下溢出（需要在循环内部判断，在外部有可能long类型也无法表达该字符串数字，从而损失精度导致错误判断）
				if((positive && returnNum > 0x7FFFFFFF) || (!positive && returnNum < 0x80000000)) {
					throw new Exception("整数溢出");
				}
			} else {
				throw new Exception("输入非法字符");
			}
		}

		return returnNum;
	}

	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		System.out.println(strToInt("-1234567890"));
	}
}
