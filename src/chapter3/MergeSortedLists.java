/**
 * @description 输入两个递增排序的链表，合并两个链表并使新链表的节点依然是递增排序的
 */
package chapter3;

public class MergeSortedLists {

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
	 * @param list1	递增链表1
	 * @param list2  递增链表2
	 * @return Node<Integer> 递增合并链表（的头节点）
	 * @description 递归实现合并两个递增链表，将两个递增链表的含较小值的头节点作为合并链表的尾节点
	 * 				链表 = 头节点 = 数据域 + 引用域
	 * 				在链表与二叉树中经常用到递归方法解决问题，其中链表的关键在于头节点，二叉树的关键在于根节点
	 */
	public Node<Integer> mergeSortedLists(Node<Integer> list1, Node<Integer> list2) {
		// 输入验证，也为递归边界条件
		if(list1 == null) {
			return list2;
		} else if(list2 == null) {
			return list1;
		}

		Node<Integer> mergedList = null;	// 已经合并的链表（的头节点）

		// 将两个链表的较小值头节点提取出来，并加入到已经合并的链表
		if(list1.getE() < list2.getE()) {
			mergedList = list1;		// 复制数据域
			mergedList.setNextNode(mergeSortedLists(list1.getNextNode(), list2));	// 修改引用域
		} else {
			mergedList = list2;
			mergedList.setNextNode(mergeSortedLists(list1, list2.getNextNode()));
		}

		return mergedList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 实例化内部类的时候要先实例化外部类
		MergeSortedLists mergeSortedLists = new MergeSortedLists();

		Node<Integer> a = mergeSortedLists.new Node<Integer>(1);
		Node<Integer> b = mergeSortedLists.new Node<Integer>(3);
		Node<Integer> c = mergeSortedLists.new Node<Integer>(5);
		Node<Integer> d = mergeSortedLists.new Node<Integer>(7);
		Node<Integer> e = mergeSortedLists.new Node<Integer>(2);
		Node<Integer> f = mergeSortedLists.new Node<Integer>(4);
		Node<Integer> g = mergeSortedLists.new Node<Integer>(6);
		Node<Integer> h = mergeSortedLists.new Node<Integer>(8);

		//构建单向链表
		a.setNextNode(b);
		b.setNextNode(c);
		c.setNextNode(d);

		e.setNextNode(f);
		f.setNextNode(g);
		g.setNextNode(h);

		Node<Integer> list = mergeSortedLists.mergeSortedLists(a, e);
		System.out.println("合并后的递增链表为：");
		while(list != null) {
			System.out.print(list.getE() + " ");
			list = list.getNextNode();
		}
	}

}
