/**
 * @description 求1+2+...n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
 */
package chapter6;

public class Accumulate {

	/**
	 * 
	 * @param n
	 * @return
	 * @description 通过递归的方式进行计算累加，关键在于如何结束递归：利用短路性质
	 * 				采用逻辑与或的方式来短路，与的时候通过n>0来短路，这样在n=0的时候不需要计算递归的值，或的时候通过n==0来短路，在n=0的时候不需要计算递归的值
	 */
	public static int getAccumulattion(int n) {
		int sum = n;
		// boolean temp = (n > 0) && ((sum += Sum_Solution(n-1)) > 0);
		@SuppressWarnings("unused")
		boolean temp = (n == 0) || ((sum += getAccumulattion(n - 1)) > 0);
		return sum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("累计和为：" + getAccumulattion(10));
	}

}
