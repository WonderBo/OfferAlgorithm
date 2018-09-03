/**
 * @description 输入两个链表，找出它们的第一个公共节点。
 */
package chapter5;

class MyListNode {
	int val;
	MyListNode next;
	MyListNode(int x) { val = x; }
}

public class FirstCommonNodeInLists {

	/**
	 * 
	 * @param listNode1 链表1
	 * @param listNode2 链表2
	 * @return 链表第一个公共节点
	 * @description 链表长度不一致，则遍历链表步调不一致，因此可以提前移动长链表从而确保步调一致，从而得到第一个公共链表节点。
	 * 				如果从链表尾节点起始向前遍历（先进后出）从而获得第一个公共链表节点，可以考虑使用辅助栈保存链表节点，但是空间复杂度增加。
	 */
	public static MyListNode getFirstCommonNodeInLists(MyListNode listNode1, MyListNode listNode2) {
		// 输入验证
		if(listNode1 == null || listNode2 == null) {
			return null;
		}
		
		int listLength1 = getLengthOfList(listNode1);
		int listLength2 = getLengthOfList(listNode2);
		int listLengthDiff = 0;		// 链表长度差
		// 由于单向链表的单向性（不可逆），从可扩展性方面建议拷贝入参的链表的头节点
		MyListNode longListNode = null;		// 长链表
		MyListNode shortListNode = null;	// 短链表
		// 为链表长度差、长链表、短链表三变量赋值
		if(listLength1 > listLength2) {
			listLengthDiff = listLength1 - listLength2;
			longListNode = listNode1;
			shortListNode = listNode2;
		} else {
			listLengthDiff = listLength2 - listLength1;
			longListNode = listNode2;
			shortListNode = listNode1;
		}
		
		// 在长链表中先移动 ‘长度差’ 步，从而保证后面步调一致
		for(int i = 0; i < listLengthDiff; i++) {
			longListNode = longListNode.next;
		}
		
		// 长短链表同时向后移动，得到第一个公共节点（相同节点）
		while(longListNode != null && shortListNode != null && longListNode != shortListNode) {
			longListNode = longListNode.next;
			shortListNode = shortListNode.next;
		}
		
		return longListNode;
	}
	
	/**
	 * 
	 * @param listNode 链表
	 * @return 链表长度
	 * @description 遍历链表获取链表长度
	 */
	public static int getLengthOfList(MyListNode listNode) {
		int listLength = 0;
		MyListNode indexListNode = listNode;	// 复制链表头节点（在求链表长度过程中避免修改头节点，避免后面程序出错）
		while(indexListNode != null) {
			listLength ++;
			indexListNode = indexListNode.next;
		}
		
		return listLength;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyListNode listNode1 = new MyListNode(1);
		MyListNode listNode2 = new MyListNode(2);
		MyListNode listNode3 = new MyListNode(3);
		MyListNode listNode4 = new MyListNode(4);
		MyListNode listNode5 = new MyListNode(5);
		MyListNode listNode6 = new MyListNode(6);
		MyListNode listNode7 = new MyListNode(7);
		// 链表1: listNode1
		listNode1.next = listNode2;
		listNode2.next = listNode3;
		listNode3.next = listNode6;
		listNode6.next = listNode7;
		// 链表2: listNode4
		listNode4.next = listNode5;
		listNode5.next = listNode6;
		listNode6.next = listNode7;
		
		System.out.println("两个链表的第一个公共节点为：" + getFirstCommonNodeInLists(listNode1, listNode4).val);
	}

}