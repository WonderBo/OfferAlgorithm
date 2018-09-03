/**
 * @description 统计一个数字在排序数组中出现的次数
 */
package chapter6;

public class NumberOfK {

	/**
	 * 
	 * @param dataArray 数组
	 * @param k 数字
	 * @param start 数组头下标
	 * @param end 数组尾下标
	 * @return 数组中第一个k的下标
	 * @description 利用递归二分查找算法在数组中找到第一个k（关键在于若相等则与前一元素比较，其余部分与二分查找类似）
	 * 				可以同Test.BinarySearch.binarySearchForFirstTime()做对比
	 */
	public static int getFirstK(int[] dataArray, int k, int start, int end) {
		// 递归边界条件1（数组中不包含数字k）
		if(start > end) {
			return -1;
		}
		
		int middle = (start + end) >> 1;
		if(dataArray[middle] == k) {
			// 递归边界条件2（验证中间元素的前一个元素是否与中间元素不相等 或者 中间元素为数组首元素，如果成立则中间元素为第一个k，此时直接返回）
			if((middle >= 1 && dataArray[middle - 1] != k) || middle == 0) {		// 注意数组边界判断（分情况，且防越界）
				return middle;
			} else {
				end = middle - 1;
			}
		} else if(dataArray[middle] > k) {
			end = middle - 1;
		} else {
			start = middle + 1;
		}
		
		return getFirstK(dataArray, k, start, end);
	}
	
	/**
	 * 
	 * @param dataArray 数组
	 * @param k 数字
	 * @param start 数组头下标
	 * @param end 数组尾下标
	 * @return 数组中最后一个k的下标
	 * @description 利用递归二分查找算法在数组中找到最后一个k（关键在于若相等则与后一元素比较，其余部分与二分查找类似）
	 * 				可以同Test.BinarySearch.binarySearchForLastTime()做对比
	 */
	public static int getLastK(int[] dataArray, int k, int start, int end) {
		// 递归边界条件1（数组中不包含数字k）
		if(start > end) {
			return -1;
		}
		
		int middle = (start + end) >> 1;
		if(dataArray[middle] == k) {
			// 递归边界条件2（验证中间元素的后一个元素是否与中间元素不相等 或者 中间元素为数组尾元素，如果成立则中间元素为最后一个k，此时直接返回）
			if((middle <= dataArray.length - 2 && dataArray[middle + 1] != k) || middle == dataArray.length - 1) {	// 注意数组边界判断（分情况，且防越界）
				return middle;
			} else {
				start = middle + 1;
			}
		} else if(dataArray[middle] > k) {
			end = middle - 1;
		} else {
			start = middle + 1;
		}
		
		return getLastK(dataArray, k, start, end);
	}
	
	/**
	 * 
	 * @param dataArray 数组
	 * @param k 数字
	 * @return 数字在排序数组中出现的次数
	 * @description 根据最后k下标与第一k下标计算出数字在排序数组中出现的次数
	 */
	public static int getNumberOfK(int[] dataArray, int k) {
		// 输入验证
		if(dataArray == null || dataArray.length == 0) {
			return 0;
		}
		
		int firstK = getFirstK(dataArray, k, 0, dataArray.length - 1);
		int lastK = getLastK(dataArray, k, 0, dataArray.length - 1);
		// 注意判断firstK和lastK是否等于-1（-1则代表数组中不包含数字k）
		if(firstK != -1 && lastK != -1) {
			return lastK - firstK + 1;
		}
		
		return 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] dataArray = {1, 2, 3, 3, 3, 3, 4, 5};
		System.out.println("数字在排序数组中出现的次数：" + getNumberOfK(dataArray, 3));
	}

}
