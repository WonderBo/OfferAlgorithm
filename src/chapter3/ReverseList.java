/**
 * @description 输入一个链表的头节点，反转该链表并输出反转后链表的头节点
 */
package chapter3;

public class ReverseList {

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
	 * @param list 原链表
	 * @return Node<Integer> 反转后链表（的头节点）
	 * @description 循环方式实现反转链表，关键在于利用后节点来保存链表后部分的信息以防止链表的断裂，同时注意循环的起始与结束情况
	 */
	public Node<Integer> reverseList_Iterative(Node<Integer> list) {
		// 输入验证
		if(list == null) {
			return null;
		}
		
		// 反转后链表的头节点
		Node<Integer> reverseHeadNode = null;
		// 初始化当前节点，前节点与后节点指针，其中当前节点与前节点指针用于反转链表指针，后节点指针用于存储链表后部分的信息
		Node<Integer> nodeIndex = list;
		Node<Integer> preIndex = null;
		Node<Integer> nextIndex = null;

		while(nodeIndex != null) {
			// 反转指针前必须存储链表后部分的信息，否则导致链表断裂
			nextIndex = nodeIndex.getNextNode();	
			
			// 反转后链表的头节点即为原链表的尾节点
			if(nextIndex == null) {
				reverseHeadNode = nodeIndex;
			}

			// 反转当前节点与前节点间的指针
			nodeIndex.setNextNode(preIndex);		

			// 向后移动当前节点与前节点指针
			preIndex = nodeIndex;
			nodeIndex = nextIndex;
		}

		return reverseHeadNode;
	}

	/**
	 * 
	 * @param list 原链表
	 * @return Node<Integer> 反转后链表（的头节点）
	 * @description 递归方式实现反转链表，关键在于对递归的理解：大事化小，小事化了。即假设已经通过递归边界条件解决前面的小规模问题，再通过小规模问题的结果解决大规模问题
	 * 				递归封装屏蔽了小规模问题的解决详情，重点关注通过小规模问题的结果解决大规模问题的步骤
	 */
	public Node<Integer> reverseList_Recursive(Node<Integer> list) {
		// 输入验证
		if(list == null) {
			return null;
		}
		
		// 递归边界/结束条件
		if(list.getNextNode() == null) {
			return list;
		}
		
		// 先反转后面的链表，直至走到链表的末端节点（即为递归结束条件）
		Node<Integer> reversedHeadNode = reverseList_Recursive(list.getNextNode());
		// 再将当前节点设置为后节点的后续节点
		list.getNextNode().setNextNode(list);
		list.setNextNode(null);
		
		return reversedHeadNode;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 实例化内部类的时候要先实例化外部类
		ReverseList reverseList = new ReverseList();

		Node<Integer> a = reverseList.new Node<Integer>(1);
		Node<Integer> b = reverseList.new Node<Integer>(2);
		Node<Integer> c = reverseList.new Node<Integer>(3);
		Node<Integer> d = reverseList.new Node<Integer>(4);
		Node<Integer> e = reverseList.new Node<Integer>(5);
		Node<Integer> f = reverseList.new Node<Integer>(6);

		//构建单向链表
		a.setNextNode(b);
		b.setNextNode(c);
		c.setNextNode(d);
		d.setNextNode(e);
		e.setNextNode(f);
		
		// 循环方法
//		Node<Integer> list = reverseList.reverseList_Iterative(a);
//		System.out.println("反转后链表头节点：" + list.getE());
//		System.out.println("反转后链表：");
//		while(list != null) {
//			System.out.println(list.getE());
//			list = list.getNextNode();
//		}
		
		// 递归方法
		Node<Integer> list = reverseList.reverseList_Recursive(a);
		System.out.println("反转后链表头节点：" + list.getE());
		System.out.println("反转后链表：");
		while(list != null) {
			System.out.println(list.getE());
			list = list.getNextNode();
		}
	}

}
