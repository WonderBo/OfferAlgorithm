/**
 * @description 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等
 */
package chapter4;

import java.util.Stack;

public class StackPushPopOrder {

	/**
	 * 
	 * @param pushArray
	 * @param popArray
	 * @return
	 * @description 建立辅助栈，根据压入序列与弹出序列模拟入栈、出栈操作
	 */
	public static boolean isPopOrder(int[] pushArray, int[] popArray) {
		// 输入验证
		if(pushArray == null || popArray == null || pushArray.length == 0 
				|| popArray.length == 0 || pushArray.length != popArray.length) {
			return false;
		}
		
		Stack<Integer> stack = new Stack<Integer>();	// 辅助栈
		int popIndex = 0;	// 标志弹出序列下标
		// 由于压入序列的所有元素都必须入栈，因此采用for外层循环
		for(int i = 0; i < pushArray.length; i++) {
			stack.push(pushArray[i]);
			
			// 由于根据弹出序列的元素值可能会连续出栈若干次，因此采用内层while循环
			while(!stack.isEmpty() && stack.peek() == popArray[popIndex]) {		// 注意弹栈与获取栈顶元素时需要判断栈是否为空
				stack.pop();
				popIndex ++;
			}
		}
		
		// 根据辅助栈是否剩余元素判断压入序列与弹出序列是否相对应
		return stack.isEmpty();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] pushArray = {1, 2, 3, 4, 5};
		int[] popArray = {4, 5, 3, 2, 1};
		System.out.println("栈的压入、弹出序列是否对应：" + isPopOrder(pushArray, popArray));
	}

}
