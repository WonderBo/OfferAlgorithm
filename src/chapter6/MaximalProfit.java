/**
 * description 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少
 */
package chapter6;

public class MaximalProfit {

	/**
	 * 
	 * @param priceArray 股票价格数组
	 * @return
	 * @description 最大利润就是数组所有数对的最大差值，而数对虽不是严格的动态子数组，但也可以使用前后双指针表达，因此可以用数组中动态子数组问题解题思路解决
	 */
	public static int getMaximalProfit(int[] priceArray) {
		// 输入验证
		if(priceArray == null || priceArray.length < 2) {
			return 0;
		}
		
		int minPrice = priceArray[0];	// 卖出股票价格
		int maxProfit = priceArray[1] - priceArray[0];	// 卖出股票最大差价
		
		// 依次固定股票卖出价，则最大利润为当前价格减去前面股票价格的最低值，最后根据循环求出全局最优值
		for(int i = 2; i < priceArray.length; i++) {	// i为卖出股票价格的下标（子数组后端下标）
			// 更新之前股票最低值
			if(priceArray[i - 1] < minPrice) {
				minPrice = priceArray[i - 1];
			}
			
			// 更新当前股票最大差价/利润
			if(priceArray[i] - minPrice > maxProfit) {
				maxProfit = priceArray[i] - minPrice;
			}
		}
		
		return maxProfit;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] priceArray = {9, 11, 8, 5, 7, 12, 16, 14};
		System.out.println("买卖该股票一次可能获得的最大利润: " + getMaximalProfit(priceArray));
	}

}
