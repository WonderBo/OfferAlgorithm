/**
 * @description 实现函数double Power(double base, int exponent)，求base的exponent次方，不得使用库函数，且不考虑大数问题
 */
package chapter3;

public class Power {
	//采用全局变量来标志是否发生异常（true为发生异常）
	public boolean invalidInput = false;
	
	/**
	 * 
	 * @param base		底数
	 * @param exponent	指数
	 * @return double
	 * @description 求解底数的指数次方，对于思维的全面性，应考虑负面测试，边界测试，功能测试三个方面
	 */
	public double power(double base, int exponent) {
		this.invalidInput = false;
		
		//Java中int类型数据的大小比较可以使用双等号，double类型则不能直接使用双等号来比较大小,如果使用的话得到的结果将永远是不相等
		//负面测试----应对异常情况（底数为0，指数为负，此时分母为0）
		if(Double.doubleToLongBits(base) == Double.doubleToLongBits(0.0) && exponent <= 0) {
			this.invalidInput = true;
			return 0.0;
		}
		
		int unsignedExponent = exponent;
		//思维的完整性：分情况讨论（指数为正，负）
		if(exponent < 0) {
			unsignedExponent = - exponent;
		}
		
		//double result = powerWithUnsignedExponent(base, unsignedExponent);
		double result = powerWithUnsignedExponent_Optimization(base, unsignedExponent);
				
		if(exponent < 0) {
			result = 1 / result;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param base				底数
	 * @param unsignedExponent	无符号指数
	 * @return double
	 * @description 常规方法求解底数的正数指数次方
	 */
	public double powerWithUnsignedExponent(double base, int unsignedExponent) {
		double result = 1.0;
		
		for(int i = 1; i <= unsignedExponent; i++) {
			result *= base;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param base				底数
	 * @param unsignedExponent	无符号指数
	 * @return double
	 * @description 优化方法求解底数的正数指数次方(分治思想--递归实现)
	 */
	public double powerWithUnsignedExponent_Optimization(double base, int unsignedExponent) {
		//边界条件
		if(unsignedExponent == 0) {
			return 1;
		}
		if(unsignedExponent == 1) {
			return base;
		}
		
		//右移运算符代替除以2(二分法)
		double result = powerWithUnsignedExponent_Optimization(base, unsignedExponent >> 1);
		result *= result;
		
		//位与运算符代替求余运算符（%）来判断一个数为奇数还是偶数
		if((unsignedExponent & 0x1) == 1) {
			result *= base;
		}
		
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Power power = new Power();
		double result = power.power(2, 10);
		
		//调用函数时需要检查全局变量invalidInput以判断是否发生异常
		if(power.invalidInput == true) {
			System.out.println("发生异常：底数为0.0， 指数非正");
		} else {
			System.out.println(result);
		}
	}

}
