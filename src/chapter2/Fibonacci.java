/**
 * @description 求出裴波那契数列的第n项
 */
package chapter2;

public class Fibonacci {

	/**
	 * 
	 * @param n
	 * @return
	 * @description 递归方式实现裴波那契数列，由于大量的重复计算，导致计算复杂度以n的指数的方式增加
	 */
	public static int getFibonacciItemN_Recursive(int n) {
		//参数验证  和  边界条件
		if(n <= 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		}
		return getFibonacciItemN_Recursive(n - 1) + getFibonacciItemN_Recursive(n - 2);
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 * @description 循环方式实现裴波那契数列，从下往上计算，把递归的算法用循环实现
	 */
	public static int getFibonacciItemN_Iterative(int n) {
		//参数验证
		if(n < 0) {
			System.out.println("参数错误");
			return -1;
		}
		
		//边界条件
		int[] boundaryArr = {0,1};
		if(n < 2) {
			return boundaryArr[n];
		}
		
		//循环实现避免重复计算
		int fibonacciNumberOne = 0;
		int fibonacciNumberTwo = 1;
		int fibonacciNumber = 0;
		for(int i=2; i<=n; i++) {
			fibonacciNumber = fibonacciNumberOne + fibonacciNumberTwo;
			
			fibonacciNumberOne = fibonacciNumberTwo;
			fibonacciNumberTwo = fibonacciNumber;
		}
		
		return fibonacciNumber;
	}
	
	/**
	 * @param args
	 * @description 测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 30;
		
		System.out.println("递归方式实现裴波那契数列");
		long startTime = System.currentTimeMillis();
		System.out.println("第" + n + "项结果为：" + getFibonacciItemN_Recursive(n));
		long endTime = System.currentTimeMillis();
		System.out.println("运行时间为：" + (endTime - startTime));
		
		System.out.println("循环方式实现裴波那契数列");
		startTime = System.currentTimeMillis();
		System.out.println("第" + n + "项结果为：" + getFibonacciItemN_Iterative(n));
		endTime = System.currentTimeMillis();
		System.out.println("运行时间为：" + (endTime - startTime));
	}

}
