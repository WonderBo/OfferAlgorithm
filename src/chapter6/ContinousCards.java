/**
 * @description 从扑克牌中随机抽出5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2~10为数字本身，A为1，J、Q、K分别为11、12、13，大小王可以看成任何数
 */
package chapter6;

import java.util.Arrays;

public class ContinousCards {

	/**
	 * 
	 * @param cardArray 抽出牌数组
	 * @return
	 * @description 将熟悉的扑克牌抽象成数组，把找顺子的过程通过排序、计数等步骤实现
	 */
	public static boolean isContinousCards(int[] cardArray) {
		// 输入验证
		if(cardArray == null || cardArray.length < 1) {
			return false;
		}
		
		// 排序数组（为简洁易懂直接调用函数即可）
		Arrays.sort(cardArray);
		int numOfZero = 0;	// 大小王数量
		int numOfGrap = 0;	// 中间缺数字数量
		// 统计数组中0的个数
		for(int i = 0; i < cardArray.length; i++) {
			if(cardArray[i] == 0) {
				numOfZero ++;
			}
		}
		
		// 统计数组中的间隔数目
		for(int i = numOfZero; i < cardArray.length - 1; i++) {		// 注意数组下标越界
			// 如果两个数相等，有对子，则不可能是顺子
			if(cardArray[i] == cardArray[i + 1]) {
				return false;
			}
			numOfGrap += (cardArray[i + 1] - cardArray[i] - 1);
		}
		
		if(numOfZero >= numOfGrap) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] cardArray = {0, 1, 3, 4, 5};
		System.out.println("抽出的牌是否为顺子：" + isContinousCards(cardArray));
	}

}
