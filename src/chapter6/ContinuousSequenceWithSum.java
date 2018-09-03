/**
 * @description 输入一个正数s，打印出所有和为s的连续正数序列（至少含有两个数）
 */
package chapter6;

public class ContinuousSequenceWithSum {

	/**
	 * 
	 * @param s 目标和
	 * @description 利用双首指针遍历连续数组，打印出所有和为s的连续正数序列，偏大则后移前指针，偏小则后移后指针
	 */
	public static void getContinuousSequenceWithSum(int s) {
		// 输入验证
		if(s < 3) {
			return;
		}
		
		// 由于连续序列存在连续性，利用双首指针遍历连续数组[1, s-1]
		int aheadIndex = 1;
		int behindIndex = 2;
		int totalSum = aheadIndex + behindIndex;
		while((aheadIndex < behindIndex) && (behindIndex < s)) {	// 循环结束条件
			if(totalSum == s) {		// 由于打印所有连续序列，因此此处不为循环结束条件
				printContinuousSequence(aheadIndex, behindIndex);
				
				// 将后指针后移，寻找其他可能连续序列，否则此处会陷入死循环
				behindIndex ++;
				totalSum += behindIndex;	// 在前一个序列的和的基础上求操作之后的序列的和，这样可以减少不必要的重复计算，从而提高代码的效率，下同
			} else if(totalSum < s) {
				behindIndex ++;
				totalSum += behindIndex;
			} else {
				totalSum -= aheadIndex;
				aheadIndex ++;
			}
		}
	}
	
	/**
	 * 
	 * @param aheadIndex 前指针
	 * @param behindIndex 后指针
	 * @description 打印从前指针到后指针的连续序列
	 */
	public static void printContinuousSequence(int aheadIndex, int behindIndex) {
		for(int i = aheadIndex; i <= behindIndex; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getContinuousSequenceWithSum(15);
	}

}
