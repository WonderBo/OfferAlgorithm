/**
 * @description 输入一整型数组，数组里有正数也有负数，数组里的一个或者多个整数组成一个子数组，求出所有子数组的和的最大值。要求时间复杂度为O（n）
 */
package chapter5;

public class MaxSumOfSubarrays {

	/**
	 * 
	 * @param intArray	数组
	 * @return int 最大子数组和
	 * @throws Exception
	 * @description 两种思路解决问题，但代码一样：（1）分析累加子数组的和的过程，从而找到解题规律（2）间接使用动态规划（假设最大子数组的尾下标是确定的）
	 */
	public int getMaxSumOfSubarrays(int[] intArray) throws Exception {
		// 输入验证
		if(intArray == null || intArray.length == 0) {
			// 向上逐层抛出异常，直到被捕获处理，或者抛到最上层，如main()和线程的run(),后面的代码将不再执行
			throw new Exception("输入错误");
		}
		
		int subarraySum = 0;	// 子数组和（小于等于0将被切断抛弃）
		int maxSumOfSubarray = intArray[0];		// 最大子数组和作为比较对象（数组元素可能全为负，因此不能初始化为0，可初始化为数组首元素或者最小整数值）
		// 遍历数组元素，更新子数组和与最大子数组和
		for(int i = 0; i < intArray.length; i++) {
			// 子数组和小于等于0将被切断抛弃，更新子数组和
			if(subarraySum <= 0) {
				subarraySum = intArray[i];
			} else {
				subarraySum += intArray[i];
			}
			
			// 当前子数组和大于最大子数组和，则更新最大子数组和
			if(subarraySum > maxSumOfSubarray) {
				maxSumOfSubarray = subarraySum;
			}
		}
		
		return maxSumOfSubarray;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = {-10, -2, -5, -3, -8}; // {1, -2, 3, 10, -4, 7, 2, -5};
		MaxSumOfSubarrays maxSumOfSubarrays = new MaxSumOfSubarrays();
		try {
			System.out.println("最大子数组和：" + maxSumOfSubarrays.getMaxSumOfSubarrays(intArray));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
