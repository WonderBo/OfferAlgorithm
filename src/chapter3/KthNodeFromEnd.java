/**
 * @description 输入一链表，输出该链表倒数第k个节点（节点从1开计数，即头节点为第一个节点，尾节点为倒数第一个节点）
 */
package chapter3;

public class KthNodeFromEnd {

	/**
	 * @description 节点类（内部类）
	 */
	private class Node<E> {
		// 数据域
		private E e;
		// 引用域
		private Node<E> nextNode;

		public Node() {}

		public Node(E e) {
			this.e = e;
		}

		public E getE() {
			return e;
		}

		public void setE(E e) {
			this.e = e;
		}

		public Node<E> getNextNode() {
			return nextNode;
		}

		public void setNextNode(Node<E> nextNode) {
			this.nextNode = nextNode;
		}
	}
	
	/**
	 * 
	 * @param list 链表（头节点）
	 * @param k 倒数节点数
	 * @return Node<Integer> 倒数第k个节点
	 * @description 当单指针不方便解决问题时，可尝试双指针遍历单向链表，关键在于根据题目特点，选择指针出发时间（固定距离）与前进速度（距离倍数）来解决问题
	 * 				此题固定距离k可以选择指针出发时间不同来解决，求链表中间节点的问题可以选择指针前进速度（1：2）不同来解决
	 */
	public Node<Integer> findKthNodeFromEnd(Node<Integer> list, int k) {
		// 输入验证—1
		if(list == null || k <= 0) {
			return null;
		}
		
		// 声明前后两个指针并进行初始化
		Node<Integer> aheadIndex = list;
		Node<Integer> behindIndex = list;
		
		// 先移动前指针直至前后指针间形成固定间距为k-1
		for(int i = 0; i < k - 1; i++) {
			// 输入验证—2（当k大于链表长度时，返回null）
			if(aheadIndex.getNextNode() != null) {
				aheadIndex = aheadIndex.getNextNode();
			} else {
				return null;
			}		
		}
		
		// 同时移动前后指针直至前指针到达尾节点
		while(aheadIndex.getNextNode() != null) {
			aheadIndex = aheadIndex.getNextNode();
			behindIndex = behindIndex.getNextNode();
		}
		
		// 返回后指针指向的节点
		return behindIndex;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 实例化内部类的时候要先实例化外部类
		KthNodeFromEnd kthNodeFromEnd = new KthNodeFromEnd();
		
		Node<Integer> a = kthNodeFromEnd.new Node<Integer>(1);
		Node<Integer> b = kthNodeFromEnd.new Node<Integer>(2);
		Node<Integer> c = kthNodeFromEnd.new Node<Integer>(3);
		Node<Integer> d = kthNodeFromEnd.new Node<Integer>(4);
		Node<Integer> e = kthNodeFromEnd.new Node<Integer>(5);
		Node<Integer> f = kthNodeFromEnd.new Node<Integer>(6);
		
		//构建单向链表
		a.setNextNode(b);
		b.setNextNode(c);
		c.setNextNode(d);
		d.setNextNode(e);
		e.setNextNode(f);
		
		System.out.println(kthNodeFromEnd.findKthNodeFromEnd(a, 3).getE());
	}

}
