/**
 * @description 二叉树实现与遍历(二叉树可以由头节点表示，同链表)
 */
package chapter2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyBinaryTree<E> {

	/**
	 * @author 汪波
	 * @param <E>
	 * @description 二叉树节点类
	 */
	public static class BinaryTreeNode<E> {
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

	private BinaryTreeNode<E> root;

	public MyBinaryTree(BinaryTreeNode<E> root) {
		this.root = root;
	}

	public BinaryTreeNode<E> getRoot() {
		return root;
	}

	//递归方式实现遍历简洁易懂
	/**
	 * 
	 * @param node
	 * @description 递归方式实现前序遍历
	 */
	public void preorderRecursively(BinaryTreeNode<E> node) {
		if(node != null) {
			System.out.print(node.getE() + " ");
			preorderRecursively(node.getLeftChildNode());
			preorderRecursively(node.getRightChildNode());
		}
	}

	/**
	 * 
	 * @param node
	 * @description 递归方式实现中序遍历
	 */
	public void inorderRecursively(BinaryTreeNode<E> node) {
		if(node != null) {
			inorderRecursively(node.getLeftChildNode());
			System.out.print(node.getE() + " ");
			inorderRecursively(node.getRightChildNode());
		}
	}

	/**
	 * 
	 * @param node
	 * @description 递归方式实现后序遍历
	 */
	public void postorderRecursively(BinaryTreeNode<E> node) {
		if(node != null) {
			postorderRecursively(node.getLeftChildNode());
			postorderRecursively(node.getRightChildNode());
			System.out.print(node.getE() + " ");
		}
	}

	//循环方式实现遍历借用栈(控制输出顺序)和循环(控制调用逻辑)实现输出，其中对于栈要注意入栈顺序与弹栈条件与时机，对于循环要注意循环条件与边界
	//循环方式实现中序遍历与后序遍历：栈：中序遍历主要对各右子节点与当前节点（或者说是上级节点的左子节点）入栈，后序遍历主要对各左子节点入栈
	//循环方式实现中序遍历与后序遍历：循环：从下到上对左子节点和父节点根据顺序进行输出，相应的右子节点进行循环遍历；
	/**
	 * 
	 * @param node
	 * @description 循环方式实现前序遍历
	 */
	public void preorderIteratively(BinaryTreeNode<E> node) {
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		if(node != null) {
			stack.push(node);
			while(!stack.empty()) {
				//弹栈获取当前节点并输出当前节点
				node = stack.pop();
				System.out.print(node.getE() + " ");

				//根据顺序先入栈右子节点再入栈左子节点，相当于用子节点替换当前节点
				if(node.getRightChildNode() != null) {
					stack.push(node.getRightChildNode());
				}
				if(node.getLeftChildNode() != null) {
					stack.push(node.getLeftChildNode());
				}
			}
		}
	}

	/**
	 * 
	 * @param node
	 * @description 循环方式实现中序遍历
	 */
	public void inorderIteratively(BinaryTreeNode<E> node) {
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		while(node != null) {
			while(node != null) {
				if(node.getRightChildNode() != null) {
					//当前节点右子节点入栈
					stack.push(node.getRightChildNode());
				}
				//当前节点（或者说是上级节点的左子节点）入栈
				stack.push(node);
				node = node.getLeftChildNode();
			}
			//弹栈得到当前栈内最左节点
			node = stack.pop();
			//从下到上输出无右子节点的当前节点
			while(!stack.empty() && node.getRightChildNode() == null) {
				//
				System.out.print(node.getE() + " ");
				node = stack.pop();
			}
			//输出存在右子节点的当前节点
			System.out.print(node.getE() + " ");
			if(!stack.empty()) {
				//对当前节点的右子节点弹栈，获取并进行下次循环
				node = stack.pop();
			} else {
				node = null;
			}
		}
	}

	/**
	 * 
	 * @param node
	 * @description 循环方式实现后序遍历
	 */
	public void postorderIteratively(BinaryTreeNode<E> node) {
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		BinaryTreeNode<E> temp = node;
		while(node != null) {
			//对当前节点及其子孙左子节点进行入栈
			for(; node.getLeftChildNode()!=null; node=node.getLeftChildNode()) {
				stack.push(node);
			}
			//当前结点无右子节点或右子节点已经输出
			while(node != null && (node.getRightChildNode() == null || node.getRightChildNode() == temp)) {
				System.out.print(node.getE() + " ");
				//记录上一个已输出节点
				temp = node;
				if(stack.empty()) {
					return;
				}
				node = stack.pop();
			}
			//处理右子节点(先对当前节点入栈便于后面输出，然后获取当前节点的右子节点进行循环)
			stack.push(node);
			node = node.getRightChildNode();
		}
	}

	/**
	 * 
	 * @param node
	 * @description 利用队列宽度优先遍历二叉树
	 */
	public void bfsBinaryTree(BinaryTreeNode<E> node) {
		// 输入验证
		if(node == null) {
			return;
		}

		// 利用队列实现自顶向下宽度优先遍历二叉树
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(node);	// 入队列根节点
		while(!queue.isEmpty()) {
			// 出队当前列节点
			BinaryTreeNode<E> tempNode = queue.poll();
			System.out.print(tempNode.getE() + " ");

			// 入队列子节点
			if(tempNode.getLeftChildNode() != null) {
				queue.offer(tempNode.getLeftChildNode());
			}
			if(tempNode.getRightChildNode() != null) {
				queue.offer(tempNode.getRightChildNode());
			}
		}
	}

	/**
	 * 
	 * @param node
	 * @description 利用栈深度优先遍历二叉树（与二叉树的前序遍历相同）
	 */
	public void dfsBinaryTree(BinaryTreeNode<E> node) {
		// 输入验证
		if(node == null) {
			return;
		}

		// 利用栈实现自左到右深度优先遍历二叉树
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		stack.push(node);	// 入栈根节点
		while(!stack.isEmpty()) {
			// 出栈当前节点
			BinaryTreeNode<E> tempNode = stack.pop();
			System.out.print(tempNode.getE() + " ");

			// 压栈子节点（由于栈为先进后出，因此先压栈右子节点，再压栈左子节点，注意与队列的区别）
			if(tempNode.getRightChildNode() != null) {
				stack.push(tempNode.getRightChildNode());
			}
			if(tempNode.getLeftChildNode() != null) {
				stack.push(tempNode.getLeftChildNode());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeNode<String> a = new BinaryTreeNode<String>("4"); 
		BinaryTreeNode<String> b = new BinaryTreeNode<String>("8");
		BinaryTreeNode<String> c = new BinaryTreeNode<String>("6", a, b);  
		BinaryTreeNode<String> d = new BinaryTreeNode<String>("12");  
		BinaryTreeNode<String> e = new BinaryTreeNode<String>("16");  
		BinaryTreeNode<String> f = new BinaryTreeNode<String>("14", d, e);  
		BinaryTreeNode<String> g = new BinaryTreeNode<String>("10", c, f);
		MyBinaryTree<String> myBinaryTree = new MyBinaryTree<String>(g);

		System.out.print(" 递归实现前序遍历:");  
		myBinaryTree.preorderRecursively(myBinaryTree.getRoot());  
		System.out.println("\n");  

		System.out.print(" 递归实现中序遍历:");  
		myBinaryTree.inorderRecursively(myBinaryTree.getRoot());  
		System.out.println("\n");  

		System.out.print(" 递归实现后序遍历:");  
		myBinaryTree.postorderRecursively(myBinaryTree.getRoot());  
		System.out.println("\n");  

		System.out.print(" 循环实现前序遍历:");  
		myBinaryTree.preorderIteratively(myBinaryTree.getRoot());  
		System.out.println("\n");  

		System.out.print(" 循环实现中序遍历:");  
		myBinaryTree.inorderIteratively(myBinaryTree.getRoot());  
		System.out.println("\n");  

		System.out.print(" 循环实现后序遍历:");  
		myBinaryTree.postorderIteratively(myBinaryTree.getRoot());  
		System.out.println("\n");  

		System.out.print(" 宽度优先遍历二叉树:");  
		myBinaryTree.bfsBinaryTree(myBinaryTree.getRoot());  
		System.out.println("\n");  
		
		System.out.print(" 深度优先遍历二叉树:");  
		myBinaryTree.dfsBinaryTree(myBinaryTree.getRoot());  
		System.out.println("\n"); 
	}

}
