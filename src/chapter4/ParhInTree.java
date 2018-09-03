/**
 * @description 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径
 */
package chapter4;

import java.util.Stack;

class Node {
	int val;
	Node left;
	Node right;
	public Node(int val) { this.val = val; }
}

public class ParhInTree {

	/**
	 * 
	 * @param root
	 * @param expectedSum
	 * @description 利用二叉树的前序遍历（算法）和栈（数据结构）打印出二叉树中节点值的和为输入整数的所有路径
	 */
	public static void findParhInTree(Node root, int expectedSum) {
		// 输入验证
		if(root == null) {
			return;
		}
		
		Stack<Integer> pathStack = new Stack<Integer>();	// 保存路径值
		findParhInTreeCore(root, expectedSum, pathStack);
	}
	
	/**
	 * 
	 * @param node
	 * @param currentSum
	 * @param pathStack
	 * @description 递归实现前序遍历（或者深度优先遍历）得到二叉树的路径，用栈保存路径节点值，并判断二叉树路径的值和是否为指定值
	 */
	public static void findParhInTreeCore(Node node, int currentSum, Stack<Integer> pathStack) {
		// 向栈中压栈当前节点
		pathStack.push(node.val);
		
		// 如果当前节点为叶节点，并且路径上节点值的和等于输入的值，则打印出这条路径
		if(node.left == null && node.right == null) {
			if(node.val == currentSum) {
				System.out.print("满足条件路径：");
				for(int i : pathStack) {
					System.out.print(i + " ");
				}
				System.out.println();
			}
		}
		
		// 如果当前节点不是叶节点，则遍历它的子节点
		if(node.left != null) {
			findParhInTreeCore(node.left, currentSum - node.val, pathStack);
		}
		if(node.right != null) {
			findParhInTreeCore(node.right, currentSum - node.val, pathStack);
		}
		
		// 在返回父节点之前，在路径上删除当前节点
		pathStack.pop();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node a = new Node(10);
		Node b = new Node(5);
		Node c = new Node(12);
		Node d = new Node(4);
		Node e = new Node(7);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		
		findParhInTree(a, 22);
	}

}
