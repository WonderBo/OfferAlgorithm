/**
 * @description 旋转数组（把一个数组最开始的若干个元素搬到数组末尾）的最小数字，输入一递增排序数组的一个旋转，输出旋转数组的最小元素
 */
package chapter2;

public class MinNumberInRotatedArray {

	/**
	 * 
	 * @param intArray	旋转数组
	 * @return	最小元素
	 * @throws Exception 
	 * @description 二分查找法获取旋转数组的最小元素，但是注意特殊情况（算法：二分法，实现方法：循环实现递归）
	 */
	public int getMinNumInRotatedArray(int[] intArray) throws Exception {
		//输入验证
		if(intArray == null || intArray.length <= 0) {
			throw new Exception("非法数组");
		}
		
		//下表初始化
		int headIndex = 0;
		int tailIndex = intArray.length - 1;
		//将midIndex初始化为headIndex，是因为若旋转0个元素，即为原升序数组，则最小元素为第一个元素
		int midIndex = headIndex;
		//递归表示，循环实现
		while(intArray[headIndex] >= intArray[tailIndex]) {
			//循环的结束条件，也就是递归的边界条件
			if(tailIndex - headIndex == 1) {
				midIndex = tailIndex;
				break;
			}
			
			//二分法下标
			midIndex = (headIndex + tailIndex) / 2;
			
			//特殊情况：如果下标headIndex，tailIndex，midIndex指向的三个数字相等，不适用该算法，即使用顺序查找
			if(intArray[headIndex] == intArray[midIndex] && intArray[midIndex] == intArray[tailIndex]) {
				return minByOrder(intArray, headIndex, tailIndex);
			}
			
			//根据二分下标的值移动首尾下标，以缩小查找范围
			if(intArray[midIndex] >= intArray[headIndex]) {
				headIndex = midIndex;
			} else if(intArray[midIndex] <= intArray[tailIndex]) {
				tailIndex = midIndex;
			}
		}
		
		return intArray[midIndex];
	}
	
	/**
	 * 
	 * @param intArray	数组
	 * @param headIndex	头指针
	 * @param tailIndex	尾指针
	 * @return	顺序查找数组最小值（形参必须包括headIndex，tailIndex，以便对每次收缩范围的数组进行判断查找，而不占用其它空间）
	 */
	public int minByOrder(int[] intArray, int headIndex, int tailIndex) {
		int result = intArray[headIndex];
		for(int i = headIndex + 1; i < tailIndex; i++) {
			if(result > intArray[i]) {
				result = intArray[i];
			}
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = new int[]{8, 9, 15, 34, 6, 7, 8};
		MinNumberInRotatedArray minNumberInRotatedArray = new MinNumberInRotatedArray();
		try {
			int minNun = minNumberInRotatedArray.getMinNumInRotatedArray(intArray);
			System.out.println("最小元素：" + minNun);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
