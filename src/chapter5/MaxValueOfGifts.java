/**
 * @description 在m*n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值(>0)，可从棋盘左上角开始拿格子的礼物，并每次向下或向右移动一个，
 * 				直到到达棋盘右下角，给定一棋盘与上面的礼物价值，求出最多能拿到多少价值的礼物。
 */
package chapter5;

public class MaxValueOfGifts {

	/**
	 * 
	 * @param giftsValueArray 礼物价值二维数组
	 * @return int 礼物最大价值
	 * @description 利用动态规划分析问题（列出递归表达式），利用循环解决问题（避免重复计算，使用数组保留中间结果）
	 * 
	 */
	public int getMaxValueOfGifts1(int[][] giftsValueArray) {
		// 输入验证
		if(giftsValueArray == null || giftsValueArray.length <= 0 || giftsValueArray[0].length <=0) {
			return 0;
		}
		
		int rows = giftsValueArray.length;					// 行数
		int columns = giftsValueArray[0].length;			// 列数
		int[][] maxValueArray = new int[rows][columns];		// 辅助二维数组（保留中间数据）
		
		// 从左上依次遍历数组求解问题，从而避免不必要的重复计算
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				int leftValue = 0;
				int upValue = 0;
				
				// 判断条件防止数组下标越界（同防止链表空指针）
				if(j > 0) {
					leftValue = maxValueArray[i][j - 1];
				}
				if(i > 0) {
					upValue = maxValueArray[i - 1][j];
				}
				maxValueArray[i][j] = Math.max(leftValue, upValue) + giftsValueArray[i][j];
			}
		}
		
		return maxValueArray[rows - 1][columns - 1];
	}
	
	/**
	 * 
	 * @param giftsValueArray 礼物价值二维数组
	 * @return int 礼物最大价值
	 * @description 在第一种解决方案的基础上减少辅助数组的大小，降低空间复杂度
	 */
	public  int getMaxValueOfGifts2(int[][] giftsValueArray) {
		// 输入验证
		if(giftsValueArray == null || giftsValueArray.length <= 0 || giftsValueArray[0].length <=0) {
			return 0;
		}
		
		int rows = giftsValueArray.length;					// 行数
		int columns = giftsValueArray[0].length;			// 列数
		// 由于最大价值只依赖于(i-1, j)与(i, j-1)两个格子，因此第i-2行及以上数据没必要保留，只需一维数组暂存即可（可覆盖）
		// 其中前j个数字分别是当前第i行前面j个格子的最大价值，之后的数字分别保存第i-1行columns-j个格子的最大价值
		int[] maxValueArray = new int[columns];				// 辅助一维数组（暂存中间数据）
		
		// 从左上依次遍历数组求解问题，从而避免不必要的重复计算
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				int leftValue = 0;
				int upValue = 0;
				
				// 判断条件防止数组下标越界（同防止链表空指针）
				if(j > 0) {
					leftValue = maxValueArray[j - 1];
				}
				if(i > 0) {
					upValue = maxValueArray[j];
				}
				maxValueArray[j] = Math.max(leftValue, upValue) + giftsValueArray[i][j];
			}
		}
		
		return maxValueArray[columns - 1];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] giftsValueArray = new int[][]{{1,10,3,8},{12,2,9,6},{5,7,4,11},{3,7,16,5}};
		MaxValueOfGifts maxValueOfGifts = new MaxValueOfGifts();
		System.out.println("方案1最大礼物价值：" + maxValueOfGifts.getMaxValueOfGifts1(giftsValueArray));
		System.out.println("方案2最大礼物价值：" + maxValueOfGifts.getMaxValueOfGifts2(giftsValueArray));
	}

}
