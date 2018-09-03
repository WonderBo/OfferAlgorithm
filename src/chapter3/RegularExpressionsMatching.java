/**
 * @description 实现一个函数用来匹配包含'.'(表示任意一个字符)和'*'(前面的一个字符可以出现任意次)的正则表达式
 */
package chapter3;

public class RegularExpressionsMatching {

	/**
	 * 
	 * @param str 待匹配字符串
	 * @param pattern 模式字符串
	 * @return boolean 输入验证后的匹配结果
	 * @description 由于不需要每次递归都进行输入验证，因此最好将输入验证和主体功能分离
	 */
	public boolean match(String str, String pattern) {
		// 输入验证
		if(str == null || pattern == null || "".equals(str) || "".equals(pattern)) {
			return false;
		}
		
		// 递归方法实现主体功能
		return matchCore(str, pattern, 0, 0);
	}
	
	/**
	 * 
	 * @param str 待匹配字符串
	 * @param pattern 模式字符串
	 * @param indexOfStr 待匹配字符串下标
	 * @param indexOfPattern 模式字符串下标
	 * @return boolean 匹配结果
	 * @description 递归实现字符串匹配，分具体情况讨论，每种情况对应一个递归子问题，最后根据子问题结果得到总问题的结果（类似于动态规划）
	 */
	public boolean matchCore(String str, String pattern, int indexOfStr, int indexOfPattern) {
		// 递归边界条件（匹配成功）
		if(indexOfStr == str.length() && indexOfPattern == pattern.length()) {
			return true;
		}
		// 递归边界条件（匹配失败）
		if((indexOfStr != str.length() && indexOfPattern == pattern.length()) || 
				(indexOfStr == str.length() && indexOfPattern != pattern.length())) {
			return false;
		}
		
		// 若当前字符后存在*字符，需将二者作为整体考虑，并将*分为0，1，多三种情况考虑
		if(indexOfPattern < pattern.length() - 1 && pattern.charAt(indexOfPattern + 1) == '*') {	//注意index + 1 导致数组边界溢出，因此前面做出判断
			// 当前具体字符的匹配和.字符的任意匹配
			if(pattern.charAt(indexOfPattern) == str.charAt(indexOfStr) || pattern.charAt(indexOfPattern) == '.') {
				return matchCore(str, pattern, indexOfStr + 1, indexOfPattern) ||		// *为多
						matchCore(str, pattern, indexOfStr + 1, indexOfPattern + 2) ||	// *为1
						matchCore(str, pattern, indexOfStr, indexOfPattern + 2);		// *为0且当前字符匹配但不考虑
			} else {
				return matchCore(str, pattern, indexOfStr, indexOfPattern + 2);			// *为0且当前字符不匹配
			}
		// 若当前字符后不存在*字符，只需考虑当前字符即可
		} else {
			// 当前具体字符的匹配和.字符的任意匹配
			if(pattern.charAt(indexOfPattern) == str.charAt(indexOfStr) || pattern.charAt(indexOfPattern) == '.') {
				return matchCore(str, pattern, indexOfStr + 1, indexOfPattern + 1);
			} else {
				return false;
			}
		}
	}
	
	/**
	 * @param args
	 * @description 测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegularExpressionsMatching regularExpressionsMatching = new RegularExpressionsMatching();
		String str = "aaa";
		String pattern = "ab*ac*a*";
		System.out.println(str + "和" + pattern + "匹配结果：" + regularExpressionsMatching.match(str, pattern));
	}

}
