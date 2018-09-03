/**
 * @description 输入n个整数，找出其中最小的k个数
 */
package chapter5;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

public class KLeastNumbers {
	/**
	 * 
	 * @param intArray	交换数据的数组
	 * @param index1	交换数据1的下标
	 * @param index2	交换数据2的下标
	 * @description 对数组中对两个数的位置进行交换，注意此时swapNum(num1, num2)不能实现相应位置交换功能，原因为值传递和引用传递的区别
	 */
	public void swapNumInArray(int[] intArray, int index1, int index2) {
		int temp = intArray[index1];
		intArray[index1] = intArray[index2];
		intArray[index2] = temp;
	}

	/**
	 * 
	 * @param array			数组
	 * @param startIndex	头下标
	 * @param endIndex		尾下标
	 * @return
	 * @description 在数组中随机选择一个数作为参照，把数组中的数据分为两部分，比参照数小的移到数组左边，反之大的移到右边
	 */
	public int patitionArray(int[] intArray, int startIndex, int endIndex) {
		// 输入验证
		if(intArray == null || intArray.length == 0 || startIndex < 0 || endIndex >= intArray.length || startIndex > endIndex) {
			return -1;
		}

		// 对比参照数从而将数组元素划分到数组两边
		// smallEndIndex: 由于不使用其余数据结构，因此需要一边界位置指针指示较大数据集与较小数据集的分界。此处位置指针指向较小数据集的末元素，初始为startIndex -1
		int smallEndIndex = startIndex - 1;
		for(int i = startIndex; i < endIndex; i++) {
			if(intArray[i] < intArray[endIndex]) {
				smallEndIndex ++;
				if(smallEndIndex != i) {
					swapNumInArray(intArray, smallEndIndex, i);
				}
			}
		}
		// 交换参照数与最左较大数的位置
		swapNumInArray(intArray, ++ smallEndIndex, endIndex);

		//返回划分参照数的具体下标
		return smallEndIndex;
	}

	/**
	 * 
	 * @param intArray 输入数组
	 * @param k 最小k个数
	 * @return int[] 最小k个数组成的数组
	 * @description 使用patition函数求取无序数组中第k大的数字或者最小的k个数。此方法会改变数组（交换数组中数字的顺序），时间复杂度为O(n)
	 * 				patition函数将数组分为三个部分：较小元素组，当前元素，较大元素组。注意三者间的大小关系与位置/下标关系。
	 */
	public int[] getLeastNumbers(int[] intArray, int k) {
		// 输入验证（语法验证，语义验证）
		if(intArray == null || intArray.length == 0 || k <= 0 || k > intArray.length) {
			return null;
		}

		int startIndex = 0;
		int endIndex = intArray.length - 1;
		// 分治法：不断划分数组直到找到第k大的数组元素
		int patitionIndex = patitionArray(intArray, startIndex, endIndex);
		while(patitionIndex != k - 1) {
			if(patitionIndex > k - 1) {
				endIndex = patitionIndex - 1;
				patitionIndex = patitionArray(intArray, startIndex, endIndex);
			} else {
				startIndex = patitionIndex + 1;
				patitionIndex = patitionArray(intArray, startIndex, endIndex);
			}
		}

		int[] outputArray = new int[k];
		for(int i = 0; i < k; i++) {
			outputArray[i] = intArray[i];
		}
		
		return outputArray;
	}
	
	/**
	 * 
	 * @param dataVector 输入数据向量
	 * @param k 最小k个数
	 * @return TreeSet<Integer> 最小k个数组成的TreeSet
	 * @description 此种方法适合处理海量数据但是k较小的情况，时间复杂度为O(n*log k)，而且不会修改输入的数据
	 * 				选择容器来存储k个最小数据，依次处理每个数据，当容器未满时则直接加入容器，若容器已满且该数据小于容器中最大数据，则删除容器最大数据，再加入该数据
	 * 				容器需要查询最大数据，删除数据，添加数据，因此选用二叉树数据结构来作为容器，其时间复杂度为O(log k),比如最大堆，红黑树
	 * 				处理海量数据：（1）降低空间复杂度：分批导入内存；（2）降低时间复杂度：选择合适的数据结构与算法
	 * 				
	 */
	public TreeSet<Integer> getLeastNumbers2(Vector<Integer> dataVector, int k) {
		// 输入验证（语法验证，语义验证）
		if(dataVector.size() == 0 || dataVector == null || k <= 0 || k > dataVector.size()) {
			return null;
		}
		
		TreeSet<Integer> leastNumbersTreeSet = new TreeSet<Integer>();	// 输出数据二叉树容器（bug：TreeSet不存储重复数据，可以选择其他二叉树容器）
		
		// 遍历输入数据容器
		Iterator<Integer> dataIterator = dataVector.iterator();				
		while(dataIterator.hasNext()) {
			// 如果输出容器未满
			if(leastNumbersTreeSet.size() < k) {
				leastNumbersTreeSet.add(dataIterator.next());
			} else {
				// 输出容器已满
				int greatestNumberInTreeSet = leastNumbersTreeSet.last();
				int currentNumberInVector = dataIterator.next();
				
				// 输入数据小于输出容器中最大数据，则进行替换
				if(currentNumberInVector < greatestNumberInTreeSet) {
					leastNumbersTreeSet.remove(greatestNumberInTreeSet);
					leastNumbersTreeSet.add(currentNumberInVector);
				}
				
			}
		}
		
		// 返回输出数据二叉树容器
		return leastNumbersTreeSet;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = {4, 5, 1, 6, 2, 7, 3, 8};
		int k = 5;
		
		// 测试基于patition函数得到最小的k个数
		KLeastNumbers kLeastNumbers = new KLeastNumbers();
		System.out.print("方法1-最小的" + k + "个数是：");
		int[] outputArray = kLeastNumbers.getLeastNumbers(intArray, k);
		for(int num : outputArray) {
			System.out.print(num + " ");
		}
		
		// 测试时间复杂度为O(n*log k)且适合处理海量数据的方法来得到最小的k个数
		Vector<Integer> dataVector = new Vector<Integer>();
		for(int num : intArray) {
			dataVector.add(num);
		}
		TreeSet<Integer> leastNumbersTreeSet = kLeastNumbers.getLeastNumbers2(dataVector, k);
		Iterator<Integer> leastNumbersIterator = leastNumbersTreeSet.iterator();
		System.out.print("\n方法2-最小的" + k + "个数是：");
		while(leastNumbersIterator.hasNext()) {
			System.out.print(leastNumbersIterator.next() + " ");
		}
	} 

}
