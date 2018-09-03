/**
 * @description 复制一个复杂链表，在复杂链表中，每个节点除了有一个next指针指向下一个节点，还有一个sibling指针指向链表中的任意节点或者null
 */
package chapter4;

class ComplexListNode {
	int val;
	ComplexListNode next;
	ComplexListNode sibling;
	public ComplexListNode(){}
	// 测试用构造函数
	public ComplexListNode(int val) {this.val = val;}
}

public class CopyComplexList {

	/**
	 * 
	 * @param head
	 * @description 复制原始链表的任意节点N并创建新节点N'，再把N'链接到N的后面（使用链表来保存原始节点与复制节点的映射关系，从而消除Map辅助空间）
	 */
	public static void cloneNodes(ComplexListNode head) {
		ComplexListNode tempNode = head;
		while(tempNode != null) {
			ComplexListNode cloneNode = new ComplexListNode();
			cloneNode.val = tempNode.val;
			cloneNode.next = tempNode.next;
			
			tempNode.next = cloneNode;
			
			tempNode = cloneNode.next;
		}
	}
	
	/**
	 * 
	 * @param head
	 * @description 如果原始链表上的节点N的sibling指向S，则它对应的复制节点N'的sibling指向S的复制节点S'
	 */
	public static void connectSiblingNodes(ComplexListNode head) {
		ComplexListNode tempNode = head;
		while(tempNode != null) {
			ComplexListNode cloneNode = tempNode.next;
			// 空指针判断
			if(tempNode.sibling != null) {
				cloneNode.sibling = tempNode.sibling.next;
			}
			
			tempNode = cloneNode.next;
		}
	}
	
	/**
	 * 
	 * @param head
	 * @return
	 * @description 把connectSiblingNodes得到的链表拆分为两个链表，奇数位置上的节点组成原始链表，偶数位置上的节点组成复制出来的链表
	 */
	public static ComplexListNode reconnectNodes(ComplexListNode head) {
		ComplexListNode cloneHead = null;	
		
		ComplexListNode temp = head;		
		ComplexListNode cloneTemp = null;
		
		// 节点初始化
		if(temp != null) {
			// 保存复制链表的头结点
			cloneHead = temp.next;	
			
			// temp：原始链表的复制指针，cloneTemp：复制链表的复制指针
			cloneTemp = temp.next;
			temp.next = cloneTemp.next;
			temp = temp.next;
		}
		
		// 循环拆分链表节点为两个新链表
		while(temp != null) {
			cloneTemp.next = temp.next;
			cloneTemp = cloneTemp.next;
			
			temp.next = cloneTemp.next;
			temp = temp.next;
		}
		
		return cloneHead;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 构造复杂链表
		ComplexListNode a = new ComplexListNode(1);
		ComplexListNode b = new ComplexListNode(2);
		ComplexListNode c = new ComplexListNode(3);
		ComplexListNode d = new ComplexListNode(4);
		ComplexListNode e = new ComplexListNode(5);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		a.sibling = c;
		b.sibling = e;
		c.sibling = null;
		d.sibling = b;
		e.sibling = null;
		
		// 复制复杂链表
		cloneNodes(a);
		connectSiblingNodes(a);
		ComplexListNode cloneHead = reconnectNodes(a);
		
		// 检查复制链表
		ComplexListNode temp = cloneHead;
		while(temp != null) {
			if(temp.sibling == null) {
				System.out.println("当前节点：" + temp.val + " 兄弟节点：null");
			} else {
				System.out.println("当前节点：" + temp.val + " 兄弟节点：" + temp.sibling.val);
			}
			temp = temp.next;
		}
	}

}
