/**
 * @description 输入一递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s，如果有多对数字的和为s，输出任意一组即可
 */
package chapter6;

public class TwoNumbersWithSum {

	/**
	 * 
	 * @param sortIntArray 递增排序数组
	 * @param s 和值
	 * @return 数组中的两个不同数
	 * @description 利用首尾指针遍历数组求出和为s的两个数字，偏大则前移后指针，偏小则后移前指针
	 */
	public static int[] getTwoNumbersWithSum(int[] sortIntArray, int s) {
		// 输入验证
		if(sortIntArray == null || sortIntArray.length < 2 || s < 3) {
			return null;
		}
		
		int[] returnArray = new int[2];
		
		// 利用首尾指针遍历数组 优化 双首指针遍历数组所带来的高时间复杂度（数组与链表的区别，数组：双向；链表：单向）
		int aheadIndex = 0;
		int behindIndex = sortIntArray.length - 1;
		while(aheadIndex < behindIndex) {	// 循环结束条件1
			// Slot的使用规则正是当变量作用域不重合时可以重复使用
			int sum = sortIntArray[aheadIndex] + sortIntArray[behindIndex];
			
			if(sum == s) {	// 循环结束条件2
				returnArray[0] = sortIntArray[aheadIndex];
				returnArray[1] = sortIntArray[behindIndex];
				break;
			} else if(sum < s) {
				aheadIndex ++;
			} else {
				behindIndex --;
			}
		}
		
		return returnArray;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] sortIntArray = {1, 2, 4, 7, 11, 15};
		int[] returnArray = getTwoNumbersWithSum(sortIntArray, 15);
		if(returnArray != null) {
			System.out.println("递增排序数组中和为s的两个数为：" + returnArray[0] + ", " + returnArray[1]);
		} else {
			System.out.println("递增排序数组中不存在和为s的两个数");
		}
	}

}
