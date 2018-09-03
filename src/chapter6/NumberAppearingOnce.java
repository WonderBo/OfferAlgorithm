/**
 * @description 在一个数组中除了一个数字只出现一次外，其他数字都出现了三次，请找出那个只出现一次的数字
 */
package chapter6;

public class NumberAppearingOnce {

	/**
	 * 
	 * @param intArray 整型数组
	 * @return 只出现一次的数字
	 * @throws Exception 输入异常
	 * @description 从二进制角度出发，利用位运算解决整数问题（整数 <-> 32位bit）。将数字按位化整为零来巧妙解决整数问题
	 */
	public static int getNumberAppearingOnce(int[] intArray) throws Exception {
		// 输入验证
		if(intArray == null || intArray.length == 0) {
			throw new Exception("输入异常");
		}
		
		int result = 0;
		
		// 根据整数的二进制形式按位累加所有数组元素
		int[] bitSumArray = new int[32];
		for(int i = 0; i < intArray.length; i++) {
			for(int j = 31; j >= 0; j--) {
				if((intArray[i] & 1) == 1) {
					bitSumArray[j] += 1;
				}
				intArray[i] = intArray[i] >> 1;
			}
		}
		
		// 取模运算后将二进制转化为十进制
		for(int i = 0; i < 32; i++) {
			result = result << 1;
			result += (bitSumArray[i] % 3);
		}
		
		return result;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int[] intArray = {1, 2, 3, 9, 3, 2, 2, 1, 1, 3};
		System.out.println("数组中只出现一次的数字为：" + getNumberAppearingOnce(intArray));
	}

}
