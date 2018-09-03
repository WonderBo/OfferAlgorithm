/**
 * @description 输入一整数数组，调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分
 * 				注意事项： 1.如果需要按照某种规律重新调整数组元素的顺序（类似于分割数组），为使时间复杂度降低，可以考虑数组首尾各设定一指针并向中间靠拢（eg.快排与本例）
 * 						 2.将代码块封装成函数的目的：（1）功能复杂，提取出来可读性提高；（2）解耦和，便于代码的重用，提高功能扩展性
 */
package chapter3;

public class ReorderArray {

	/**
	 * 
	 * @param intArray 未改变顺序的整数数组
	 * @description 调整数组中数字的顺序使得所有奇数位于偶数前（判断标准固定/写死：根据奇偶性进行数组划分）
	 */
	public static void reorderOddEven(int[] intArray) {
		//输入验证
		if(intArray == null || intArray.length == 0) {
			return;
		}

		//设定头指针和尾指针
		int headIndex = 0;
		int tailIndex = intArray.length - 1;

		while(headIndex < tailIndex) {
			//后面由于headIndex和tailIndex在循环内部发生改变，因此后面的循环判断与分支判断都需要验证headIndex和tailIndex的大小关系
			while(headIndex < tailIndex && (intArray[headIndex] & 1) == 1) {	//位运算获取整数奇偶性
				headIndex ++;
			}

			while(headIndex < tailIndex && (intArray[tailIndex] & 1) != 1) {
				tailIndex --;
			}

			if(headIndex < tailIndex) {
				int temp = intArray[headIndex];
				intArray[headIndex] = intArray[tailIndex];
				intArray[tailIndex] = temp;
			}
		}
	}

	/**
	 * 
	 * @param intArray 未改变顺序的整数数组
	 * @description 根据标准调整数组中数字的顺序（判断标准分离/解耦：根据标准进行数组划分，比如奇偶性，正负性，能否被3整除）
	 * 				注： isOdd 划分标准（解耦和，提高代码的可重用性，更好地进行功能扩展）
	 */
	public static void reorder(int[] intArray) {
		//输入验证
		if(intArray == null || intArray.length == 0) {
			return;
		}

		//设定头指针和尾指针
		int headIndex = 0;
		int tailIndex = intArray.length - 1;

		while(headIndex < tailIndex) {
			//后面由于headIndex和tailIndex在循环内部发生改变，因此后面的循环判断与分支判断都需要验证headIndex和tailIndex的大小关系
			while(headIndex < tailIndex && isOdd(intArray[headIndex])) {
				headIndex ++;
			}

			while(headIndex < tailIndex && !isOdd(intArray[tailIndex])) {
				tailIndex --;
			}

			if(headIndex < tailIndex) {
				int temp = intArray[headIndex];
				intArray[headIndex] = intArray[tailIndex];
				intArray[tailIndex] = temp;
			}
		}
	}

	/**
	 * 
	 * @param num 整数
	 * @return boolean 奇数返回true，偶数返回false
	 * @description 位运算获取整数奇偶性
	 */
	public static boolean isOdd(int num) {
		return (num & 1) == 1;
	}

	/**
	 * @param args
	 * @description 测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		//判断标准固定/写死
		reorderOddEven(intArray);
		for(int i = 0; i < intArray.length; i++) {
			System.out.print(intArray[i] + " ");
		}

		int[] intArray2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		//判断标准分离/解耦
		reorder(intArray2);
		System.out.println();
		for(int i = 0; i < intArray2.length; i++) {
			System.out.print(intArray2[i] + " ");
		}
	}

}
