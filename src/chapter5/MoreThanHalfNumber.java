/**
 * @description 输入一个数组，该数组中有一个数字出现的次数超过数组长度的一半，请找出并输出这个数字
 */
package chapter5;

public class MoreThanHalfNumber {

	/**
	 * 
	 * @param intArray	交换数据的数组
	 * @param index1	交换数据1的下标
	 * @param index2	交换数据2的下标
	 * @description 对数组中对两个数的位置进行交换，注意此时swapNum(num1, num2)不能实现相应位置交换功能，原因为值传递和引用传递的区别
	 */
	public void swapNumInArray(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	/**
	 * 
	 * @param array			数组
	 * @param startIndex	头下标
	 * @param endIndex		尾下标
	 * @return
	 * @description 在数组中随机选择一个数作为参照，把数组中的数据分为两部分，比参照数小的移到数组左边，反之大的移到右边
	 */
	public int partitionArray(int[] array, int startIndex, int endIndex) {
		// 参数验证
		if(array == null || array.length <= 0 || startIndex < 0 || endIndex >= array.length || startIndex > endIndex) {
			return -1;
		}

		// 在数组中随机选择一参照数，并置换到数组尾（Math.random()：[0, 1),其中对于整数来说，[0,n+1)表达式与[0,n]是相同的），此步可省略，直接选取最后元素为参照数
		int randomIndex = (int) (Math.random() * (endIndex + 1 - startIndex) + startIndex);
		swapNumInArray(array, randomIndex, endIndex);

		// 对比参照数从而将数组元素划分到数组两边
		// smallEndIndex: 由于不使用其余数据结构，因此需要一边界位置指针指示较大数据集与较小数据集的分界。此处位置指针指向较小数据集的末元素，初始为startIndex -1
		int smallEndIndex = startIndex - 1;
		for(int i = startIndex; i < endIndex; i++) {
			if(array[i] < array[endIndex]) {
				smallEndIndex ++;
				if(smallEndIndex != i) {
					swapNumInArray(array, smallEndIndex, i);
				}
			}
		}

		// 交换参照数与最左较大数的位置
		swapNumInArray(array, ++ smallEndIndex, endIndex);

		//返回划分参照数的具体下标
		return smallEndIndex;
	}

	/**
	 * 
	 * @param array 数组
	 * @param number 数字
	 * @return boolean
	 * @description 检查该数字在数组中出现的次数是否超过数组长度的一半
	 */
	public boolean checkMoreThanHalf(int[] array, int number) {
		int times = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == number) {
				times ++;
			}
		}

		if(times * 2 > array.length) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param array 数组
	 * @return int 重复数字或者异常(-1)
	 * @description 若数组中一个数字的出现次数超过数组长度一半，那么该数组排序后，位于数组中间的数字一定就是那个出现次数超过数组长度一半的数字
	 * 				类似快速排序算法，使用分治法，可以在O(n)时间复杂度下求取无序数组中第k大的数字
	 * 				此方法会改变数组（交换数组中数字的顺序）
	 */
	public int moreThanHalfNumber1(int[] array) {
		// 输入验证1
		if(array == null || array.length <= 0) {
			return -1;
		}

		int startIndex = 0;
		int endIndex = array.length - 1;
		int middleIndex = array.length >> 1;
		int partitionIndex = partitionArray(array, startIndex, endIndex);

		// 分治法：不断划分数组直到找到第middleIndex大的数组元素
		while(partitionIndex != middleIndex) {
			if(partitionIndex < middleIndex) {
				startIndex = partitionIndex + 1;
				partitionIndex = partitionArray(array, startIndex, endIndex);
			} else {
				endIndex = partitionIndex - 1;
				partitionIndex = partitionArray(array, startIndex, endIndex);
			}
		}
		int result = array[middleIndex];

		// 输入验证2（需要根据前面的结果做出验证）
		if(checkMoreThanHalf(array, result)) {
			return result;
		}

		return -1;
	}

	/**
	 * 
	 * @param array 数组
	 * @return int 重复数字或者异常(-1)
	 * @description 若数组中一个数字的出现次数超过数组长度一半，那么它出现的次数比其他所有数字出现次数的和还多，由此可以将数组中数字分为两类
	 * 				所找数字（返回结果）为最后一次把次数设置为1时对应的数字
	 * 				此方法不会改变数组
	 * 				
	 */
	public int moreThanHalfNumber2(int[] array) {
		// 输入验证1
		if(array == null || array.length <= 0) {
			return -1;
		}
		
		int result = array[0];	// 保存数字
		int times = 1;			// 次数
		// 数组下一个数字与result相同，则times加1，反之，则减1，如果times为0，则保存下一个数字到result，并设times为1
		for(int i = 1; i < array.length; i++) {
			if(times == 0) {	// 先判断times是否为0，再对其做加减
				result = array[i];
				times = 1;
			} else if(result == array[i]) {
				times ++;
			} else {
				times --;
			}
		}
		
		// 输入验证2（需要根据前面的结果做出验证）
		if(checkMoreThanHalf(array, result)) {
			return result;
		}

		return -1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MoreThanHalfNumber moreThanHalfNumber = new MoreThanHalfNumber();
		int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
		System.out.println("方法1-数组中出现次数超过一半的数字为：" + moreThanHalfNumber.moreThanHalfNumber1(array));
		System.out.println("方法2-数组中出现次数超过一半的数字为：" + moreThanHalfNumber.moreThanHalfNumber2(array));
	}

}
