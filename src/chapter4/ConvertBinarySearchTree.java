/**
 * @description 输入一棵二叉搜索树，将该二叉搜索树转化成一个排序的双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
package chapter4;

class BTNode {
	int val;
	BTNode left;
	BTNode right;
	public BTNode(int val) { this.val = val; }
}

public class ConvertBinarySearchTree {

	/**
	 * 
	 * @param treeNode
	 * @return
	 * @description 将二叉搜索树转化成一个排序的双向链表（尾节点），并往前遍历得到双向链表头节点
	 */
	public static BTNode convert(BTNode treeNode) {
		// 输入验证
		if(treeNode == null) {
			return null;
		}
		
		// 转换二叉搜索树为排序的双向链表
		BTNode lastListNode = null;
		// 注意参数传入null不会形成引用传递（虽然是传入对象类型，但是注意空指针的特殊性，可以看作值传递），因此必须使用返回值来保存值，入参null只是作为返回值的初始值
		BTNode headListNode = convertNode(treeNode, lastListNode); 
		
		// 由双向链表的尾节点往前遍历得到头节点s
		while(headListNode != null && headListNode.left != null) {
			headListNode = headListNode.left;
		}
		
		return headListNode;
	}
	
	/**
	 * 
	 * @param treeNode
	 * @param lastListNode
	 * @return BTNode 必须要返回BTNode，否则将导致空指针异常
	 * @description 递归实现中序遍历将二叉搜索树转化成排序的双向链表，其中注意双向链表作为入参传入到递归方法（双向链表也可作为类成员变量）
	 */
	public static BTNode convertNode(BTNode treeNode, BTNode lastListNode) {
		// 递归边界条件
		if(treeNode == null) {
			return null;
		}
		
		// 转化左子树
		if(treeNode.left != null) {
			lastListNode = convertNode(treeNode.left, lastListNode);
		}
		
		// 链接父节点与左子树（转化后链表的尾节点）：链表作为入参传入递归方法，因此只需关注链接前半部分
		treeNode.left = lastListNode;
		if(lastListNode != null) {
			lastListNode.right = treeNode;
		}
		lastListNode = treeNode;
		
		// 转化右子树（包括了链接父节点与右子树）
		if(treeNode.right != null) {
			lastListNode = convertNode(treeNode.right, lastListNode);
		}
		
		return lastListNode;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTNode a = new BTNode(10);
		BTNode b = new BTNode(6);
		BTNode c = new BTNode(14);
		BTNode d = new BTNode(4);
		BTNode e = new BTNode(8);
		BTNode f = new BTNode(12);
		BTNode g = new BTNode(16);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		
		BTNode convertList = convert(a);
		BTNode temp = convertList;
		while(temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.right;
		}
	}

}
