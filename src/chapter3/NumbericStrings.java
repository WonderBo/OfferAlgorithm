/**
 * @description 实现一个函数用来判断字符串是否可以表示数值（包括整数与小数）
 */
package chapter3;

import java.util.HashMap;
import java.util.Map;

public class NumbericStrings {

	/**
	 * 
	 * @param str 匹配字符串
	 * @return boolean 匹配结果
	 * @description （1）判断字符串是否可以表示数值，因为数值的多种表现形式，因此需要全面思考问题，而后归纳总结解决问题
	 * 				（2）解决字符串匹配问题需要注意字符串为空问题，以及字符串数组的边界溢出问题，需要随时做相应的判断
	 * 				（3）引用传递的方法中，是将实参的地址传递给形参，此时形参与实参指向相同的地址，如果后面形参重新指向另外的对象，但是实参不变，那么在该方法外，
	 * 			形参将不会再影响原指向对象，因为形参的作用域仅为方法内部，此时同理于值传递（Integer作为方法形参不能传递字符串下标的原因）
	 */
	public boolean isNumberic(String str) {
		// 输入验证
		if(str == null || "".equals(str)) {
			return false;
		}

		int indexOfStr = 0;		// 字符串指针
		boolean isNumberic = false;		//字符串可数值化标志
		
		// 整数部分验证（含符号，非必需的，比如 .1234 = 0.1234）
		indexOfStr = hasSymbol(str, indexOfStr);	//符号

		//由于参数indexOfStr为值传递（int类型），不能保存字符串下标值，而且Integer的value值为final类型，不能更改，如果使用与int类型结果一样，因此只能封装为Map而后赋值传递
		Map<String, Object> integerPartMap = isUnsignedInteger(str, indexOfStr);	//数值
		boolean isIntegerPart = (boolean) integerPartMap.get("result");
		indexOfStr = (int) integerPartMap.get("index");

		isNumberic = isIntegerPart;

		// 小数部分验证（非必需的，比如 1234. = 1234.0 但是整数部分和小数部分至少存在一个）
		if(indexOfStr < str.length() && str.charAt(indexOfStr) == '.') {		//判断前注意字符串边界是否溢出
			indexOfStr ++;

			Map<String, Object> decimalPartMap = isUnsignedInteger(str, indexOfStr);
			boolean isDecimalPart = (boolean) decimalPartMap.get("result");
			indexOfStr = (int) decimalPartMap.get("index");

			isNumberic = isNumberic || isDecimalPart;
		}

		// 指数部分验证（含符号，非必需的，若有则必须格式正确）
		if(indexOfStr < str.length() && (str.charAt(indexOfStr) == 'e' || str.charAt(indexOfStr) == 'E')) {		//判断前注意字符串边界是否溢出
			indexOfStr ++;	
			indexOfStr = hasSymbol(str, indexOfStr);		//符号

			Map<String, Object> indexPartMap = isUnsignedInteger(str, indexOfStr);		//数值
			boolean isIndexPart = (boolean) indexPartMap.get("result");
			indexOfStr = (int) indexPartMap.get("index");

			isNumberic = isNumberic && isIndexPart;
		}

		// 其余部分验证（为空验证）
		if(indexOfStr == str.length()) {
			return isNumberic;
		}

		return false;
	}

	/**
	 * 
	 * @param str 匹配字符串
	 * @param indexOfStr 字符串下标
	 * @return int 现字符串下标
	 * @description 判断存在符号位，如果存在符号位，字符串下标向后移动一位（由于符号位不是必需的，因此不需要判断是否存在，即返回boolean值）
	 */
	public int hasSymbol(String str, int indexOfStr) {
		if(indexOfStr < str.length() && (str.charAt(indexOfStr) == '+' || str.charAt(indexOfStr) == '-')) {		//判断前注意字符串边界是否溢出
			indexOfStr ++;
		}

		return indexOfStr;
	}

	/**
	 * 
	 * @param str 匹配字符串
	 * @param indexOfStr 字符串下标
	 * @return Map 封装了是否为无符号整数的boolean值，以及现字符串下标
	 * @description 判断是否为无符号整数值，如果是则移动字符串下标至不为‘0-9’字符处，由于结果返回多个值，因此需要封装为Map
	 */
	public Map<String, Object> isUnsignedInteger(String str, int indexOfStr) {
		//定义与初始化部分
		int indexTemp = indexOfStr;		//原字符串下标
		Map<String, Object> returnMap = new HashMap<String, Object>();		//Map返回容器
		returnMap.put("result", false);
		returnMap.put("index", indexOfStr);

		//逻辑流程部分
		while(indexOfStr < str.length() && str.charAt(indexOfStr) >= '0' && str.charAt(indexOfStr) <= '9' ) {		//判断前注意字符串边界是否溢出
			indexOfStr ++;
		}

		//返回结果部分
		if(indexOfStr > indexTemp) {	//移动字符串下标，则存在整数
			returnMap.put("result", true);
			returnMap.put("index", indexOfStr);
			return returnMap;
		}	
		return returnMap;
	}

	/**
	 * @param args
	 * @description 测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumbericStrings numbericStrings = new NumbericStrings();
		String str = "-123.54e-21";
		System.out.println(str + "可否转化为数值的结果：" + numbericStrings.isNumberic(str));
	}

}
