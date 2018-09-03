/**
 * @description 0,1,2...n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字，求出这个圆圈里剩下的最后一个数字
 */
package chapter6;

class Node {
	int val;
	Node next;
	public Node(int val) {
		this.val = val;
	}
}

public class LastNumberInCircle {

	/**
	 * 
	 * @param n 数字总个数
	 * @param m 删除数字的移动步数
	 * @return
	 * @description 使用环形链表模拟圆圈，进而模拟删除数字的过程解决问题，时间复杂度O(mn)，空间复杂度O(n)
	 */
	public static int getLastNumberInCircle(int n, int m) {
		// 输入验证
		if(n < 1 || m < 1) {
			return -1;
		}
		
		// 构造环形链表
		Node headNode = new Node(0);	// 头节点（链表头节点很重要，一般需要备份）
		Node tempNode = headNode;
		for(int i = 1; i < n; i++) {
			tempNode.next = new Node(i);
			tempNode = tempNode.next;
		}
		tempNode.next = headNode;
		
		// 循环删除节点至只剩一个节点（由于删除节点操作需要保存删除节点的前一节点信息，因此从tempNode开始遍历而不是headNode）
		while(tempNode != tempNode.next) {
			for(int i = 1; i < m; i++) {
				tempNode = tempNode.next;
			}
			System.out.println("当前删除节点为：" + tempNode.next.val);
			tempNode.next = tempNode.next.next;
		}
		
		return tempNode.val;
	}
	
	/**
	 * 
	 * @param n 数字总个数
	 * @param m 删除数字的移动步数
	 * @return
	 * @description 分析每次被删除的数字的规律从而得到完整递推公式，根据递推公式使用循环（从下向上）或者递归（从上向下）解决问题，时间复杂度O(n)，空间复杂度O(1)
	 */
	public static int getLastNumberInCircle2(int n, int m) {
		// 输入验证
		if(n < 1 || m < 1) {
			return -1;
		}
		
		int lastNum = 0;
		for(int i = 2; i <= n; i++) {
			lastNum = (lastNum + m) % i;
		}
		
		return lastNum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("环形链表：圆圈中最后剩下的数字是：" + getLastNumberInCircle(5, 3));
		System.out.println("递推公式：圆圈中最后剩下的数字是：" + getLastNumberInCircle2(5, 3));
	}

}
