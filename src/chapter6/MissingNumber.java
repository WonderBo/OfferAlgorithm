/**
 * @description 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0~n-1之内。在范围0~n-1内的n个数字中有且只有一个数字不在该数组中，请找出该数字
 */
package chapter6;

public class MissingNumber {

	/**
	 * 
	 * @param intArray 数组
	 * @return 缺失的数字或者数组长度（此时数组不缺失数字）或者-1（此时输入无效）
	 * @description 二分查找算法找到数组中第一个数字与下标不相等的数字，即为0~n-1中缺失的数字
	 */
	public static int getMissingNumber(int[] intArray) {
		// 输入验证
		if(intArray == null || intArray.length == 0) {
			return -1;
		}
		
		int leftIndex = 0;
		int rightIndex = intArray.length - 1;
		int middleIndex = 0;
		// 二分查找
		while(leftIndex <= rightIndex) {	// 循环判断条件1
			middleIndex = (leftIndex + rightIndex) >> 1;
			// 如果中间元素的值和下标不相等，并且它前一个元素与它下标相等，则该中间数字正好是第一个值和下标不相等的元素，它的下标就是在数组中不存在的数字
			if(intArray[middleIndex] != middleIndex) {
				if((middleIndex >= 1 && intArray[middleIndex - 1] == middleIndex - 1) || middleIndex == 0) {	// 循环判断条件2（分情况，且防越界）
					return middleIndex;
				} else {
					rightIndex = middleIndex - 1;
				}
			} else {
				leftIndex = middleIndex + 1;
			}
		}
		
		// 如果该排序数组不缺失数字则返回数组长度
		if(leftIndex == intArray.length) {
			return intArray.length;
		}
		
		// 无效的输入，比如数组不是按照要求排序的，或者有数字不在0~n-1范围之内
		return -1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = {0, 1, 2, 4, 5, 6};
		System.out.println("0~n-1中缺失的数字为：" + MissingNumber.getMissingNumber(intArray));
	}

}
