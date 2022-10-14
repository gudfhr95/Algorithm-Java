class Solution {

  public ListNode deleteMiddle(ListNode head) {
    int length = 0;

    ListNode cur = head;
    while (cur != null) {
      length += 1;

      cur = cur.next;
    }

    int index = length / 2;
    if (index == 0) {
      return head.next;
    }

    cur = head;
    for (int i = 0; i < index - 1; i++) {
      cur = cur.next;
    }

    cur.next = cur.next.next;

    return head;
  }
}