class Solution {

  public ListNode middleNode(ListNode head) {
    int length = 0;
    ListNode cur = head;
    while (cur.next != null) {
      cur = cur.next;
      length += 1;
    }

    int target = length / 2;
    if (length % 2 != 0) {
      target += 1;
    }

    cur = head;
    for (int i = 0; i < target; i++) {
      cur = cur.next;
    }

    return cur;
  }
}