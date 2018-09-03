/**
 * @description 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的
 */
package chapter4;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int val) { this.val = val; }
}

public class SymmetricalBinaryTree {

	/**
	 * 
	 * @param root
	 * @return
	 * @description 根据题目要求约定入参为一棵二叉树（二叉树根节点）
	 */
	public static boolean isSymmetricalBinaryTree(TreeNode root) {
		return isSymmetrical(root, root);
	}
	
	/**
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 * @description 通过比较二叉树的前序遍历序列和对称前序遍历序列来判断二叉树是否对称（如果两个序列是一样的，那么二叉树就是对此的）
	 * 				采用递归实现前序遍历和对称前序遍历，两次递归逻辑类似，因此可以将两次递归合并为一次递归
	 */
	public static boolean isSymmetrical(TreeNode node1, TreeNode node2) {
		// 注意节点值全部相同但非对称二叉树的情况：引入下一层的空指针用来打散相同节点值
		// 递归边界条件1
		if(node1 == null && node2 == null) {
			return true;
		}
		// 递归边界条件2
		if(node1 == null || node2 == null) {
			return false;
		}
		
		// 递归实现前序遍历判断二叉树是否对称
		if(node1.val == node2.val) {
			return isSymmetrical(node1.left, node2.right) && isSymmetrical(node1.right, node2.left);
		} else {
			// 递归边界条件3
			return false;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode a = new TreeNode(8);
		TreeNode b = new TreeNode(6);
		TreeNode c = new TreeNode(6);
		TreeNode d = new TreeNode(5);
		TreeNode e = new TreeNode(7);
		TreeNode f = new TreeNode(7);
		TreeNode g = new TreeNode(5);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		
		System.out.println("二叉树是否为对称：" + isSymmetricalBinaryTree(a));
	}

}
