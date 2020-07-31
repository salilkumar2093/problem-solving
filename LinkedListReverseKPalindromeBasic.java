package com.manager.parking;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class KReverseList {
	public static void main(String... args) {
		KReverseList obj = new KReverseList();
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(3);
		node.next.next.next.next = new ListNode(4);
		node.next.next.next.next.next = new ListNode(4);
		node.next.next.next.next.next.next = new ListNode(5);
		ListNode newHead = obj.deleteDuplicates(node);
		while (newHead != null) {
			System.out.print(newHead.val + " ");
			newHead = newHead.next;
		}
	}

	/*
	 * 
	 * 
	 * [1,2,3,3,4,4,5] 1==2, prev =1 temp = 2 1 == 2 ptr.next =1, ptr =1, prev=2,
	 * temp = 3 2==3, ptr.next = 2, ptr =2, prev = 3, temp = 3 3 == 3, prev = 3,
	 * temp = 4
	 * 
	 */
	public ListNode deleteDuplicates(ListNode head) {
		ListNode newHead = new ListNode(-1);
		newHead.next = head;
		ListNode ptr = newHead;

		while (ptr.next != null && ptr.next.next != null) {
			if (ptr.next.val == ptr.next.next.val) {
				int val = ptr.next.val;
				while (ptr.next != null && ptr.next.val == val) {
					ptr.next = ptr.next.next;
				}
			} else {
				ptr = ptr.next;
			}
		}
		return newHead.next;
	}

	public boolean isPalindrome(ListNode head) {
		ListNode slow = head, fast = head, head2 = head;
		while (fast.next != null) {
			slow = slow.next;
			fast = slow.next;
		}
		System.out.println(fast.val + "  " + slow.val);
		ListNode rev = slow;
		slow.next = null;
		rev = reverseK(rev);
		System.out.println(rev.val);
		while (head2 != null && rev != null) {
			System.out.println(head2.val + " == " + rev.val);

			if (head2.val != rev.val)
				return false;
			head2 = head2.next;
			rev = rev.next;
		}
		if (head2 != null || rev != null)
			return false;
		return true;

	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null)
			return head;
		ListNode temp = head;

		ListNode finalNode = new ListNode(-1);
		finalNode.next = head;
		ListNode currentStart = finalNode;

		int count = 1;
		while (temp != null) {
			if (count % k == 0) {
				ListNode next = temp.next;
				temp.next = null;

				ListNode rev = reverseK(currentStart.next);
				currentStart.next = rev;
				while (rev.next != null)
					rev = rev.next;

				rev.next = next;
				currentStart = rev;
				temp = rev;
			}
			temp = temp.next;
			count++;
		}
		System.out.println(finalNode.val);
		return finalNode.next;
	}

	ListNode reverseK(ListNode head) {
		ListNode temp1 = head, currNext = head.next;
		while (currNext != null) {
			ListNode temp2 = currNext.next;
			currNext.next = temp1;
			temp1 = currNext;
			currNext = temp2;
		}
		head.next = null;
		return temp1;
	}
}
