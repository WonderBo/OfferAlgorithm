/**
 * @description 给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值
 */
package chapter6;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class MaxInSlidingWindow {

	/**
	 * 
	 * @param numArray 数组
	 * @param size 滑动窗口的大小
	 * @return
	 * @description 使用双端队列保存有可能是滑动窗口最大值的数字的下标，从而求出所有滑动窗口里的最大值
	 * 				下标：（1）方便查找数组元素；（2）方便判断元素是否还在滑动窗口中
	 * 				双端队列：（1）从尾部删除不可能是最大值的数字下标；（2）从头部删除移除滑动窗口的数字下标
	 */
	public static List<Integer> getMaxInSlidingWindow(int[] numArray, int size) {
		// 输入验证
		if(numArray == null || numArray.length == 0 || size < 1 || size > numArray.length) {
			return null;
		}
		
		List<Integer> resultList = new ArrayList<Integer>();	// 保存所有滑动窗口里的最大值
		// Deque：double ended queue（双端队列）此接口定义在双端队列两端访问元素的方法。提供插入、移除和检查元素的方法（用LinkedList双向链表实现类似）
		Deque<Integer> indexDeque = new ArrayDeque<Integer>();
		
		// 当滑动窗口未满时，此时先从 队列尾部出队列 不可能为最大值的下标元素，然后从 队列尾部入队列 当前下标元素
		for(int i = 0; i < size; i++) {
			while(!indexDeque.isEmpty() && numArray[indexDeque.peekLast()] <= numArray[i]) {
				indexDeque.pollLast();
			}
			indexDeque.offerLast(i);
		}
		
		// 当滑动窗口已满时，此时先从 队列头部出队列 移除滑动窗口的下标元素， 然后从 队列尾部出队列 不可能为最大值的下标元素，最后从 队列尾部入队列 当前下标元素
		for(int i = size; i < numArray.length; i++) {
			// 将双端队列头元素对应的数字（当前滑动窗口最大值）加入到结果链表
			resultList.add(numArray[indexDeque.peekFirst()]);
			
			if(!indexDeque.isEmpty() && i - indexDeque.peekFirst() >= size) {
				indexDeque.pollFirst();
			}
			while(!indexDeque.isEmpty() && numArray[indexDeque.peekLast()] <= numArray[i]) {
				indexDeque.pollLast();
			}
			indexDeque.offerLast(i);
		}
		// 将最后一个滑动窗口的最大值加入到结果链表
		resultList.add(numArray[indexDeque.peekFirst()]);
		
		return resultList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numArray = {2, 3, 4, 2, 6, 2, 5, 1};
		List<Integer> resultList = getMaxInSlidingWindow(numArray, 3);
		Iterator<Integer> iterator = resultList.iterator();
		System.out.print("滑动窗口的最大值：");
		while(iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
	}

}
