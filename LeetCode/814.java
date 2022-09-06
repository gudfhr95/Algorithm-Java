class Solution {

  public TreeNode pruneTree(TreeNode root) {
    int result = pruneTreeRec(root);
    if (result == 0) {
      return null;
    }

    return root;
  }

  public int pruneTreeRec(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int left = pruneTreeRec(node.left);
    int right = pruneTreeRec(node.right);

    if (left == 0) {
      node.left = null;
    }

    if (right == 0) {
      node.right = null;
    }

    return node.val + left + right;
  }
}