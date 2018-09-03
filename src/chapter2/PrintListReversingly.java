/**
 * @description 不改变链表的前提下，从尾到头打印每个节点值
 */
package chapter2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class PrintListReversingly {
	
	/**
	 * @param linkedList
	 * @description 根据遍历与打印顺序'后进先出'的特点，使用栈实现这种顺序
	 */
	public static void printListReversingly_Iteratively(LinkedList<Object> linkedList) {
		Stack<Object> stack = new Stack<Object>();
		
		//利用迭代器遍历链表元素（迭代器本质上与链表类似：利用头节点与后继指针进行遍历）并入栈
		Iterator<Object> iterator = linkedList.iterator();
		while(iterator.hasNext()) {
			stack.push(iterator.next());
		}
		
		//遍历栈并迭代出栈打印元素
		while(!stack.empty()) {
			Object value = stack.peek();
			System.out.println(value);
			stack.pop();
		}
	}
	
	/**
	 * 
	 * @param linkedList
	 * @param index 下标指针
	 * @description 利用递归方式从尾到头打印每个节点值,但是当链表很长时，导致函数调用层数很深，可能使函数调用栈溢出
	 */
	public static void printListReversingly_Recursively(LinkedList<Object> linkedList, int index) {
		if(index < linkedList.size()) {
			//注意方法传参时i++与++i是不同的（i++与++i只有单独成句时效果才相同）
			printListReversingly_Recursively(linkedList, ++index);
			System.out.println(linkedList.get(--index));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Object> linkedList = new LinkedList<Object>();
		linkedList.add(1);
		linkedList.add(3);
		linkedList.add(5);
		
		System.out.println("栈迭代方式：");
		printListReversingly_Iteratively(linkedList);
		
		System.out.println("递归方式：");
		printListReversingly_Recursively(linkedList, 0);
	}

}
