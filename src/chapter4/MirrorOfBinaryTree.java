/**
 * @description 请完成一个函数，输入一棵二叉树，该函数输出它的镜像
 */
package chapter4;

class BinaryTreeNode {
	int val;
	BinaryTreeNode left;
	BinaryTreeNode right;
	public BinaryTreeNode(int val) { this.val = val; }
}

public class MirrorOfBinaryTree {

	/**
	 * 
	 * @param node
	 * @description 递归方式实现二叉树前序遍历，并交换二叉树左右节点位置生成镜像
	 */
	public static void getMirrorOfBinaryTreeRecursively(BinaryTreeNode node) {
		// 输入验证
		if(node == null) {
			return;
		}

		// 递归边界条件
		if(node.left == null && node.right == null) {
			return;
		}

		// 交换左右子节点位置
		BinaryTreeNode tempNode = node.left;
		node.left = node.right;
		node.right = tempNode;
		// 递归遍历并交换左右子节点
		getMirrorOfBinaryTreeRecursively(node.left);
		getMirrorOfBinaryTreeRecursively(node.right);
	}

	/**
	 * 
	 * @param node
	 * @description 前序遍历打印二叉树
	 */
	public static void printBinaryTree(BinaryTreeNode node) {
		if(node != null) {
			System.out.print(node.val + " ");
			printBinaryTree(node.left);
			printBinaryTree(node.right);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeNode a = new BinaryTreeNode(8);
		BinaryTreeNode b = new BinaryTreeNode(6);
		BinaryTreeNode c = new BinaryTreeNode(10);
		BinaryTreeNode d = new BinaryTreeNode(5);
		BinaryTreeNode e = new BinaryTreeNode(7);
		BinaryTreeNode f = new BinaryTreeNode(9);
		BinaryTreeNode g = new BinaryTreeNode(11);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		
		System.out.print("原来二叉树前序遍历：");
		printBinaryTree(a);
		System.out.print("\n镜像二叉树前序遍历：");
		getMirrorOfBinaryTreeRecursively(a);
		printBinaryTree(a);
	}

}
