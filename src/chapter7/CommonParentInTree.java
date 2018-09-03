/**
 * @description 输入两个树节点，求它们的最低公共祖先（根据不同条件下的树所对应的解决方法也不同）
 */
package chapter7;

import java.util.Iterator;
import java.util.LinkedList;

// 二叉搜索树节点
class BinaryTreeNode {
	int val;
	BinaryTreeNode left;
	BinaryTreeNode right;
	BinaryTreeNode(int x) { val = x; }
}

// 带有指向父节点指针的普通树节点
class TreeNodeWithParentPointer {
	int val;
	TreeNodeWithParentPointer parent;
	TreeNodeWithParentPointer[] children;
	TreeNodeWithParentPointer(int x) { val = x; }
}

// 普通树节点（不含指向父节点的指针）
class TreeNode {
	int val;
	TreeNode[] children;
	TreeNode(int x) { val = x; }
}

public class CommonParentInTree {

	/**
	 * 
	 * @param root 根节点
	 * @param node1 节点1
	 * @param node2 节点2
	 * @return
	 * @description 二叉搜索树中求两个节点的最低公共祖先（根据节点值大小递归判断最低公共祖先）
	 */
	public static BinaryTreeNode getCommonParentInTree(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
		// 输入验证 / 递归边界条件1
		if(root == null || node1 == null || node2 == null) {
			return null;
		}

		// 在二叉搜索树中根据值的大小递归判断最低公共祖先的位置
		if((root.val - node1.val) * (root.val - node2.val) <= 0) {	// 递归边界条件2（注意根节点正好是其中一个节点的情况，即这两个节点在一条路径上）
			return root;
		} else if(root.val > node1.val) {
			return getCommonParentInTree(root.left, node1, node2);
		} else {
			return getCommonParentInTree(root.right, node1, node2);
		}
	}

	
	/**
	 * 
	 * @param root 根节点
	 * @param node1 节点1
	 * @param node2 节点2
	 * @return
	 * @description 带有指向父节点指针的普通树中求两个节点的最低公共祖先（转换为两个链表求第一个公共节点问题（参考chapter5.FirstCommonNodeInLists））
	 */
	public static TreeNodeWithParentPointer getCommonParentInTree(TreeNodeWithParentPointer root, 
			TreeNodeWithParentPointer node1, TreeNodeWithParentPointer node2) {
		// 输入验证
		if(root == null || node1 == null || node2 == null) {
			return null;
		}
		
		int listLength1 = getLengthOfList(node1);
		int listLength2 = getLengthOfList(node2);
		int listLengthDiff = 0;								// 链表长度差
		// 由于单向链表的单向性（不可逆），从可扩展性方面建议拷贝入参的链表的头节点
		TreeNodeWithParentPointer longListNode = null;		// 长链表
		TreeNodeWithParentPointer shortListNode = null;		// 短链表
		// 为链表长度差、长链表、短链表三变量赋值
		if(listLength1 > listLength2) {
			listLengthDiff = listLength1 - listLength2;
			longListNode = node1;
			shortListNode = node2;
		} else {
			listLengthDiff = listLength2 - listLength1;
			longListNode = node2;
			shortListNode = node1;
		}
		
		// 在长链表中先移动 ‘长度差’ 步，从而保证后面步调一致
		for(int i = 0; i < listLengthDiff; i++) {
			longListNode = longListNode.parent;
		}
		
		// 长短链表同时向后移动，得到第一个公共节点（相同节点）
		while(longListNode != null && shortListNode != null && longListNode != shortListNode) {
			longListNode = longListNode.parent;
			shortListNode = shortListNode.parent;
		}

		return longListNode;
	}

	/**
	 * 
	 * @param treeNode 树节点（相当于链表头节点）
	 * @return 链表长度
	 * @description 遍历链表获取链表长度
	 */
	public static int getLengthOfList(TreeNodeWithParentPointer treeNode) {
		int length = 0;
		TreeNodeWithParentPointer indexTreeNode = treeNode;	// 复制链表头节点（在求链表长度过程中避免修改头节点，避免后面程序出错）
		while(indexTreeNode != null) {
			length ++;
			indexTreeNode = indexTreeNode.parent;
		}

		return length;
	}
	
	
	/**
	 * 
	 * @param root 根节点
	 * @param node1 节点1
	 * @param node2 节点2
	 * @return
	 * @description 普通树中求两个节点的最低公共祖先（使用两个链表分别保存根节点到两个节点的路径，再利用迭代器求公共节点）
	 */
	public static TreeNode getCommonParentInTree(TreeNode root, TreeNode node1, TreeNode node2) {
		// 输入验证
		if(root == null || node1 == null || node2 == null) {
			return null;
		}
		
		// 使用两个链表分别保存根节点到树中两个节点的路径
		LinkedList<TreeNode> pathList1 = new LinkedList<TreeNode>();
		LinkedList<TreeNode> pathList2 = new LinkedList<TreeNode>();
		getNodePath(root, node1, pathList1);
		getNodePath(root, node2, pathList2);
		
		TreeNode resultNode = null;
		// 利用迭代器遍历两个链表，求解两个链表中最后一个公共节点
		Iterator<TreeNode> iterator1 = pathList1.iterator();
		Iterator<TreeNode> iterator2 = pathList2.iterator();
		while(iterator1.hasNext() && iterator2.hasNext()) {
			TreeNode tempNode1 = iterator1.next();
			TreeNode tempNode2 = iterator2.next();
			
			if(tempNode1 == tempNode2) {
				resultNode = tempNode1;
			}
		}
		
		return resultNode;
	}
	
	/**
	 * 
	 * @param root 根节点
	 * @param node 节点
	 * @param list 根节点到节点的路径链表
	 * @return
	 * @description 利用递归实现树的前序遍历来获取根节点到输入节点的路径链表（注意前进与回溯的时机）
	 */
	public static boolean getNodePath(TreeNode root, TreeNode node, LinkedList<TreeNode> list) {
		// 递归边界条件1（肯定）   --即找到相应元素就直接返回true
		if(root == node) {
			return true;
		}
		
		boolean isFound = false;
		TreeNode[] childNodeArray = root.children;
		list.addLast(root);	// 找不到相应元素就将当前节点加入路径进行前进
		// 递归边界条件2（否定）
		if(childNodeArray != null && childNodeArray.length > 0) {
			// 遍历当前节点的所有子节点，在子节点里边找相应元素
			for(int i = 0; i < childNodeArray.length; i++) {
				if(isFound) {
					break;
				} else {
					isFound = getNodePath(childNodeArray[i], node, list);
				}
			}
		}
		
		// 找不到相应元素就将当前节点从路径中删除进行回溯（当递归回来到这里的时候，当前节点一定是list中的最后一个节点，即栈顶（用LinkedList模拟栈））
		if(!isFound) {
			list.removeLast();
		}
		
		return isFound;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
