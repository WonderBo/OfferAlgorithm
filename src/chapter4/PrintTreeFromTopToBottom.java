/**
 * @description 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印
 */
package chapter4;

import java.util.LinkedList;
import java.util.Queue;

class BTNode1 {
	int val;
	BTNode1 left;
	BTNode1 right;
	public BTNode1(int val) { this.val = val; }
}

public class PrintTreeFromTopToBottom {
	
	/**
	 * 
	 * @param root
	 * @description 利用队列宽度优先遍历二叉树（树是图的一种特殊退化形式）
	 */
	public static void printTreeFromTopToBottom(BTNode1 root) {
		// 输入验证
		if(root == null) {
			return;
		}
		
		// 利用队列实现自顶向下宽度优先遍历二叉树
		Queue<BTNode1> queue = new LinkedList<BTNode1>();
		queue.offer(root);	// 入队列根节点
		while(!queue.isEmpty()) {
			// 出队当前列节点
			BTNode1 tempNode = queue.poll();
			System.out.print(tempNode.val + " ");
			
			// 入队列子节点
			if(tempNode.left != null) {
				queue.offer(tempNode.left);
			}
			if(tempNode.right != null) {
				queue.offer(tempNode.right);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTNode1 a = new BTNode1(8);
		BTNode1 b = new BTNode1(6);
		BTNode1 c = new BTNode1(10);
		BTNode1 d = new BTNode1(5);
		BTNode1 e = new BTNode1(7);
		BTNode1 f = new BTNode1(9);
		BTNode1 g = new BTNode1(11);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		
		System.out.print("不分行从上到下打印二叉树：");
		printTreeFromTopToBottom(a);
	}

}
