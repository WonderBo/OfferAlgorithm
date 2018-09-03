/**
 * @description 把只包含因子2，3和5的数称作丑数，求从小到大的顺序的第1500个丑数。习惯上把1当作第一个丑数。
 */
package chapter5;

public class UglyNumber {

	/**
	 * 
	 * @param number 整数
	 * @return
	 * @description 丑数判断
	 */
	public boolean isUglyNumber(int number) {
		while(number % 2 == 0) {
			number /= 2;
		} 
		while(number % 3 == 0) {
			number /= 3;
		} 
		while(number % 5 == 0) {
			number /= 5;
		}

		if(number == 1) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param index 
	 * @return
	 * @description 蛮力求解，大量非丑数的判定计算导致时间复杂度很高
	 */
	public int getUglyNumber(int index) {
		// 输入验证
		if(index <= 0) {
			return 0;
		}

		int uglyCount = 0;
		int number = 0;
		while(uglyCount < index) {
			number ++;
			if(isUglyNumber(number)) {
				uglyCount ++;
			}
		}

		return number;
	}


	/**
	 * 
	 * @param number1 整数1
	 * @param number2 整数2
	 * @param number3 整数3
	 * @return
	 * @description 获取三个整数中的最小整数
	 */
	public int getMinNumber(int number1, int number2, int number3) {
		int minNumber = number1;
		if(number2 < minNumber) {
			minNumber = number2;
		}
		if(number3 < minNumber) {
			minNumber = number3;
		}

		return minNumber;
	}

	/**
	 * 
	 * @param index
	 * @return
	 * @description 创建数组存储排好序的丑数，每个丑数都是由前面的某个丑数乘以2或3或5得到的，从而避免在非丑数上耗费时间（空间换时间）
	 */
	public int getUglyNumber2(int index) {
		// 输入验证
		if(index <= 0) {
			return 0;
		}

		int[] uglyArray = new int[index];	// 排好序的丑数数组
		uglyArray[0] = 1;	// 丑数数组初始第一个元素（作为计算后面元素的依据）

		int uglyIndex = 1;	// 丑数下标
		// 分别为乘以2或3或5后大于当前丑数数组最大值的丑数下标
		int uglyPreIndexOf2 = 0;	
		int uglyPreIndexOf3 = 0;	
		int uglyPreIndexOf5 = 0;

		while(uglyIndex < index) {
			uglyArray[uglyIndex] = getMinNumber(uglyArray[uglyPreIndexOf2] * 2, uglyArray[uglyPreIndexOf3] * 3, uglyArray[uglyPreIndexOf5] * 5);

			// 关键在于维护好 乘以2或3或5后取最小值 得到最新丑数 的丑数下标，从而避免每次都对每个数组元素分别乘以2或3或5取最小值（因为数组是有序的）
			while(uglyArray[uglyPreIndexOf2] * 2 <= uglyArray[uglyIndex]) {
				uglyPreIndexOf2 ++;
			}
			while(uglyArray[uglyPreIndexOf3] * 3 <= uglyArray[uglyIndex]) {
				uglyPreIndexOf3 ++;
			}
			while(uglyArray[uglyPreIndexOf5] * 5 <= uglyArray[uglyIndex]) {
				uglyPreIndexOf5 ++;
			}
			
			uglyIndex ++;
		}

		return uglyArray[uglyIndex - 1];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UglyNumber uglyNumber = new UglyNumber();
		long currentTime = System.currentTimeMillis();
		System.out.println("第1500个丑数为：" + uglyNumber.getUglyNumber(1500));
		System.out.println("方法一耗费时间：" + (System.currentTimeMillis() - currentTime));

		currentTime = System.currentTimeMillis();
		System.out.println("第1500个丑数为：" + uglyNumber.getUglyNumber2(1500));
		System.out.println("方法二耗费时间：" + (System.currentTimeMillis() - currentTime));
	}

}
