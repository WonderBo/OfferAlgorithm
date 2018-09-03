/**
 * @description 用两个队列实现一个栈，功能声明如下：弹栈与压栈
 * 				在java5中新增加了java.util.Queue接口，用以支持队列的常见操作。该接口扩展了java.util.Collection接口
 * 				使用offer()来加入元素，使用poll()来获取并移出元素，值得注意的是LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用
 */
package chapter2;

import java.util.LinkedList;
import java.util.Queue;

public class StackWithTwoQueues<T> {

	private Queue<T> queue1;
	private Queue<T> queue2;

	public Queue<T> getQueue1() {
		return queue1;
	}
	public void setQueue1(Queue<T> queue1) {
		this.queue1 = queue1;
	}
	public Queue<T> getQueue2() {
		return queue2;
	}
	public void setQueue2(Queue<T> queue2) {
		this.queue2 = queue2;
	}

	public StackWithTwoQueues() {
		queue1 = new LinkedList<T>();
		queue2 = new LinkedList<T>();
	}

	/**
	 * 
	 * @param t 压栈节点值
	 * @description 在栈顶部插入节点
	 */
	public void pushStack(T t) {
		if(queue2.size() > 0) {
			queue2.offer(t);
		} else {
			queue1.offer(t);
		}
	}

	/**
	 * 
	 * @return T 删除的栈顶部节点值
	 * @description 弹栈
	 */
	public T popStack() {
		//根据队列是否为空选定源队列与目标队列
		Queue<T> sourceQueue = null;
		Queue<T> targetQueue = null;
		if(queue1.size() > 0) {
			sourceQueue = queue1;
			targetQueue = queue2;
		} else if(queue2.size() > 0) {
			sourceQueue = queue2;
			targetQueue = queue1;
		} else {
			System.out.println("栈为空");
			return null;
		}
		
		//将源队列中的元素进行出队列操作，再对该元素进行入队列操作插入到目标队列中，直至源队列只剩下一个元素
		if(targetQueue.size() <= 0) {
			while(sourceQueue.size() > 1) {
				T temp = sourceQueue.peek();
				sourceQueue.poll();
				targetQueue.offer(temp);
			}
		}
		
		//源队列剩下的最后一个元素即为应该删除的元素，进行获取，然后出队列，最后返回即可
		T head = sourceQueue.peek();
		sourceQueue.poll();
		return head;
	}

	/**
	 * @param args
	 * @description 测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackWithTwoQueues<Integer> stackWithTwoQueues = new StackWithTwoQueues<Integer>();
		stackWithTwoQueues.pushStack(1);
		stackWithTwoQueues.pushStack(2);
		stackWithTwoQueues.pushStack(3);
		System.out.println(stackWithTwoQueues.popStack());
		stackWithTwoQueues.pushStack(4);
		System.out.println(stackWithTwoQueues.popStack());
		System.out.println(stackWithTwoQueues.popStack());
		System.out.println(stackWithTwoQueues.popStack());
	}

}
