/**
 * 
 */
package chapter6;

class BTreeNode {
	int val;
	BTreeNode left;
	BTreeNode right;
	BTreeNode(int x) { val = x; }
}

public class BalancedBinaryTree {

	// 全局boolean标志，避免重复遍历二叉树求解二叉树深度
	private boolean isBlanced = true;
	
	/**
	 * 
	 * @param rootNode 二叉树节点
	 * @return 节点深度
	 * @description 求解二叉树深度与判断平衡二叉树的递归轨迹相同，则可以合并分析求解过程，避免双重递归，导致重复遍历节点求解二叉树深度
	 */
	public int getTreeDepth(BTNode rootNode) {
		// 递归边界条件
		if(rootNode == null) {
			return 0;
		}
		
		// 后序遍历二叉树（先遍历左子树再遍历右子树，再遍历根节点，根据根节点做出相关操作，比如输出，判断，压栈等）
		int leftLength = getTreeDepth(rootNode.left);
		int rightLength = getTreeDepth(rootNode.right);
		if(Math.abs(rightLength - leftLength) > 1) {
			isBlanced = false;
		}
		
		return Math.max(leftLength, rightLength) + 1;
	}
	
	/**
	 * 
	 * @param rootNode 二叉树节点
	 * @return 是否平衡二叉树
	 * @description 根据全局变量判断是否平衡二叉树
	 * 				类成员变量作用：1.引用传递值（不会因为值传递而不能更新值）	2.全局变量（当前对象所有方法共享值）	     【两者考虑角度不同，但实质相同】
	 */
	public boolean isBalancedBinaryTree(BTNode rootNode) {
		// 输入验证
		if(rootNode == null) {
			return true;
		}
		
		getTreeDepth(rootNode);
		return this.isBlanced;
	}
	
	// 不推荐，仅作参考
	/**
	 * 
	 * @param rootNode 二叉树节点
	 * @return 是否平衡二叉树
	 * @description 由于存在双重递归（getTreeDepth()，isBalancedBinaryTree_DoubleRecursion()），部分节点存在重复遍历情况，因此时间效率不高
	 */
	public boolean isBalancedBinaryTree_DoubleRecursion(BTNode rootNode) {
		// 输入验证 + 递归边界条件
		if(rootNode == null) {
			return true;
		}
		
		// 从上至下遍历每个节点，直接计算出该节点左右子树的高度，然后根据深度差判断是否为平衡二叉树，若当前节点满足平衡条件则继续向下判断，若不满足则直接返回
		int leftLength = TreeDepth.getTreeDepth(rootNode.left);		// getTreeDepth()为同一个包TreeDepth类中的静态方法，为递归求解二叉树深度
		int rightLength = TreeDepth.getTreeDepth(rootNode.right);
		if(Math.abs(leftLength - rightLength) > 1) {
			return false;
		} else {
			return isBalancedBinaryTree_DoubleRecursion(rootNode.left) && isBalancedBinaryTree_DoubleRecursion(rootNode.right);
		}
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
		
		BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
		long time1 = System.currentTimeMillis();
		System.out.println("一重递归：此二叉树是否为平衡二叉树：" + balancedBinaryTree.isBalancedBinaryTree(a));
		long time2 = System.currentTimeMillis();
		System.out.println("一重递归消耗时间：" + (time2 - time1));
		System.out.println("双重递归：此二叉树是否为平衡二叉树：" + balancedBinaryTree.isBalancedBinaryTree_DoubleRecursion(a));
		long time3 = System.currentTimeMillis();
		System.out.println("双重递归消耗时间：" + (time3 - time2));
	}

}
