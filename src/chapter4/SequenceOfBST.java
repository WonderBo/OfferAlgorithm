/**
 * @description 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。假设输入的数组的任意两个数字都互不相同
 */
package chapter4;

public class SequenceOfBST {

	/**
	 * 
	 * @param sequence 后序遍历序列
	 * @return
	 * @description 判断整数数组是不是二叉搜索树的后序遍历序列
	 */
	public static boolean isSequenceOfBST(int[] sequence) {
		// 输入判断
		if(sequence == null) {
			return false;
		}

		return isSequenceOfBSTCore(sequence, 0, sequence.length - 1);
	}

	/**
	 * 
	 * @param sequence 后序遍历序列
	 * @param startIndex 子树起始下标
	 * @param endIndex 子树结束下标
	 * @return
	 * @description 二叉树遍历的关键在于确定二叉树的根节点，然后利用递归解决二叉树问题（其中注意递归边界条件的形式）
	 */
	public static boolean isSequenceOfBSTCore(int[] sequence, int startIndex, int endIndex) {
		// 二叉搜索树的根节点为后序遍历序列的最后一个元素
		int root = sequence[endIndex];

		// 在二叉搜索树中左子树节点的值小于根节点的值（确定断点）
		int i = startIndex;
		for(; i < endIndex; i++) {
			if(sequence[i] > root) {
				break;
			}
		}

		// 在二叉搜索树中右子树节点的值大于根节点的值（验证规则）
		int j = i;
		for(; j < endIndex; j++) {
			if(sequence[j] < root) {	// 递归边界条件1（递归边界条件分为正常结束与异常结束两种，正常结束又分为显式判断与隐式判断）
				return false;
			}
		}

		// 判断左子树是不是二叉搜索树
		boolean left = true;
		if(i > startIndex) {	// 递归边界条件2（包含在递归判断条件内【隐式】：该树没有节点）
			left = isSequenceOfBSTCore(sequence, startIndex, i - 1);
		}

		// 判断右子树是不是二叉搜索树
		boolean right = true;
		if(i < endIndex) {	// 递归边界条件2（包含在递归判断条件内【隐式】：该树没有节点）
			right = isSequenceOfBSTCore(sequence, i, endIndex - 1);
		}

		return left && right;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] sequence = {5, 7, 6, 9, 11, 10, 8};
		System.out.println("该整数数组是否为二叉搜索树的后序遍历序列：" + isSequenceOfBST(sequence));
	}

}
