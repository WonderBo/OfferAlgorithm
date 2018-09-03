/**
 * @description 请定义一个队列并实现函数max得到队列里的最大值，要求函数max，push_back和pop_front的时间复杂度都是O(1)
 * 				滑动窗口与队列的转换；滑动窗口最大值 -> 双端队列最大值 -> 双端队列头元素
 */
package chapter6;

import java.util.ArrayDeque;
import java.util.Deque;

class InternalData<T> {
	T val;
	int index;	// 下标的作用在于id，用于出列队时核对data双端队列与max双端队列的头部元素是否一样，一样则也需要出队列max双端队列
	public InternalData(T val, int index) {
		this.val = val;
		this.index = index;
	}
}

public class QueueWithMax {
	// Deque（双端队列）和LinkedList（双向链表）实现均可，其中Deque包含LinkedList的相关操作方法
	private Deque<InternalData<Integer>> dataDeque = new ArrayDeque<InternalData<Integer>>();   // 存放数据
	private Deque<InternalData<Integer>> maxDeque = new ArrayDeque<InternalData<Integer>>();   // 存放可能是队列最大值的数据(当前队列最大值为其头部数据)
	private int currentIndex;  // 当前下标，相当于队列元素的自增长id
	
	/**
	 * 
	 * @param number 入队列元素
	 * @description 入队列data双端队列头部元素（同时更新max双端队列，先从队列尾部出队列不可能为最大值的元素，然后从队列尾部入队列当前元素），注意更新当前下标id
	 */
	public void push_back(Integer number) {
		while(!maxDeque.isEmpty() && maxDeque.peekLast().val <= number) {
			maxDeque.pollLast();
		}
		
		InternalData<Integer> internalData = new InternalData<Integer>(number, currentIndex);
		maxDeque.offerLast(internalData);
		dataDeque.offerLast(internalData);
		
		currentIndex ++;
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description 出队列data双端队列头部元素（如果data双端队列与max双端队列的头部元素下标一样，则也需要出队列max双端队列头部元素）
	 */
	public void pop_front() throws Exception {
		// 队列为空验证
		if(maxDeque.isEmpty()) {
			throw new Exception("队列为空，不可出队列");
		}
		
		if(dataDeque.peekFirst().index == maxDeque.peekFirst().index) {
			maxDeque.pollFirst();
		}
		dataDeque.pollFirst();
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @description 返回max双端队列的头元素
	 */
	public Integer max() throws Exception {
		// 队列为空验证
		if(maxDeque.isEmpty()) {
			throw new Exception("队列为空，不可出队列");
		}
		
		return maxDeque.peekFirst().val;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		QueueWithMax queueWithMax = new QueueWithMax();
		queueWithMax.push_back(3);
		queueWithMax.push_back(7);
		System.out.println("当前队列最大值：" + queueWithMax.max());
		queueWithMax.pop_front();
		System.out.println("当前队列最大值：" + queueWithMax.max());
		queueWithMax.push_back(5);
		queueWithMax.pop_front();
		System.out.println("当前队列最大值：" + queueWithMax.max());
	}

}
