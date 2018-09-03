/**
 * @description 01背包问题和完全背包问题
 */
package chapter2;

public class Knapsack {

	/**
	 * 
	 * @param goodsNum		商品数量
	 * @param capacity		背包容量
	 * @param goodsValueArr	商品价值数组
	 * @param goodsWeightArr商品重量数组
	 * @return
	 * @description 01背包问题：每类物品最多放一个
	 */
	public static int knapsack01(int goodsNum, int capacity, int[] goodsValueArr, int[] goodsWeightArr) {
		//初始化结果数组
		//下一级结果数组依赖于上一级结果数组
		int[] results = new int[capacity + 1];
		for(int i = 0; i < results.length; i++) {
			results[i] = 0;
		}

		//双重循环实现动态规划。外层循环：商品种类；内层循环（倒序）：背包容量
		/*倒序原因：保证第i次循环中的状态f[i][v]是由状态f[i-1][v-c[i]]递推而来。
		    换句话说，这正是为了保证每件物品只选一次，保证在考虑“选入第i件物品”这件策略时，依据的是一个绝无已经选入第i件物品的子结果f[i-1][v-c[i]]。*/
		for(int i = 0; i < goodsNum; i++) {
			for(int j = results.length - 1; j >= goodsWeightArr[i]; j--) {
				results[j] = Math.max(results[j], results[j - goodsWeightArr[i]] + goodsValueArr[i]);
			}
		}

		return results[capacity];
	}

	/**
	 * 
	 * @param capacity		背包容量
	 * @param goodsValueArr	商品价值数组
	 * @param goodsWeightArr商品重量数组
	 * @return
	 * @description 完全背包问题：每类物品都有无限件可放
	 */
	public static int knapsackComplete(int capacity, int[] goodsValueArr, int[] goodsWeightArr) {
		//初始化结果数组
		//下一级结果数组的后部分依赖于该级结果数组的前部分
		int[] results = new int[capacity + 1];
		for(int i = 0; i < results.length; i++) {
			results[i] = 0;
		}
		
		//双重循环实现动态规划。外层循环：商品种类；内层循环（倒序）：背包容量
		/*完全背包的特点恰是每种物品可选无限件，所以在考虑“加选一件第i种物品”这种策略时，
		    却正需要一个可能已选入第i种物品的子结果f[i][v-c[i]]，所以就可以并且必须采用v=0..V的顺序循环。*/
		for(int i = 0; i < goodsValueArr.length; i++) {
			for(int j = goodsWeightArr[i]; j < results.length; j++) {
				results[j] = Math.max(results[j], results[j - goodsWeightArr[i]] + goodsValueArr[i]);
			}
		}

		return results[capacity];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int goodsNum = 5;
		int capacity = 10;
		int[] goodsValueArr = new int[]{400, 500, 200, 300, 350};
		int[] goodsWeightArr = new int[]{5, 5, 3, 4, 3};

		int mostValue = knapsack01(goodsNum, capacity, goodsValueArr, goodsWeightArr);
		System.out.println("knapsack01: " + mostValue);
		
		mostValue = knapsackComplete(10, goodsValueArr, goodsWeightArr);
		System.out.println("knapsackComplete: " + mostValue);
	}

}
