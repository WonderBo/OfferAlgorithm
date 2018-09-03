/**
 * @description 根据二叉树的前序遍历和中序遍历重建二叉树，其中前/中序遍历结果均不含重复数字
 */
package chapter2;

public class ConstructBinaryTree {

	/**
	 * @author 汪波
	 * @param <E>
	 * @description 二叉树节点类
	 */
	public class BinaryTreeNode<E> {
		private E e;
		private BinaryTreeNode<E> leftChildNode;
		private BinaryTreeNode<E> rightChildNode;

		public E getE() {
			return e;
		}
		public void setE(E e) {
			this.e = e;
		}
		public BinaryTreeNode<E> getLeftChildNode() {
			return leftChildNode;
		}
		public void setLeftChildNode(BinaryTreeNode<E> leftChildNode) {
			this.leftChildNode = leftChildNode;
		}
		public BinaryTreeNode<E> getRightChildNode() {
			return rightChildNode;
		}
		public void setRightChildNode(BinaryTreeNode<E> rightChildNode) {
			this.rightChildNode = rightChildNode;
		}
	}

	/**
	 * 
	 * @param preorder 前序遍历
	 * @param inorder 中序遍历
	 * @return 二叉树头节点（代表二叉树）
	 * @description 输入验证后，通过分治递归由前/中序遍历得到二叉树
	 */
	public BinaryTreeNode<Integer> construct(int[] preorder, int[] inorder) {
		//输入验证
		if(preorder == null || inorder == null || preorder.length <= 0 || preorder.length != inorder.length) {
			return null;
		}
		return constructCore(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
	}

	/**
	 * 
	 * @param preorder 前序遍历
	 * @param inorder 中序遍历
	 * @param startPreorderIndex 前序遍历头索引
	 * @param endPreorderIndex 前序遍历尾索引
	 * @param startInorderIndex 中序遍历头索引
	 * @param endInorderIndex 中序遍历尾索引
	 * @return 二叉树头节点（代表二叉树）
	 * @description 引入索引操作数组，通过分治递归由前/中序遍历得到二叉树
	 */
	public BinaryTreeNode<Integer> constructCore(int[] preorder, int[] inorder, int startPreorderIndex, int endPreorderIndex, int startInorderIndex, int endInorderIndex) {	
		//表示根节点（前序遍历序列的第一个值是根节点的值）
		BinaryTreeNode<Integer> rootNode = new BinaryTreeNode<Integer>();
		Integer rootNodeValue = preorder[startPreorderIndex];
		rootNode.setE(rootNodeValue);

		//边界条件，即递归结束条件
		if(startPreorderIndex == endPreorderIndex) {
			if(startInorderIndex == endInorderIndex && preorder[startPreorderIndex] == inorder[startInorderIndex]) {
				return rootNode;
			} else {
				System.out.println(startPreorderIndex);
				System.out.println(startInorderIndex);
				System.out.println(endInorderIndex);
				System.out.println("非法输入-1");
			}	
		}

		//在中序遍历序列中根据根节点的值找到根节点索引，作为划分左右子树的依据
		int rootInorderIndex = startInorderIndex;
		while(rootInorderIndex < endInorderIndex && inorder[rootInorderIndex] != rootNodeValue) {
			rootInorderIndex ++;
		}
		if(rootInorderIndex == endInorderIndex && inorder[rootInorderIndex] != rootNodeValue) {
			System.out.println("非法输入-2");
		}

		//构建左子树（由于边界条件在前，此处判断条件是可选的）
		int leftLength = rootInorderIndex - startInorderIndex;
		int leftStartPreorderIndex = startPreorderIndex + 1;
		int leftEndPreorderIndex = startPreorderIndex + leftLength;
		int leftStartInorderIndex = startInorderIndex;
		int leftEndInorderIndex = startInorderIndex + leftLength - 1;
		if(leftLength > 0) {
			rootNode.setLeftChildNode(constructCore(preorder, inorder, leftStartPreorderIndex, leftEndPreorderIndex, leftStartInorderIndex, leftEndInorderIndex));
		}

		//构建右子树（由于边界条件在前，此处判断条件是可选的）
		int rightStartPreorderIndex = leftEndPreorderIndex + 1;
		int rightEndPreorderIndex = endPreorderIndex;
		int rightStartInorderIndex = rootInorderIndex + 1;
		int rightEndInorderIndex = endInorderIndex;
		if(leftLength < endPreorderIndex - startPreorderIndex) {
			rootNode.setRightChildNode(constructCore(preorder, inorder, rightStartPreorderIndex, rightEndPreorderIndex, rightStartInorderIndex, rightEndInorderIndex));
		}

		return rootNode;
	}

	/**
	 * 
	 * @param node
	 * @description 递归方式实现后序遍历
	 */
	public void postorderRecursively(BinaryTreeNode<Integer> node) {
		if(node != null) {
			postorderRecursively(node.getLeftChildNode());
			postorderRecursively(node.getRightChildNode());
			System.out.print(node.getE() + " ");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] preorder = new int[]{10,6,4,8,14,12,16};
		int[] inorder = new int[]{4,6,8,10,12,14,16};
		ConstructBinaryTree constructBinaryTree = new ConstructBinaryTree();
		BinaryTreeNode<Integer> rootNode = constructBinaryTree.construct(preorder, inorder);
		System.out.print("二叉树的后序遍历：");
		constructBinaryTree.postorderRecursively(rootNode);
	}
}
