/**
 * @description 在字符串中找出一个最长的不含重复字符的子字符串，计算该最长子字符串的长度，假设字符串中只包含'a'-'z'的字符。
 */
package chapter5;

public class LongestSubstringWithoutDup {

	/**
	 * 
	 * @param str 字符串
	 * @return int 最长不含重复字符的子字符串
	 * @description 将首尾两端均不固定的子字符串固定住一端（一般固定尾端），以便能够减小问题的复杂度从而利用动态规划求解（一定要考虑完所有的可能情况），
	 * 				然后利用循环遍历固定端的所有可能情况从而达到不固定的效果，最后求出最终最优解。
	 */
	public int longestSubstringWithoutDup(String str) {
		// 输入验证
		if(str == null || "".equals(str)) {
			return 0;
		}
		
		int currentMaxLength = 0;	// 当前固定尾端的最长不含重复字符的子字符串（可作为下次循环的中间保存结果）
		int allMaxLength = 0;		// 所有情况的最长不含重复字符的子字符串
		int[] preIndexArray = new int[26];	// 类似于位图考虑元素重复问题，不存入0，1而存入元素上次出现下标。也可认为数组是特殊的hash表。
		// 字符串字符上次出现下标初始化为-1
		for(int i = 0; i < preIndexArray.length; i++) {
			preIndexArray[i] = -1;
		}
		
		// 利用循环求解动态规划，既可以避免重复计算，又可以达到循环遍历固定尾端的效果
		for(int i = 0; i < str.length(); i++) {
			int preIndex = preIndexArray[(str.charAt(i) - 'a')];
			if(preIndex < 0 || (i - preIndex) > currentMaxLength) {
				currentMaxLength ++;
			} else {
				currentMaxLength = i - preIndex;
			}
			preIndexArray[(str.charAt(i) - 'a')] = i;
			
			if(currentMaxLength > allMaxLength) {
				allMaxLength = currentMaxLength;
			}
		}
		
		return allMaxLength;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestSubstringWithoutDup longestSubstringWithoutDup = new LongestSubstringWithoutDup();
		String str = "arabcacfr";
		System.out.println("最长不含重复字符的子字符串长度为：" + longestSubstringWithoutDup.longestSubstringWithoutDup(str));
	}

}
