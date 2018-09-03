/**
 * @description 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
 */
package chapter4;

import java.util.LinkedList;
import java.util.Queue;

class BTNode2 {
	int val;
	BTNode2 left;
	BTNode2 right;
	public BTNode2(int val) { this.val = val; }
}

public class PrintTreeInLines {

	/**
	 * 
	 * @param root
	 * @description 利用队列宽度优先遍历二叉树，由于是分行打印，需要两个变量分别保存此行节点打印情况与下行节点数目信息
	 */
	public static void printTreeInLines(BTNode2 root) {
		// 输入验证
		if(root == null) {
			return;
		}

		// 利用队列实现自顶向下宽度优先遍历二叉树
		Queue<BTNode2> queue = new LinkedList<BTNode2>();
		queue.offer(root);		
		int toBePrinted = 1;	// 当前层中还没有打印的节点数
		int nextLevelNum = 0;	// 下一层的节点数
		while(!queue.isEmpty()) {
			// 出队当前列节点，并更新toBePrinted
			BTNode2 tempNode = queue.poll();
			System.out.print(tempNode.val + " ");
			toBePrinted --;

			// 入队列子节点，并更新nextLevelNum
			if(tempNode.left != null) {
				queue.offer(tempNode.left);
				nextLevelNum ++;
			}
			if(tempNode.right != null) {
				queue.offer(tempNode.right);
				nextLevelNum ++;
			}
			
			// toBePrinted等于0表示当前层的所有节点已经打印完毕，可以继续打印下一层
			if(toBePrinted == 0) {
				System.out.println();
				toBePrinted = nextLevelNum;
				nextLevelNum = 0;
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTNode2 a = new BTNode2(8);
		BTNode2 b = new BTNode2(6);
		BTNode2 c = new BTNode2(10);
		BTNode2 d = new BTNode2(5);
		BTNode2 e = new BTNode2(7);
		BTNode2 f = new BTNode2(9);
		BTNode2 g = new BTNode2(11);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		
		System.out.println("分行从上到下打印二叉树：");
		printTreeInLines(a);
	}

}
