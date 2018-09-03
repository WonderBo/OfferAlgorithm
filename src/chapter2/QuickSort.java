/**
 * @description 快速排序
 */
package chapter2;

public class QuickSort {

	/**
	 * 
	 * @param intArray	交换数据的数组
	 * @param index1	交换数据1的下标
	 * @param index2	交换数据2的下标
	 * @description 对数组中对两个数的位置进行交换，注意此时swapNum(num1, num2)不能实现相应位置交换功能，原因为值传递和引用传递的区别
	 */
	public static void swapNum(int[] intArray, int index1, int index2) {
		int temp = intArray[index1];
		intArray[index1] = intArray[index2];
		intArray[index2] = temp;
	}

	/**
	 * 
	 * @param intArray		数组
	 * @param startIndex	头下标
	 * @param endIndex		尾下标
	 * @return
	 * @description 在数组中随机选择一个数作为参照，把数组中的数据分为两部分，比参照数小的移到数组左边，反之大的移到右边
	 */
	public static int partitionArr(int[] intArray, int startIndex, int endIndex) {
		//参数验证
		if(intArray == null || intArray.length <= 0 || startIndex < 0 || endIndex >= intArray.length || startIndex > endIndex) {
			System.out.println("非法参数输入");
			return -1;
		}

		//在数组中随机选择一参照数，并置换到数组尾（Math.random()：[0, 1),其中对于整数来说，[0,n+1)表达式与[0,n]是相同的）
		int randomIndex = (int)(Math.random() * (endIndex + 1 - startIndex) + startIndex);
		swapNum(intArray, randomIndex, endIndex);

		//index: 由于不使用其余数据结构，因此需要一边界位置指针指示较大数据集与较小数据集的分界。此处位置指针指向较小数据集的末元素，初始为startIndex -1
		int index = startIndex - 1;
		//i: 循环位置指针
		for(int i = startIndex; i < endIndex; i ++) {
			if(intArray[i] < intArray[endIndex]) {
				index ++;
				if(index != i) {
					swapNum(intArray, index, i);
				}
			}
		}

		//交换参照数与最左较大数的位置
		index ++;
		swapNum(intArray, index, endIndex);

		//返回划分参照数的具体下标
		return index;
	}

	/**
	 * 
	 * @param intArray		排序数组
	 * @param startIndex	头下标
	 * @param endIndex		尾下标
	 * @description 分治法（递归）实现快速排序
	 */
	public static void quickSort(int[] intArray, int startIndex, int endIndex) {
		//边界条件
		if(startIndex == endIndex) {
			return;
		}
		
		//分治法划分边界
		int index = partitionArr(intArray, startIndex, endIndex);
		if(index == -1) {
			return;
		} else {
			//递归解决子问题，其中注意判断条件，避免边界溢出
			if(index > startIndex) {
				quickSort(intArray, startIndex, index - 1);
			}
			if(index < endIndex) {
				quickSort(intArray, index + 1, endIndex);
			}
		}
	}
	/**
	 * @param args
	 * @description 测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArr = {1,13,9,2,7,5,6,4,8,20,11};
		quickSort(intArr, 0, intArr.length - 1);
		for(int i = 0; i < intArr.length; i++) {
			System.out.print(intArr[i] + "  ");
		}
	}
}
