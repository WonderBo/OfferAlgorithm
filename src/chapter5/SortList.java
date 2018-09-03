/**
 * @description 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 */
package chapter5;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class SortList {
	public ListNode sortList(ListNode head) {
		return mergeSort(head);
	}

	/**
	 * 
	 * @param l1 链表1
	 * @param l2 链表2
	 * @return
	 * @description 合并两个有序链表（时间复杂度为n，空间复杂度为1）
	 */
	public ListNode merge(ListNode l1, ListNode l2) {
		ListNode resultHeadNode = new ListNode(0);	// 保存头指针
		ListNode indexNode = resultHeadNode;		// 移动索引指针
		while(l1 != null && l2 != null) {
			if(l1.val < l2.val) {
				indexNode.next = l1;
				l1 = l1.next;
			} else {
				indexNode.next = l2;
				l2 = l2.next;
			}
			indexNode = indexNode.next;
		}

		while(l1 != null) {
			indexNode.next = l1;
			indexNode = indexNode.next;
			l1 = l1.next;
		}
		while(l2 != null) {
			indexNode.next = l2;
			indexNode = indexNode.next;
			l2 = l2.next;
		}
		return resultHeadNode.next;
	}

	/**
	 * 
	 * @param head
	 * @return
	 * @description 类似数组的归并排序，但是由于复用存在的链表节点，其空间复杂度降低
	 */
	public ListNode mergeSort(ListNode head) {
		// 输入验证和递归边界条件可合并
		if(head == null) {
			return null;
		}
		if(head.next == null) {
			return head;
		}

		// 快慢指针寻找链表中间节点（注意快慢指针移动先后顺序以及判断空指针异常）
		ListNode fastNode = head;
		ListNode slowNode = head;
		while(fastNode != null && slowNode != null) {
			fastNode = fastNode.next;
			if(fastNode != null) {
				fastNode = fastNode.next;
			}
			if(fastNode != null) {
				slowNode = slowNode.next;
			}
		}
		// 根据中间节点对链表进行截断
		ListNode middleNode = slowNode.next;
		slowNode.next = null;

		ListNode preSortedNode = mergeSort(head);
		ListNode postSortedNode = mergeSort(middleNode);
		return merge(preSortedNode, postSortedNode);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortList solution = new SortList();
		ListNode head = new ListNode(4);
		head.next = new ListNode(2);
		head.next.next = new ListNode(1);
		head.next.next.next = new ListNode(3);
		ListNode result = solution.mergeSort(head);
		while(result != null) {
			System.out.println(result.val);
			result = result.next;
		}
	}

}
