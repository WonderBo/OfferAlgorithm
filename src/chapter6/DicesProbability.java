/**
 * @description 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率
 */
package chapter6;

public class DicesProbability {

	private static final int maxValue = 6;

	/**
	 * 
	 * @param num 骰子数
	 * @description 基于递归求解各个骰子点数和出现的概率，时间性能不够好
	 */
	public static void printProbability_Recursion(int num) {
		// 输入验证
		if(num < 1) {
			return;
		}

		int maxSum = maxValue * num;
		int[] sumTimesArray = new int[maxSum - num + 1];	// 数组元素默认初始化为0，数组容量由骰子的可能点数和[num, maxSum-num]可得

		// 用递归实现将骰子点数和出现的次数存入到数组中
		getsumTimes(num, num, 0, sumTimesArray);

		double totalTimes = Math.pow(maxValue, num);	// num个骰子所有点数的的排列数
		for(int i = num; i <= maxSum; i++) {
			// 对应点数和出现的次数除以总排列数即为对应点数出现的概率
			double ratio = sumTimesArray[i - num] / totalTimes;		
			System.out.println("点数和：" + i + " \t对应出现的概率：" + ratio);
		}
	}

	/**
	 * 
	 * @param originalNum 总共骰子数（便于确定数组下标）
	 * @param currentNum 当前剩余（未扔）骰子数
	 * @param sum 各个骰子点数和（sum不可以作为返回值，若作为返回值，则由sum整数递归得到sum数组，数据类型改变，因此需要把sum放在方法内部）
	 * @param sumTimesArray 存放点数和出现次数的数组
	 * @description 用递归模拟扔骰子的过程，不断减小问题的规模，从而最后在数组中更新点数和出现的次数
	 */
	public static void getsumTimes(int originalNum, int currentNum, int sum, int[] sumTimesArray) {
		// 递归结束条件（当骰子扔完，则在数组中更新对应点数和的出现次数）
		if(currentNum == 0) {
			sumTimesArray[sum - originalNum] ++;
			return;
		}

		// 循环递归
		for(int i = 1; i <= maxValue; i++) {
			getsumTimes(originalNum, currentNum - 1, sum + i, sumTimesArray);
		}
	}


	/**
	 * 
	 * @param num 骰子数
	 * @description 基于循环求解各个骰子点数和出现的概率，时间性能好
	 */
	public static void printProbability_Iteration(int num) {
		// 输入验证
		if(num < 1) {
			return;
		}

		int[][] sumTimesArray = new int[2][maxValue * num + 1];		// 用两个数组交替保存每轮抛掷骰子点数和的出现次数
		int flag = 0;	// 标记当前使用的数组

		// 当抛掷第一个骰子时，有maxValue种可能，每种可能出现一次（初始化，为后面抛掷骰子求解点数和出现次数做铺垫）
		for(int i = 1; i <= maxValue; i++) {
			sumTimesArray[flag][i] = 1;
		}
		// 从抛掷第二个骰子开始，此时和为n的骰子出现次数应该等于上一次循环中骰子点数和为n-1,n-2,n-3,n-4,n-5,n-6的次数总和
		for(int i = 2; i <= num; i++) {
			// 第i次掷骰子，和最小为i，小于i的情况不可能发生,所以将数组中不可能发生的次数设置为0
			for(int j = 0; j < i; j++) {
				sumTimesArray[1 - flag][j] = 0;
			}
			
			// 更新其他可能点数和的出现次数
			for(int j = i; j <= maxValue * i; j++) {
				sumTimesArray[1 - flag][j] = 0;		// 初始化，因为数组存放的是之前过期的值且数组需要重复使用，因此需要清零（此步可省略，因为后面会覆盖）
				// 把另一个数组的第j个数字设为前一个数组对应的j-1,j-2,j-3,j-4,j-5，j-6之和（k <= j：用于前期判断，防止数组头部越界）
				for(int k = 1; k <= j && k <= maxValue; k++) {
					sumTimesArray[1 - flag][j] += sumTimesArray[flag][j - k];
				}
			}
			
			// 通过改变flag来交换使用这两个数组
			flag = 1 - flag;
		}

		double totalTimes = Math.pow(maxValue, num);	// num个骰子所有点数的的排列数
		for(int i = num; i <= maxValue * num; i++) {
			// 对应点数和出现的次数除以总排列数即为对应点数出现的概率
			double ratio = sumTimesArray[flag][i] / totalTimes;		
			System.out.println("点数和：" + i + " \t对应出现的概率：" + ratio);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long time1 = System.currentTimeMillis();
		printProbability_Recursion(6);
		long time2 = System.currentTimeMillis();
		System.out.println("递归实现耗费时间：" + (time2 - time1) + "\n");
		printProbability_Iteration(6);
		long time3 = System.currentTimeMillis();
		System.out.println("循环实现耗费时间：" + (time3 - time2));
	}

}
