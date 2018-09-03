/**
 * @description 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 */
package chapter5;

public class InversePairs {

	/**
	 * 
	 * @param array
	 * @return
	 * @description 输入验证 + 递归主功能
	 */
	public static int getInversePairs(int[] array) {
		// 输入验证
		if(array == null || array.length == 0) {
			return 0;
		}
		
		int inversePairNum = getInversePairsCore(array, 0, array.length - 1);
		return inversePairNum;
	}
	
	/**
	 * 
	 * @param array 数组
	 * @param left 数组左边界
	 * @param right 数组右边界
	 * @return 逆序对数量
	 * @description 分治法求数组逆序对数量（同归并排序），在归并过程中统计逆序对数量并进行排序
	 * 				借鉴推理（归并排序 -> 求逆序对数量：排序数组对于逆序对数量的特殊性）
	 */
	public static int getInversePairsCore(int[] array, int left, int right) {
		// 递归边界条件
		if(left == right) {
			return 0;
		}
		
		int middle = (left + right) >> 1;	// 分治边界（二分法）
		int leftInversePairNum = getInversePairsCore(array, left, middle);	// 左子数组逆序对数量
		int rightInversePairNum = getInversePairsCore(array, middle + 1, right);	// 右子数组逆序对数量
		int mergeInversePairNum = getMergeInversePairs(array, left, middle, right);		// 两子数组间（归并过程）逆序对数量
		
		return leftInversePairNum + rightInversePairNum + mergeInversePairNum;
	}
	
	/**
	 * 
	 * @param array 数组
	 * @param left 数组左边界（左子数组左边界）
	 * @param middle 数组分解边界（middle：左子数组右边界；middle+1：右子数组左边界 ）
	 * @param right 数组右边界（右子数组右边界）
	 * @return 归并过程逆序对数量
	 * @description 过程大致类似与归并排序的merge方法，区别在于两指针分别指向两个子数组的末尾，并每次比较两指针指向的数字
	 */
	public static int getMergeInversePairs(int[] array, int left, int middle, int right) {
		int mergeInversePairNum = 0;	// 归并过程逆序对数量
		int leftEndIndex = middle;	// 初始化为前半段最后一个数字的下标
		int rightEndIndex = right;	// 初始化为后半段最后一个数字的下标
		int[] tempArray = new int[right - left + 1];	// 辅助数组（空间换时间）
		int tempEndIndex = right - left;	// 辅助数组下标
		
		while(leftEndIndex >= left && rightEndIndex >= middle + 1) {
			if(array[leftEndIndex] > array[rightEndIndex]) {
				mergeInversePairNum += (rightEndIndex - middle);
				tempArray[tempEndIndex --] = array[leftEndIndex --];
			} else {
				tempArray[tempEndIndex --] = array[rightEndIndex --];
			}
		}
		
		while(leftEndIndex >= left) {
			tempArray[tempEndIndex --] = array[leftEndIndex --];
		}
		
		while(rightEndIndex >= middle + 1) {
			tempArray[tempEndIndex --] = array[rightEndIndex --];
		}
		
		for(int i = 0; i < tempArray.length; i++) {
			array[left + i] = tempArray[i];
		}
		
		return mergeInversePairNum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {7, 5, 6, 4};
		System.out.println("数组逆序对数量：" + InversePairs.getInversePairs(array));
	}

}
