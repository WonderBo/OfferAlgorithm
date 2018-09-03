/**
 * @description 如果一个链表中包含环，找出环的入口节点
 * 				将复杂问题分解为几个有效的步骤：(1)验证是否存在环，并找出环中任意一个节点 
 * 										   (2)得到环中节点数目
 * 		 								   (3)找出环的入口节点
 */
package chapter3;

public class EntryNodeInListLoop {

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
	 * @param list 链表
	 * @return Node<Integer> 遍历链表过程中的相遇节点（在环内）
	 * @description 快慢指针遍历链表，目的在于（1）验证链表是否存在环，（2）存在环则得到遍历时的相遇节点
	 */
	public Node<Integer> getMeetingNode(Node<Integer> list) {
		// 输入验证
		if(list == null) {
			return null;
		}

		// 快慢指针初始化
		Node<Integer> slowIndex = list;
		Node<Integer> fastIndex = list;

		// 由于后面循环结束条件为slowIndex == fastIndex，此时初始化满足结束条件，但是需要得到的是下次循环结束，因此需要手动执行一次循环，其中注意空指针异常
		slowIndex = slowIndex.getNextNode();
		if(slowIndex != null) {
			fastIndex = slowIndex.getNextNode();
		}
		while(slowIndex != null && fastIndex != null) {		// 循环结束条件1
			if(slowIndex == fastIndex) {					// 循环结束条件2
				return slowIndex;
			}

			// 快慢指针分别向后移动一步或者两步
			slowIndex = slowIndex.getNextNode();
			fastIndex = fastIndex.getNextNode();
			// 注意空指针异常判断
			if(fastIndex != null) {
				fastIndex = fastIndex.getNextNode();
			}
		}

		return null;		// 链表不存在环则返回null
	}

	/**
	 * 
	 * @param list 链表
	 * @return Node<Integer> 链表中环的入口节点
	 * @description 前后指针遍历链表，获取链表中环的入口节点，关键在于确定前后指针间的间距
	 */
	public Node<Integer> getEntryNodeOfLoop(Node<Integer> list) {
		// 输入验证：（1）验证链表空指针，（2）以及是否存在环(本题要求)
		// 由于getMeetingNode()已经对list进行空指针输入验证，因此此处无需重复验证
		Node<Integer> meetingNode = getMeetingNode(list);
		// 是否存在环验证
		if(meetingNode == null) {
			return null;
		}

		// 获取环中节点的数量（遍历环一圈）
		int nodeNumOfLoop = 1;
		Node<Integer> tempNode = meetingNode;
		while(tempNode.getNextNode() != meetingNode) {
			tempNode = tempNode.getNextNode();
			nodeNumOfLoop ++;
		}

		//初始化前后指针
		Node<Integer> aheadIndex = list;
		Node<Integer> behindIndex = list;

		// 前指针先向前移动k（环的节点数目）步
		for(int i = 0; i < nodeNumOfLoop; i++) {
			aheadIndex = aheadIndex.getNextNode();
		}

		//前后指针同时向前移动直至相遇，相遇节点即为环的入口节点
		while(aheadIndex != behindIndex) {
			aheadIndex = aheadIndex.getNextNode();
			behindIndex = behindIndex.getNextNode();
		}

		return aheadIndex;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//实例化内部类的时候要先实例化外部类
		EntryNodeInListLoop entryNodeInListLoop = new EntryNodeInListLoop();

		Node<Integer> a = entryNodeInListLoop.new Node<Integer>(1);
		Node<Integer> b = entryNodeInListLoop.new Node<Integer>(2);
		Node<Integer> c = entryNodeInListLoop.new Node<Integer>(3);
		Node<Integer> d = entryNodeInListLoop.new Node<Integer>(4);
		Node<Integer> e = entryNodeInListLoop.new Node<Integer>(5);
		Node<Integer> f = entryNodeInListLoop.new Node<Integer>(6);

		//构建含环链表
		a.setNextNode(b);
		b.setNextNode(c);
		c.setNextNode(d);
		d.setNextNode(e);
		e.setNextNode(f);
		f.setNextNode(c);
		
		System.out.println("相遇节点：" + entryNodeInListLoop.getMeetingNode(a).getE());
		System.out.println("环入口节点：" + entryNodeInListLoop.getEntryNodeOfLoop(a).getE());
	}

}
