/**
 * @description 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推
 */
package chapter4;

import java.util.Stack;

class BTNode3 {
	int val;
	BTNode3 left;
	BTNode3 right;
	public BTNode3(int val) { this.val = val; }
}

public class PrintTreeInZigzag {

	/**
	 * 
	 * @param root
	 * @description 使用两个栈按照之字形顺序打印二叉树，一个栈用于操作并打印当前行的节点，另一个栈根据奇偶规律按顺序保存下一行的节点
	 */
	@SuppressWarnings("unchecked")
	public static void printTreeInZigzag(BTNode3 root) {
		// 输入验证
		if(root == null) {
			return;
		}
		
		// 两个栈用于操作或保存当前层与下一层的相关节点信息
		Stack<BTNode3>[] stackArray = new Stack[2];
		stackArray[0] = new Stack<BTNode3>();
		stackArray[1] = new Stack<BTNode3>();
		int currentIndex = 0;	// 当前操作栈下标
		int nextIndex = 1;		// 当前保存/下次工作栈下标
		
		stackArray[currentIndex].push(root);
		while(!stackArray[0].isEmpty() || !stackArray[1].isEmpty()) {
			// 对操作栈进行弹栈操作
			BTNode3 tempNode = stackArray[currentIndex].pop();
			System.out.print(tempNode.val + " ");
			
			// 对保存栈进行压栈操作（根据currentIndex的值选择子节点的压栈顺序）
			if(currentIndex == 0) {		// currentIndex为0表示当前打印奇数层，则先保存左子节点再保存右子节点到保存栈中
				if(tempNode.left != null) {
					stackArray[nextIndex].push(tempNode.left);
				}
				if(tempNode.right != null) {
					stackArray[nextIndex].push(tempNode.right);
				}
			} else {					// currentIndex为1表示当前打印偶数层，则先保存右子节点再保存左子节点到保存栈中
				if(tempNode.right != null) {
					stackArray[nextIndex].push(tempNode.right);
				}
				if(tempNode.left != null) {
					stackArray[nextIndex].push(tempNode.left);
				}
			}
			
			// 操作栈为空则表示当前层所有节点已经打印完毕，此时交换操作栈与保存栈（下标）
			if(stackArray[currentIndex].isEmpty()) {
				System.out.println();
				currentIndex = 1- currentIndex;
				nextIndex = 1 - nextIndex;
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTNode3 a = new BTNode3(1);
		BTNode3 b = new BTNode3(2);
		BTNode3 c = new BTNode3(3);
		BTNode3 d = new BTNode3(4);
		BTNode3 e = new BTNode3(5);
		BTNode3 f = new BTNode3(6);
		BTNode3 g = new BTNode3(7);
		BTNode3 h = new BTNode3(8);
		BTNode3 i = new BTNode3(9);
		BTNode3 j = new BTNode3(10);
		BTNode3 k = new BTNode3(11);
		BTNode3 l = new BTNode3(12);
		BTNode3 m = new BTNode3(13);
		BTNode3 n = new BTNode3(14);
		BTNode3 o = new BTNode3(15);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		d.left = h;
		d.right = i;
		e.left = j;
		e.right = k;
		f.left = l;
		f.right = m;
		g.left = n;
		g.right = o;
		
		System.out.println("之字形打印二叉树：");
		printTreeInZigzag(a);
	}

}
