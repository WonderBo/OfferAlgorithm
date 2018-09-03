/**
 * @description 链表实现
 */
package chapter2;

public class MyLinkedList<E> {

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

	//头节点
	private Node<E> headNode;	
	//单链表中存储的节点数
	private int size;

	/**
	 * @description 初始化头节点（为空），创建链表
	 */
	public MyLinkedList(){
		headNode = new Node<E>();	
	}

	/**
	 * 
	 * @param e要添加的元素
	 * @description 向链表头节点后添加元素
	 */
	public void add(E e){
		//根据e实例化一个新的节点对象
		Node<E> node = new Node<E>(e);

		//在头节点后插入新节点
		Node<E> aftNode = headNode.getNextNode();
		headNode.setNextNode(node);
		node.setNextNode(aftNode);
		//记录添加的节点数
		size++;
	}

	/**
	 * 
	 * @param index索引位置
	 * @return 返回删除的元素
	 * @description 删除指定索引位置的元素
	 */
	public E remove(int index){
		//获取要删除节点的前一个节点
		Node<E> preNode = select(index - 1);
		//获取要删除的节点
		Node<E> delNode = preNode.getNextNode();
		//获取要删除节点的后一个节点
		Node<E> aftNode = delNode.getNextNode();

		//先建立删除节点的前一个节点和删除节点的后一个节点的关系
		preNode.setNextNode(aftNode);
		//清除dNode的下一个节点
		delNode.setNextNode(null);

		//计数器减一
		size--;

		//返回删除节点中的数据域
		return delNode.getE();
	}

	/**
	 * 
	 * @param index索引位置
	 * @return 返回节点中的数据域
	 * @description 获取指定索引位置的元素
	 */
	public E get(int index){
		//查找指定索引位置的节点对象
		Node<E> node = select(index);
		//获取节点中的数据域元素并返回
		return node.getE();
	}

	/**
	 * 
	 * @return 返回size属性
	 * @description 获取单链表中存储的元素总数
	 */
	public int size(){
		return size;
	}

	/**
	 * 
	 * @param index索引位置
	 * @return 返回获取到的节点对象
	 * @description 获取指定索引位置的节点对象
	 */
	private Node<E> select(int index){
		//输入验证
		if(index < 0 || index > size) {
			return null;
		}
		//索引节点（相当于移动的索引指针）初始化为头节点
		Node<E> indexNode = headNode;
		//头节点对应索引位置为0
		if(index == 0){
			return headNode;
		}
		for(int i=0; i<index; i++){
			indexNode = indexNode.getNextNode();
		}
		return indexNode;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList<Integer> linkedList = new MyLinkedList<Integer>();
		linkedList.add(3);
		linkedList.add(7);
		System.out.println(linkedList.size());
		linkedList.remove(2);
		System.out.println(linkedList.size());
		System.out.println(linkedList.get(1));
	}

}
