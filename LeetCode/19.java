class Solution {

  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return head;
    }

    int length = 0;
    ListNode cur = head;
    while (cur != null) {
      length += 1;

      cur = cur.next;
    }

    if (n == length) {
      return head.next;
    }

    ListNode prev = null;
    cur = head;
    for (int i = 1; i <= length - n; i++) {
      prev = cur;
      cur = cur.next;
    }
    prev.next = cur.next;

    return head;
  }
}