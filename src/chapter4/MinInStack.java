/**
 * @description 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数。在该栈中，调用min、push和pop的时间复杂度都是O(1)
 */
package chapter4;

import java.util.Stack;

public class MinInStack {

	private Stack<Integer> dataStack;	// 数据栈
	private Stack<Integer> minStack;	// 辅助栈（与数据栈保持同步，保存最小值）
	
	public MinInStack() {
		dataStack = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}
	
	/**
	 * 
	 * @param num
	 * @description 入栈操作
	 */
	public void push(int num) {
		dataStack.push(num);
		
		// 辅助栈入栈前需要先进行判断，再入栈当前最小值
		if(minStack.isEmpty() || num < minStack.peek()) {
			minStack.push(num);
		} else {
			minStack.push(minStack.peek());
		}
	}
	
	/**
	 * @description 出栈操作
	 */
	public void pop() {
		// 出栈前进行空栈判断
		if(dataStack.isEmpty() || minStack.isEmpty()) {
			return;
		}
		
		dataStack.pop();
		minStack.pop();
	}
	
	/**
	 * 
	 * @return
	 * @description 获取栈内最小元素
	 */
	public int min() {
		return minStack.peek();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinInStack minInStack = new MinInStack();
		minInStack.push(3);
		minInStack.push(4);
		System.out.println("当前栈内最小元素：" + minInStack.min());
		minInStack.push(2);
		minInStack.push(1);
		System.out.println("当前栈内最小元素：" + minInStack.min());
		minInStack.pop();
		minInStack.pop();
		System.out.println("当前栈内最小元素：" + minInStack.min());
		minInStack.push(0);
		System.out.println("当前栈内最小元素：" + minInStack.min());
	}

}
