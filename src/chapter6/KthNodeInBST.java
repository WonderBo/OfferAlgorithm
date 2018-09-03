/**
 * @description 给定一二叉搜索树，请找出其中第k大的节点
 */
package chapter6;

// 二叉树节点类
class BinaryTreeNode {
	int val;
	BinaryTreeNode left;
	BinaryTreeNode right;
	BinaryTreeNode(int x) { val = x; }
}

public class KthNodeInBST {

	public int k;	// 计数器，对象成员属性作为保存方法入参值（此时入参为关于对象的引用传递，而非值传递，避免在递归方法外部k值的变化丢失）
	
	/**
	 * 
	 * @param rootNode 二叉树根节点
	 * @param k
	 * @return 第k大的二叉树节点
	 * @description 输入验证 + 递归实现
	 */
	public BinaryTreeNode getKthNodeInBST(BinaryTreeNode rootNode, int k) {
		// 输入验证
		if(rootNode == null || k <= 0) {
			return null;
		}
		
		this.k = k;
		
		return getKthNodeInBSTCore(rootNode, this.k);
	}
	
	/**
	 * 
	 * @param rootNode 二叉树根节点
	 * @param k	
	 * @return 第k大的二叉树节点
	 * @description 中序遍历二叉搜索树，其遍历序列的数值为递增排序，因此可以根据其中序遍历过程获取第k大的节点
	 */
	public BinaryTreeNode getKthNodeInBSTCore(BinaryTreeNode rootNode, int k) {
		BinaryTreeNode kthNode = null;		// 保存第k大的节点，若不为空则后续的递归遍历则不需要，返回即可（相当于递归执行的标志，注意与递归边界条件区别）
		
		// 遍历左子树（若kthNode不为空说明已经得到第k大的节点，则没必要执行后面语句，下同）
		if(kthNode == null && rootNode.left != null) {
			kthNode = getKthNodeInBSTCore(rootNode.left, this.k);	// 入参为this.k，不为k（需要引用传递而非值传递，下同）
		}
		// 遍历中间节点（其实遍历左子树和遍历右子树都是通过遍历中间节点实现，只是调用顺序不同而已）
		if(kthNode == null) {
			// 如果遍历到中间节点时计数器不为1，则递减，若为1，则当前中间节点为第k大的节点
			if(this.k == 1) {
				kthNode = rootNode;
			} else {
				this.k --;
			}
		}
		// 遍历右子树
		if(kthNode == null && rootNode.right != null) {
			kthNode = getKthNodeInBSTCore(rootNode.right, this.k);
		}
		
		return kthNode;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeNode a = new BinaryTreeNode(5);
		BinaryTreeNode b = new BinaryTreeNode(3);
		BinaryTreeNode c = new BinaryTreeNode(7);
		BinaryTreeNode d = new BinaryTreeNode(2);
		BinaryTreeNode e = new BinaryTreeNode(4);
		BinaryTreeNode f = new BinaryTreeNode(6);
		BinaryTreeNode g = new BinaryTreeNode(8);
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
		System.out.println("二叉搜索树的第k大节点：" + new KthNodeInBST().getKthNodeInBST(a, 3).val);
	}

}
