/**
 * @description 给出一单向链表的头指针和一节点指针，在O(1)时间内删除该链表节点
 * 				代码技术注意点：1.内部类的实例化过程；2.引用传递的方法中修改与置空(= null)该引用的差别
 */
package chapter3;

public class DeleteNodeInList {

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
	 * @param node	删除节点
	 * @description 把下一个节点的内存复制覆盖到要删除的节点实现O(1)的时间复杂度，但是注意另外的两种特殊情况（思维的全面性）
	 */
	public void deleteNode(Node<String> list, Node<String> node) {
		//输入验证（该处假设node存在于list）
		if(list == null || node == null) {
			return;
		}
		
		//边界情况1：链表只有一个节点，且删除该链表的头节点（或者说尾节点）
		if(list.getNextNode() == null && list == node) {
			//置空删除该节点
			list = null;	//在main方法中不会产生作用（如需产生作用需要把printListTest方法写进该方法内）
			
			printListTest(list);
			return;
		}
		
		//边界情况2：链表有多个节点，且删除尾节点（此时删除节点不存在下一个节点，因此只能顺序查找）
		if(list.getNextNode() != null && node.getNextNode() == null) {
			//顺序遍历到删除节点的前一节点（不可遍历到删除节点，因为单向链表的不可逆性）
			Node<String> indexNode = list;
			while(indexNode.getNextNode() != node) {
				indexNode = indexNode.getNextNode();
			}
			
			//置空删除该节点
			indexNode.setNextNode(null);
			node = null;	//在main方法中不会产生作用（如需产生作用需要把printListTest方法写进该方法内）
			
			printListTest(list);
			return;
		}
		
		//一般情况：链表有多个节点，删除的节点不是尾节点
		Node<String> nextNode = node.getNextNode();
		node.setE(nextNode.getE());
		node.setNextNode(nextNode.getNextNode());
		
		//置空删除该节点
		nextNode = null;
		
		printListTest(list);
	}
	
	/**
	 * 
	 * @param list	单向链表（实际为链表的头节点）
	 * @description 打印输出链表的各节点值用于测试
	 */
	public void printListTest(Node<String> list) {
		//输入验证
		if(list == null) {
			System.out.println("链表为空！");
			return;
		}
		
		//顺序遍历列表
		Node<String> indexNode = list;
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
		DeleteNodeInList deleteNodeInList = new DeleteNodeInList();
		//实例化内部类的时候要先实例化外部类
		Node<String> a = deleteNodeInList.new Node<String>("a");
		Node<String> b = deleteNodeInList.new Node<String>("b");
		Node<String> c = deleteNodeInList.new Node<String>("c");
		Node<String> d = deleteNodeInList.new Node<String>("d");
		Node<String> e = deleteNodeInList.new Node<String>("e");
		
		Node<String> z = deleteNodeInList.new Node<String>("z");
		
		//构建单向链表
		a.setNextNode(b);
		b.setNextNode(c);
		c.setNextNode(d);
		d.setNextNode(e);

		//注意：引用传递的方法中修改与置空(= null)该引用的差别
		
		System.out.println("原链表a的数据：");
		deleteNodeInList.printListTest(a);
		
		//修改：改变该地址的内容的时候（如修改对象的某个属性值），原来引用的相同地址的地方再次访问时属性信息也会变
		System.out.println("一般情况：删除非尾节点" + "a");
		deleteNodeInList.deleteNode(a, a);
		//deleteNodeInList.printListTest(a);
		
		System.out.println("特殊情况：删除尾节点" + "e");
		deleteNodeInList.deleteNode(a, e);
		//deleteNodeInList.printListTest(a);
		
		System.out.println("原链表z的数据：");
		deleteNodeInList.printListTest(z);
		
		//置空：引用传递的方法中置空只是将该引用指向null，不会修改该引用指向的对象，也不会改变方法外其他引用的指向
		System.out.println("特殊情况：单节点链表删除头节点(或者说尾节点)" + "z");
		deleteNodeInList.deleteNode(z, z);
		
		//调用者的指针仍然是指向原来的地址的，只是在deleteNodeInList方法内部将该引用地址指向null，并没有把原来调用的地址指向null,因此此处的z != null
		System.out.println("\n引用传递的方法中的置空不起作用测试：");
		deleteNodeInList.printListTest(z);
	}

}
