/**
 * @description 用两个栈实现一个队列，功能声明如下：在队列尾部插入节点和在队列头部删除节点
 */
package chapter2;

import java.util.Stack;

public class QueueWithTwoStacks<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;
	
	public Stack<T> getStack1() {
		return stack1;
	}
	public void setStack1(Stack<T> stack1) {
		this.stack1 = stack1;
	}
	public Stack<T> getStack2() {
		return stack2;
	}
	public void setStack2(Stack<T> stack2) {
		this.stack2 = stack2;
	}
	
	public QueueWithTwoStacks() {
		stack1 = new Stack<T>();
		stack2 = new Stack<T>();
	}
	
	/**
	 * 
	 * @param t 插入的队列尾部节点值
	 * @description 在队列尾部插入节点
	 */
	public void appendTail(T t) {
		stack1.push(t);
	}
	
	/**
	 * 
	 * @return T 删除的队列头部节点值
	 * @description 在队列头部删除节点
	 */
	public T deleteHead() {
		//栈2为空则将栈1的全部元素先弹栈再压栈进入栈2
		if(stack2.size() <= 0) {
			while(stack1.size() > 0) {
				T temp = stack1.peek();
				stack1.pop();
				stack2.push(temp);
			}
		}
		
		//经过元素转移后栈2依旧为空则证明队列为空，为后面栈2弹栈做判断
		if(stack2.size() == 0) {
			System.out.println("队列为空");
		}
		//栈2不为空则直接对栈2进行弹栈
		T head = stack2.peek();
		stack2.pop();
		return head;
	}

	/**
	 * @param args
	 * @description 测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueueWithTwoStacks<Integer> queueWithTwoStacks = new QueueWithTwoStacks<Integer>();
		queueWithTwoStacks.appendTail(1);
		queueWithTwoStacks.appendTail(2);
		queueWithTwoStacks.appendTail(3);
		System.out.println(queueWithTwoStacks.deleteHead());
		queueWithTwoStacks.appendTail(4);
		System.out.println(queueWithTwoStacks.deleteHead());
		System.out.println(queueWithTwoStacks.deleteHead());
		System.out.println(queueWithTwoStacks.deleteHead());
	}

}
