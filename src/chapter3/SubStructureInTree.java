/**
 * @description 输入两棵二叉树A和B，判断B是不是A的子结构（非子树）
 */
package chapter3;

public class SubStructureInTree {

	/**
	 * @description 二叉树节点类（内部类）
	 */
	private class BinaryTreeNode<E> {
		private E e;
		private BinaryTreeNode<E> leftChildNode;
		private BinaryTreeNode<E> rightChildNode;

		public BinaryTreeNode(E e, BinaryTreeNode<E> leftChildNode, BinaryTreeNode<E> rightChildNode) {
			this.e = e;
			this.leftChildNode = leftChildNode;
			this.rightChildNode = rightChildNode;
		}

		public BinaryTreeNode(E e) {
			this(e, null, null);
		}

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
	 * @param d1 双精度浮点数1
	 * @param d2 双精度浮点数2
	 * @return boolean
	 * @description 计算机表示浮点数(float或double类型)都有一个精度限制，对于超出了精度限制的浮点数，计算机会把它们的精度之外的小数部分截断。
	 * 				因此，本来不相等的两个浮点数在计算机中可能就变成相等的了，则不能直接使用 == 来判断两个小数是否相等。
	 * 				一般如果两个浮点数之差的绝对值小于或等于某一个可接受的误差(即精度，比如0.00000001即可)，就认为它们是相等的。
	 */
	public boolean doubleEquals(Double d1, Double d2) {
		// 输入验证
		if(d1 == null || d2 == null) {
			return false;
		}
		
		if(Math.abs(d1.doubleValue() - d2.doubleValue()) < 0.0000001) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param binaryTree1 二叉树A
	 * @param binaryTree2 二叉树B
	 * @return boolean
	 * @description 递归实现 定位->比较，定位：根据节点值相等递归遍历二叉树来定位比较的起始节点，比较：doesTree1HasTree2(A, B)
	 */
	public boolean hasSubTree(BinaryTreeNode<Double> binaryTree1, BinaryTreeNode<Double> binaryTree2) {
		// 输入验证
		if(binaryTree1 == null || binaryTree2 == null) {
			return false;
		}
		
		// 初始化返回结果
		boolean result = false;
		
		// 递归遍历二叉树A，找到与B根节点值相等的节点（即定位），若相等后再判断结构是否相同
		if(doubleEquals(binaryTree1.getE(), binaryTree2.getE())) {
			result = doesTree1HasTree2(binaryTree1, binaryTree2);		// 判断结构（此处不能直接return，还需要综合判断左右子树）
		}
		if(!result) {
			result = hasSubTree(binaryTree1.getLeftChildNode(), binaryTree2);
		}
		if(!result) {
			result = hasSubTree(binaryTree1.getRightChildNode(), binaryTree2);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param binaryTree1 二叉树A
	 * @param binaryTree2 二叉树B
	 * @return boolean 
	 * @description 递归判断二叉树A的子结构与二叉树B的结构是否一样
	 */
	public boolean doesTree1HasTree2(BinaryTreeNode<Double> binaryTree1, BinaryTreeNode<Double> binaryTree2) {
		// 递归边界条件
		if(binaryTree2 == null) {
			return true;
		}
		if(binaryTree1 == null) {
			return false;
		}
		
		// 递归判断两棵二叉树结构
		if(doubleEquals(binaryTree1.getE(), binaryTree2.getE())) {
			return doesTree1HasTree2(binaryTree1.getLeftChildNode(), binaryTree2.getLeftChildNode()) && 
					doesTree1HasTree2(binaryTree1.getRightChildNode(), binaryTree2.getRightChildNode());
		}
		
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 实例化内部类的时候要先实例化外部类
		SubStructureInTree subStructureInTree = new SubStructureInTree();
		
		// 构建二叉树g
		BinaryTreeNode<Double> a = subStructureInTree.new BinaryTreeNode<Double>(9.0);
		BinaryTreeNode<Double> b = subStructureInTree.new BinaryTreeNode<Double>(4.0);
		BinaryTreeNode<Double> c = subStructureInTree.new BinaryTreeNode<Double>(7.0);
		BinaryTreeNode<Double> d = subStructureInTree.new BinaryTreeNode<Double>(7.0);
		BinaryTreeNode<Double> e = subStructureInTree.new BinaryTreeNode<Double>(2.0, b, c);
		BinaryTreeNode<Double> f = subStructureInTree.new BinaryTreeNode<Double>(8.0, a, e);
		BinaryTreeNode<Double> g = subStructureInTree.new BinaryTreeNode<Double>(8.0, f, d);
		
		// 构建二叉树j
		BinaryTreeNode<Double> h = subStructureInTree.new BinaryTreeNode<Double>(9.0);
		BinaryTreeNode<Double> i = subStructureInTree.new BinaryTreeNode<Double>(2.0);
		BinaryTreeNode<Double> j = subStructureInTree.new BinaryTreeNode<Double>(8.0, h, i);
		
		System.out.println("二叉树j是否为二叉树g的子结构：" + subStructureInTree.hasSubTree(g, j));
	}

}
