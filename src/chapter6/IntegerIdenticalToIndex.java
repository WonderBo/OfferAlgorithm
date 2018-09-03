/**
 * @description 一单调递增数组中每个元素都是整数并且都是唯一的，请找出数组中任意一个数值等于其下标的元素
 */
package chapter6;

public class IntegerIdenticalToIndex {

	/**
	 * 
	 * @param intArray 数组
	 * @return 某一数组中数值和下标相等的元素 或者 -1（此时数组中不包含数值和下标相等的元素）
	 * @description 二分查找算法找到某一数组中数值和下标相等的元素
	 */
	public static int getNumberSameAsIndex(int[] intArray) {
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
			// 如果数组中第i个数字的值大于i，那么它右边的数字都大于对应的下标，下一轮查找只需从它左边的数字中查找即可，数字的值m小于它下标i的情形与前面类似
			if(intArray[middleIndex] == middleIndex) {		// 循环判断条件2
				return middleIndex;
			} else if(intArray[middleIndex] > middleIndex) {
				rightIndex = middleIndex - 1;
			} else {
				leftIndex = middleIndex + 1;
			}
		}
		
		// 无效的输入，比如数组中不包含数值和下标相等的元素
		return -1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = {-3, -1, 1, 3, 5};
		System.out.println("数组中数值和下标相等的元素：" + IntegerIdenticalToIndex.getNumberSameAsIndex(intArray));
	}

}
