/**
 * @description 长度为n的绳子剪成m段(m，n均为整数且均大于1)，每段绳子的长度的乘积最大为多少
 * 				动态规划与贪心算法的区别：动态规划每一步为不确定选择，因此需要尝试所有可能，比较得到最优解
 * 									    贪心算法每一步都是贪心选择，但是需要数学方式证明使用贪心算法是正确的
 */
package chapter2;

public class CuttingRope {

	/**
	 * 
	 * @param length	绳长
	 * @return int		最大长度乘积
	 * @throws Exception
	 * @description 动态规划问题特征：最优解问题；整体问题最优解依赖子问题最优解；子问题间有重叠的更小子问题
	 * 				动态规划解决方法：从上往下分析问题(递归)，从下往上求解问题(循环)
	 */
	public static int maxProductAfterCutting_dp(int length) throws Exception {
		//输入验证
		if(length < 2) {
			throw new Exception("输入有误");
		}

		//问题边界情况
		if(length == 2) {
			return 1;
		}
		if(length == 3) {
			return 2;
		}

		//存储子问题结果数组
		int[] products = new int[length + 1];
		//递归边界条件
		products[0] = 0;
		products[1] = 1;
		products[2] = 2;
		products[3] = 3;

		int max = 0;
		//i为绳长
		for(int i = 4; i < products.length; i++) {
			max = 0;
			//j为第一次剪绳的一子绳长(由于对称性，最大为i/2)
			//动态规划的不确定性选择，只能尝试所有可能，然后比较得到最优解
			for(int j = 1; j <= i/2; j++) {
				int product = products[j] * products[i - j];
				if(max < product) {
					max = product;
				}
			}
			products[i] = max;
		}

		//返回对应绳长的最大长度乘积
		max = products[length];

		return max;
	}

	/**
	 * 
	 * @param length	绳长
	 * @return int		最大长度乘积
	 * @throws Exception
	 * @description 贪心算法求解问题，主要是运用数学知识证明贪心算法的正确性
	 */
	public static int maxProductAfterCutting_Greedy(int length) throws Exception {
		//输入验证
		if(length < 2) {
			throw new Exception("输入有误");
		}

		//问题边界情况
		if(length == 2) {
			return 1;
		}
		if(length == 3) {
			return 2;
		}

		//尽可能多地剪去长度为3的绳子段
		int timesOf3 = length / 3;
		
		//当绳子最后剩下长度为4时，不能再剪去长度为3的绳子段
		//此时更好的方法是把绳子剪成长度为2的两段，因为2 x 2 > 1 x 3
		if(length - 3 * timesOf3 == 1) {
			timesOf3 --;
		}
		
		int timesOf2 = (length - 3 * timesOf3) / 2;
		
		return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int maxProduct = maxProductAfterCutting_dp(10);
			System.out.println("动态规划-最大长度乘积为：" + maxProduct);
			
			maxProduct = maxProductAfterCutting_Greedy(10);
			System.out.println("贪心算法-最大长度乘积为：" + maxProduct);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
