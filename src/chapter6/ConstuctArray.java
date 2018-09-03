/**
 * @description 给定一个数组A[0,1,2...n-1]，请构建一个数组B[0,1,2...n-1]，其中B中的元素B[i]=A[0]*A[1]...A[i-1]*A[i+1]*...A[n-1]。不能使用除法
 */
package chapter6;

public class ConstuctArray {

	/**
	 * 
	 * @param intArray 输入数组
	 * @return
	 * @description 根据题目特征，将数组B转换为一个矩阵来创建，其中将总的乘积分为两部分乘积，依次求解各部分乘积再合并
	 */
	public static int[] getConstuctArray(int[] intArray) {
		// 输入验证
		if(intArray == null || intArray.length <= 0) {
			return null;
		}
		
		// 从上至下计算左边部分的乘积，并存入数组
		int[] resultArray = new int[intArray.length];
		resultArray[0] = 1;
		for(int i = 1; i < resultArray.length; i++) {
			resultArray[i] = resultArray[i - 1] * intArray[i - 1];
		}
		
		// 从下至上计算右边部分的乘积，并暂时存入temp，而后更新到数组
		int temp = 1;
		for(int i = resultArray.length - 2; i >= 0; i--) {
			temp *= intArray[i + 1];
			resultArray[i] *= temp;
		}
		
		return resultArray;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArray = {1, 2, 4, 5, 7};
		int[] resultArray = getConstuctArray(intArray);
		System.out.print("构建的乘积数组为：");
		for(int i : resultArray) {
			System.out.print(i + " ");
		}
	}

}
