/**
 * @description 输入一颗二叉树的根节点，求出该树的深度（从根节点到叶节点依次经过的节点形成树的一条路径，最长路径的长度为树的深度）
 */
package chapter6;

class BTNode {
	int val;
	BTNode left;
	BTNode right;
	BTNode(int x) { val = x; }
}

public class TreeDepth {

	/**
	 * 
	 * @param rootNode 树节点
	 * @return 节点深度
	 * @description 利用分治递归解决二叉树问题，根据左右子树深度的较大值求出节点深度
	 */
	public static int getTreeDepth(BTNode rootNode) {
		// 输入验证 + 递归边界条件（合二为一，因此不需要getTreeDepthCore()方法）
		if(rootNode == null) {
			return 0;
		}
		
		int leftLength = getTreeDepth(rootNode.left);
		int rightLength = getTreeDepth(rootNode.right);
		
		return Math.max(leftLength, rightLength) + 1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTNode a = new BTNode(1);
		BTNode b = new BTNode(2);
		BTNode c = new BTNode(3);
		BTNode d = new BTNode(4);
		BTNode e = new BTNode(5);
		BTNode f = new BTNode(6);
		BTNode g = new BTNode(7);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.right = f;
		e.left = g;
		
		System.out.println("二叉树的高度为：" + TreeDepth.getTreeDepth(a));
	}

}
