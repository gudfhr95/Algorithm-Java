class Solution {

  public TreeNode addOneRow(TreeNode root, int val, int depth) {
    if (depth == 1) {
      TreeNode newNode = new TreeNode(val);
      newNode.left = root;

      return newNode;
    }

    Queue<TreeNode> currentQueue = new LinkedList<>();
    Queue<TreeNode> nextQueue = new LinkedList<>();

    currentQueue.add(root);
    for (int i = 0; i < depth - 2; i++) {
      while (!currentQueue.isEmpty()) {
        TreeNode cur = currentQueue.remove();

        if (cur.left != null) {
          nextQueue.add(cur.left);
        }

        if (cur.right != null) {
          nextQueue.add(cur.right);
        }
      }

      currentQueue = nextQueue;
      nextQueue = new LinkedList<>();
    }

    while (!currentQueue.isEmpty()) {
      TreeNode cur = currentQueue.remove();

      TreeNode newLeft = new TreeNode(val);
      newLeft.left = cur.left;
      cur.left = newLeft;

      TreeNode newRight = new TreeNode(val);
      newRight.right = cur.right;
      cur.right = newRight;
    }

    return root;
  }
}