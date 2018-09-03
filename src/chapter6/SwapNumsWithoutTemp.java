/**
 * @description 不使用新的变量，交换两个变量的值
 */
package chapter6;

public class SwapNumsWithoutTemp {

	/**
	 * 
	 * @param intArray 整型数组
	 * @param index1 下标1
	 * @param index2 下标2
	 * @description 基于加减法，实现不使用新的变量，交换两个变量的值
	 */
	public static void swapNumsWithoutTemp(int[] intArray, int index1, int index2) {
		intArray[index1] = intArray[index1] + intArray[index2];
		
		intArray[index2] = intArray[index1] - intArray[index2];
		intArray[index1] = intArray[index1] - intArray[index2];
	}
	
	/**
	 * 
	 * @param intArray 整型数组
	 * @param index1 下标1
	 * @param index2 下标2
	 * @description 基于异或运算，实现不使用新的变量，交换两个变量的值
	 */
	public static void swapNumsWithoutTemp2(int[] intArray, int index1, int index2) {
		intArray[index1] = intArray[index1] ^ intArray[index2];
		
		intArray[index2] = intArray[index1] ^ intArray[index2];
		intArray[index1] = intArray[index1] ^ intArray[index2];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = {5, 7};
		System.out.println("元素1：" + intArray[0] + "  元素2：" + intArray[1]);
		swapNumsWithoutTemp(intArray, 0, 1);
		System.out.println("元素1：" + intArray[0] + "  元素2：" + intArray[1]);
		swapNumsWithoutTemp2(intArray, 0, 1);
		System.out.println("元素1：" + intArray[0] + "  元素2：" + intArray[1]);
	}

}
