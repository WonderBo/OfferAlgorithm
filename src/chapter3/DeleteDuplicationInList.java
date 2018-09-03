/**
 * @description 在排序的链表中删除所有重复的节点
 */
package chapter3;

/**
 * @author 汪波
 *
 */
public class DeleteDuplicationInList {

	/**
	 * @description 节点类（内部类）
	 */
	private class Node<E> {
		//数据域
		private E e;
		//引用域
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
	 * @param list	单向链表（实际为链表的头节点）
	 * @return Node<Integer>	删除过后的链表（头节点）
	 * @description 删除排序链表中所有重复的节点，注意链表头节点需要删除的特殊情况，同时需要注意获取nextNode后的为空判断
	 */
	public Node<Integer> deleteDuplication(Node<Integer> list) {
		//输入验证
		if(list == null) {
			return null;
		}

		Node<Integer> preNode = null;	//前一节点（保存连接信息）
		Node<Integer> node = list;		//当前节点
		Node<Integer> nextNode = null;	//下一节点（与当前节点比较确定是否应该删除）

		while(node != null) {
			nextNode = node.getNextNode();		
			boolean needDelete = false;	//删除标志
			//根据删除条件，设置删除标志（注意为空判断）
			if(nextNode != null && nextNode.getE().equals(node.getE())) {		
				needDelete = true;
			}

			if(!needDelete) {
				preNode = node;
				node = nextNode;
			} else {
				Integer value = node.getE();			//删除节点的值
				Node<Integer> toDeleteNode = node;	//待进行删除判断的节点
				//重复节点可能存在两个以上，因此需要进行循环判断，每次删除一个节点，直到删除完所有具有相同值(value)的节点后
				while(toDeleteNode != null && toDeleteNode.getE() == value) {
					nextNode = toDeleteNode.getNextNode();
					toDeleteNode = nextNode;
				}

				//单次相同值(value)节点删除完后进行连接操作，并移动当前节点
				if(preNode == null) {	//特殊情况：链表头节点需要删除
					preNode = nextNode;
					list  = preNode;	//返回删除后的头节点
				} else {
					preNode.setNextNode(nextNode);
				}
				node = nextNode;
			}
		}
		
		return list;
	}

	/**
	 * 
	 * @param list	单向链表（实际为链表的头节点）
	 * @description 打印输出链表的各节点值用于测试
	 */
	public void printListTest(Node<Integer> list) {
		//输入验证
		if(list == null) {
			System.out.println("链表为空！");
			return;
		}

		//顺序遍历列表
		Node<Integer> indexNode = list;
		while(indexNode != null) {
			System.out.print(indexNode.getE() + "  ");
			indexNode = indexNode.getNextNode();
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeleteDuplicationInList deleteDuplicationInList = new DeleteDuplicationInList();
		//实例化内部类的时候要先实例化外部类
		Node<Integer> a = deleteDuplicationInList.new Node<Integer>(1);
		Node<Integer> b = deleteDuplicationInList.new Node<Integer>(1);
		Node<Integer> c = deleteDuplicationInList.new Node<Integer>(3);
		Node<Integer> d = deleteDuplicationInList.new Node<Integer>(4);
		Node<Integer> e = deleteDuplicationInList.new Node<Integer>(4);
		Node<Integer> f = deleteDuplicationInList.new Node<Integer>(6);
		Node<Integer> g = deleteDuplicationInList.new Node<Integer>(7);
		Node<Integer> h = deleteDuplicationInList.new Node<Integer>(7);
		Node<Integer> i = deleteDuplicationInList.new Node<Integer>(7);

		//构建单向链表
		a.setNextNode(b);
		b.setNextNode(c);
		c.setNextNode(d);
		d.setNextNode(e);
		e.setNextNode(f);
		f.setNextNode(g);
		g.setNextNode(h);
		h.setNextNode(i);
		
		System.out.println("原链表a的数据：");
		deleteDuplicationInList.printListTest(a);
		System.out.println("删除重复节点后链表的数据：");
		Node<Integer> deletedList = deleteDuplicationInList.deleteDuplication(a);
		deleteDuplicationInList.printListTest(deletedList);
	}

}
